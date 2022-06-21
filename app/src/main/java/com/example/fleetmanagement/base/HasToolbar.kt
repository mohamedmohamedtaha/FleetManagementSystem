package com.example.fleetmanagement.base

import androidx.appcompat.widget.Toolbar

interface HasToolbar {
    fun setToolbar(toolbar: Toolbar)
    fun getToolbar():Toolbar
    fun showToolbar(b:Boolean)
    fun showBackButton(b:Boolean)
    fun showHomeLogo(b:Boolean)
    fun showProfileLogo(b:Boolean)
}