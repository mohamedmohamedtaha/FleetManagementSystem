package com.example.fleetmanagement.utils

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class StringHelperTest {
    lateinit var stringHelper: StringHelper

    @Before
    fun setUp() {
        stringHelper = StringHelper()
    }

    @Test
    fun isPositiveNumber() {
        val check  :Boolean = stringHelper.isPositiveNumber(2)
        assertTrue(check)


    }
}