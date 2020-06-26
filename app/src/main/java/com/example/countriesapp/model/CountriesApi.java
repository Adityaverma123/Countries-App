package com.example.countriesapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountriesApi {
    @GET("DevTides/countries/master/countriesV2.json")
        //A Type of observable that rxjava generates
        //Observable that emits only value and then finishes
    Single<List<CountryModel>> getCountries();
    //If we don't have end point already or you don't have url already
    //@GET
    //Single(Object)getObject(@Url String urlString)

}