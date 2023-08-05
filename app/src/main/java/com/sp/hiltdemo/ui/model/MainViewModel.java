package com.sp.hiltdemo.ui.model;


import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sp.hiltdemo.network.ApiResponse;
import com.sp.hiltdemo.repository.Repository;

import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;


public class MainViewModel extends ViewModel {
    public Repository repository;
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();

    @ViewModelInject
    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ApiResponse> getResponse() {
        return responseLiveData;
    }

//    @SuppressLint("CheckResult")
//    public void getRouteList(GetRouteListRequest getRouteListRequest) {
//        repository.executeGetRouteListAPI(getRouteListRequest)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
//                .subscribe(
//                        result -> responseLiveData.setValue(ApiResponse.success(result)),
//                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
//                );
//
//    }

    public void getDataList() {
        repository.executeGetDataList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                );
    }

}