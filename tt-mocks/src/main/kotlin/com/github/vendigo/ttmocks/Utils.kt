package com.github.vendigo.ttmocks

val chars = ('A'..'Z') + ('0'..'9')

fun generateRandomId(): String {
    return (1..8).map { chars.random() }.joinToString("")
}