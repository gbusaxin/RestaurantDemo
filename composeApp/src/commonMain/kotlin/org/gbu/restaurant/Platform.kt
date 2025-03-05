package org.gbu.restaurant

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform