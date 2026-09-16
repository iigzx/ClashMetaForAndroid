package com.cmfa.design

import android.content.Context
import android.view.View
import com.cmfa.design.databinding.DesignSettingsBinding
import com.cmfa.design.util.applyFrom
import com.cmfa.design.util.bindAppBarElevation
import com.cmfa.design.util.layoutInflater
import com.cmfa.design.util.root

class SettingsDesign(context: Context) : Design<SettingsDesign.Request>(context) {
    enum class Request {
        StartApp, StartNetwork, StartOverride, StartMetaFeature,
    }

    private val binding = DesignSettingsBinding
        .inflate(context.layoutInflater, context.root, false)

    override val root: View
        get() = binding.root

    init {
        binding.self = this

        binding.activityBarLayout.applyFrom(context)

        binding.scrollRoot.bindAppBarElevation(binding.activityBarLayout)
    }

    fun request(request: Request) {
        requests.trySend(request)
    }
}