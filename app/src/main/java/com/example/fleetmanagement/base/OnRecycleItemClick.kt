package com.example.fleetmanagement.base

import android.view.View

interface OnRecycleItemClick<T> {
    fun onClick(t:T,view:View)
}