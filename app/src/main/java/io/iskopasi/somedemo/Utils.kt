package io.iskopasi.somedemo

import android.util.Log

val String.e
    get() = if (BuildConfig.DEBUG) Log.e("-->", this) else this


val timerMap = mutableMapOf<String, Long>()

// Ignores invoke if it happened faster than @delay milliseconds
inline fun suppress(key: String, delay: Long = 500L, block: () -> Unit) {
    if (timerMap.contains(key)) {
        val lastTimeStamp = timerMap[key] ?: 0L
        val delta = System.currentTimeMillis() - lastTimeStamp

        if (delta > delay) {
            block()
            timerMap[key] = System.currentTimeMillis()
        }
    } else {
        block()
        timerMap[key] = System.currentTimeMillis()
    }
}

inline fun suppressed(key: String, delay: Long = 500L, crossinline block: () -> Unit): () -> Unit =
    {
        suppress(key, delay, block)
    }