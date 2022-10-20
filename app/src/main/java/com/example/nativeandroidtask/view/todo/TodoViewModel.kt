package com.example.nativeandroidtask.view.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nativeandroidtask.data.DataManager
import com.example.nativeandroidtask.data.api.APIService
import com.example.nativeandroidtask.data.model.Todo
import com.example.nativeandroidtask.data.model.User
import com.example.nativeandroidtask.utils.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TodoViewModel: ViewModel() {

    var mDataManger: DataManager = DataManager.getInstance()
    var mDisposable: CompositeDisposable = CompositeDisposable()

    var todoList = MutableLiveData<List<Todo>>()
    var userList = MutableLiveData<List<User>>()
    var networkState = MutableLiveData<NetworkState>()

    fun getDisposable(): CompositeDisposable {
        return mDisposable
    }

    fun fetchUserInfo(){
    }

    fun fetchTodoInfo(){
    }

    fun getUserList() {
        networkState.value = NetworkState.LOADED
        getDisposable().add(mDataManger.getApiService(APIService::class.java).users()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<User>>() {
                override fun onSuccess(t: List<User>) {
                    networkState.value = NetworkState.LOADED
                    userList.value = t
                }

                override fun onError(e: Throwable) {
                    networkState.value = NetworkState.ERROR
                }
            }))
    }

    fun getTodoList() {
        networkState.value = NetworkState.LOADED
        getDisposable().add(mDataManger.getApiService(APIService::class.java).todos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Todo>>() {
                override fun onSuccess(t: List<Todo>) {
                    networkState.value = NetworkState.LOADED
                    todoList.value = t
                }

                override fun onError(e: Throwable) {
                    networkState.value = NetworkState.ERROR
                }
            }))
    }

    override fun onCleared() {
        mDisposable.dispose()
        super.onCleared()
    }

}