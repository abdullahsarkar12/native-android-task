package com.example.nativeandroidtask.data.model

import com.google.gson.annotations.SerializedName
data class User (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val userName: String,
    @SerializedName("email")
    val email: String,
){
    override fun toString(): String {
        return name
    }
}