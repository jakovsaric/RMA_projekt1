package com.jakovsaric.footballleaguesapp

data class Competition(
    val id: Int,
    val name: String,
    val area: Area,
    val emblemUrl: String?
)

data class Area(
    val name: String,
    val flag: String?
)
