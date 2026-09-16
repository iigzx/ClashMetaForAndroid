package com.cmfa.common.constants

import android.content.ComponentName
import com.cmfa.common.util.packageName

object Components {
    private const val componentsPackageName = "com.cmfa"

    val MAIN_ACTIVITY = ComponentName(packageName, "$componentsPackageName.MainActivity")
    val PROPERTIES_ACTIVITY = ComponentName(packageName, "$componentsPackageName.PropertiesActivity")
}