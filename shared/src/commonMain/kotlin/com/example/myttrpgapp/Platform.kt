package com.example.myttrpgapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform