package com.bokju.amian.permission

import android.Manifest

enum class AmianPermission(val permissions: List<String>) {
    LOCATION(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
}
