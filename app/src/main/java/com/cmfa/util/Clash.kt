package com.cmfa.util

import android.content.Context
import android.content.Intent
import android.net.VpnService
import com.cmfa.common.compat.startForegroundServiceCompat
import com.cmfa.common.constants.Intents
import com.cmfa.common.util.intent
import com.cmfa.design.store.UiStore
import com.cmfa.service.ClashService
import com.cmfa.service.TunService
import com.cmfa.service.util.sendBroadcastSelf

fun Context.startClashService(): Intent? {
    val startTun = UiStore(this).enableVpn

    if (startTun) {
        val vpnRequest = VpnService.prepare(this)
        if (vpnRequest != null)
            return vpnRequest

        startForegroundServiceCompat(TunService::class.intent)
    } else {
        startForegroundServiceCompat(ClashService::class.intent)
    }

    return null
}

fun Context.stopClashService() {
    sendBroadcastSelf(Intent(Intents.ACTION_CLASH_REQUEST_STOP))
}