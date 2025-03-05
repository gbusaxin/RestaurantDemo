package org.gbu.restaurant.utils

fun String.encrypt() = this.onEach { it.plus(9) }
fun String.decrypt() = this.onEach { it.minus(9) }
