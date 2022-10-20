package com.example.nativeandroidtask.data

import com.example.nativeandroidtask.data.api.APIClient
import com.example.nativeandroidtask.data.app.MainApplication

class DataManager {
    companion object {
        private var mInstance: DataManager? = null

        fun getInstance(): DataManager {
            if (mInstance == null) {
                mInstance = DataManager()
            }
            return mInstance as DataManager
        }
    }

    fun <T> getApiService(service: Class<T>): T {
        return APIClient.buildRetrofit(MainApplication.getInstance()).create(service)
    }
}