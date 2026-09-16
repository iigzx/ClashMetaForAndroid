package com.cmfa.design

import android.content.Context
import android.view.View
import com.cmfa.design.databinding.DesignSettingsCommonBinding
import com.cmfa.design.preference.category
import com.cmfa.design.preference.clickable
import com.cmfa.design.preference.preferenceScreen
import com.cmfa.design.preference.tips
import com.cmfa.design.util.applyFrom
import com.cmfa.design.util.bindAppBarElevation
import com.cmfa.design.util.layoutInflater
import com.cmfa.design.util.root

class ApkBrokenDesign(context: Context) : Design<ApkBrokenDesign.Request>(context) {
    data class Request(val url: String)

    private val binding = DesignSettingsCommonBinding
        .inflate(context.layoutInflater, context.root, false)

    override val root: View
        get() = binding.root

    init {
        binding.surface = surface

        binding.activityBarLayout.applyFrom(context)

        binding.scrollRoot.bindAppBarElevation(binding.activityBarLayout)

        val screen = preferenceScreen(context) {
            tips(R.string.application_broken_tips)

            category(R.string.reinstall)

            clickable(
                title = R.string.github_releases,
                summary = R.string.meta_github_url
            ) {
                clicked {
                    requests.trySend(Request(context.getString(R.string.meta_github_url)))
                }
            }
        }

        binding.content.addView(screen.root)
    }
}