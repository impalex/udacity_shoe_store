package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// NOTE What is "images"?.. Not mentioned anywhere, ignore it for now.
@Parcelize
data class Shoe(var name: String, var size: Double, var company: String, var description: String,
                val images: List<String> = mutableListOf()) : Parcelable