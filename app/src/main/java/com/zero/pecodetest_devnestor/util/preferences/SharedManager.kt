package com.zero.pecodetest_devnestor.util.preferences

import android.content.Context
import android.content.SharedPreferences

class SharedManager(context: Context) {

    companion object {
        private const val APP_SHARED_PREFS_KEY = "Pecode_test_DevNestor"

        private const val COUNT_ITEMS = "COUNT_ITEMS"
    }
    private val preferences: SharedPreferences =
        context.getSharedPreferences(APP_SHARED_PREFS_KEY, Context.MODE_PRIVATE)

    var countItems: Int
        get() = preferences.getInt(COUNT_ITEMS, 1)
        set(value) = preferences.edit().putInt(COUNT_ITEMS, value).apply()
}