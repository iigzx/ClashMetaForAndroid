package com.cmfa.design

import android.content.Context
import android.view.View
import com.cmfa.design.databinding.DesignAppCrashedBinding
import com.cmfa.design.util.applyFrom
import com.cmfa.design.util.bindAppBarElevation
import com.cmfa.design.util.layoutInflater
import com.cmfa.design.util.root

class AppCrashedDesign(context: Context) : Design<Unit>(context) {
    private val binding = DesignAppCrashedBinding
        .inflate(context.layoutInflater, context.root, false)

    override val root: View
        get() = binding.root

    fun setAppLogs(logs: String) {
        binding.logsView.text = logs
    }

    init {
        binding.self = this

        binding.activityBarLayout.applyFrom(context)

        binding.scrollRoot.bindAppBarElevation(binding.activityBarLayout)
    }
}