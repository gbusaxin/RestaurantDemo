package org.gbu.restaurant.utils

fun validatePhone(phone: String): Boolean{
    return phone.isNotBlank() && phone.length== 12
}

fun validatePassword(password: String): Boolean{
    return password.isNotBlank() // TODO implement it
}