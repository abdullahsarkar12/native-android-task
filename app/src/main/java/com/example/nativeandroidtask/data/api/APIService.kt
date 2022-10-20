package com.example.nativeandroidtask.data.api

import com.example.nativeandroidtask.data.model.Todo
import com.example.nativeandroidtask.data.model.User
import io.reactivex.Single
import retrofit2.http.GET

interface APIService {

    @GET("users")
    fun users(): Single<List<User>>

    @GET("todos")
    fun todos(): Single<List<Todo>>
}