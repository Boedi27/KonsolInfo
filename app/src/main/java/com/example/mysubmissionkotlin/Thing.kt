package com.example.mysubmissionkotlin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thing(
    val name: String,
    val company: String,
    val consoleType:String,
    val description: String,
    val photo: Int
) : Parcelable
