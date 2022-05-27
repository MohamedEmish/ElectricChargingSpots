package com.amosh.common

class AppMessage(
    val id: Int = INFO,
    var message: String = "",
) {
    companion object {
        const val FAILURE = 0
        const val SUCCESS = 2
        const val INFO = 3
    }

    fun getMessageColor(): Int = when (id) {
        FAILURE -> android.R.color.holo_red_dark
        SUCCESS -> android.R.color.holo_green_dark
        else ->  android.R.color.system_accent1_300
    }
}