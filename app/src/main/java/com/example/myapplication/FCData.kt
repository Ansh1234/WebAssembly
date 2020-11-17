package com.example.myapplication

import java.io.Serializable

class FCData : Serializable {
    var cap = 0
    var resetTime: Long = 0
    var firstImpressionTime: Long = 0

    override fun toString(): String {
        return "FCData{cap=$cap, resetTime=$resetTime, firstImpressionTime=$firstImpressionTime}"
    }
}