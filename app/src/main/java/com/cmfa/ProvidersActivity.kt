package com.cmfa

import com.cmfa.common.util.intent
import com.cmfa.common.util.ticker
import com.cmfa.design.ProvidersDesign
import com.cmfa.design.util.showExceptionToast
import com.cmfa.util.withClash
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import java.util.concurrent.TimeUnit
import com.cmfa.design.R

class ProvidersActivity : BaseActivity<ProvidersDesign>() {
    override suspend fun main() {
        val providers = withClash { queryProviders().sorted() }
        val design = ProvidersDesign(this, providers)

        setContentDesign(design)

        val ticker = ticker(TimeUnit.MINUTES.toMillis(1))

        while (isActive) {
            select<Unit> {
                events.onReceive {
                    when (it) {
                        Event.ProfileLoaded -> {
                            val newList = withClash { queryProviders().sorted() }

                            if (newList != providers) {
                                startActivity(ProvidersActivity::class.intent)

                                finish()
                            }
                        }
                        else -> Unit
                    }
                }
                design.requests.onReceive {
                    when (it) {
                        is ProvidersDesign.Request.Update -> {
                            launch {
                                try {
                                    withClash {
                                        updateProvider(it.provider.type, it.provider.name)
                                    }

                                    design.notifyChanged(it.index)
                                } catch (e: Exception) {
                                    design.showExceptionToast(
                                        getString(
                                            R.string.format_update_provider_failure,
                                            it.provider.name,
                                            e.message
                                        )
                                    )

                                    design.notifyUpdated(it.index)
                                }
                            }
                        }
                    }
                }
                if (activityStarted) {
                    ticker.onReceive {
                        design.updateElapsed()
                    }
                }
            }
        }
    }
}