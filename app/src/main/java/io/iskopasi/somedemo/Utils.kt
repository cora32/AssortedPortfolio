package io.iskopasi.somedemo

import android.util.Log

val String.e
    get() = if (BuildConfig.DEBUG) Log.e("-->", this) else this