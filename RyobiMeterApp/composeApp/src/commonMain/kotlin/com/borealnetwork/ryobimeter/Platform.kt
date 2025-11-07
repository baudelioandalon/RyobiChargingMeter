package com.borealnetwork.ryobimeter

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform