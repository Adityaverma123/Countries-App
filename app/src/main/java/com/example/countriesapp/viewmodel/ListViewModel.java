package com.example.countriesapp.viewmodel;

import android.provider.SyncStateContract;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countriesapp.model.CountriesService;
import com.example.countriesapp.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {
    //Observable
    public MutableLiveData<List<CountryModel>>countries=new MutableLiveData<List<CountryModel>>();
    public MutableLiveData<Boolean>countryLoadError=new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean>loading=new MutableLiveData<Boolean>();
    private CountriesService countriesService=CountriesService.getInstance();
    private CompositeDisposable disposable=new CompositeDisposable();

    //entry point for the view in our viewmodel
    public void refresh(){

        fetchCountries();
    }

    private void fetchCountries()
    {

        loading.setValue(true);
                disposable.add(countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())//happens on new thread so that main thread does not gets block
                .observeOn(AndroidSchedulers.mainThread())//then when response comes we want the communication to be done in main thread
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                    @Override
                    public void onSuccess(List<CountryModel> countryModels) {
                        countries.setValue(countryModels);
                        loading.setValue(false);
                        countryLoadError.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        countryLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
                );

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
