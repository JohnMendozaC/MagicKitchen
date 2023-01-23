package com.john.magickitchen.infraestructure.network

import android.util.Base64


object Jarvis {

    const val baseUrl = "aHR0cDovL2RlbW80MzQyNjY2Lm1vY2thYmxlLmlvLw=="

    fun getDecodeData(dataEncode: String): String {
        val decodedBytes = Base64.decode(dataEncode, Base64.DEFAULT)
        return String(decodedBytes)
    }
}