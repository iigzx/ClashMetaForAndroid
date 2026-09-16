package com.cmfa.service.util

import android.content.Intent

val Intent.packageName: String?
    get() {
        return data?.takeIf { it.scheme == "package" }?.schemeSpecificPart
    }