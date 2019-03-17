package com.magicbus.data;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.magicbus.data.entries.CityResponse;
import com.magicbus.data.network.ApiInterface;
import com.magicbus.data.network.RetrofitInstance;

import java.util.List;

public class Repository implements DataStructure{
    private static Repository repository;
    ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
    public static Repository getRepository(){
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    public void register(String firstName, String lastName, String address, String email, String mobile, String password, final OnRegisterCallBack onRegisterCallBack) {



        final Call<String> register = apiInterface.register(firstName, lastName, address, email, mobile, password);

        register.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {



                String registerResponse = response.body();


                Log.d("Retrofit", registerResponse);

                onRegisterCallBack.onRegisterReceived(registerResponse);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d("Retrofit", t.getMessage());
            }
        });
    }
    public void getCities(final OnCityCallBack onCityCallBack) {

        final Call<CityResponse> cityResponseCall = apiInterface.getCity();
            cityResponseCall.enqueue(new Callback<CityResponse>() {
                @Override
                public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                    CityResponse cityResponse = response.body();
                    onCityCallBack.onCityReceived(cityResponse.getCities());
                    Log.d("Retrofit", cityResponse.toString());
                }








                @Override
                public void onFailure(Call<CityResponse> call, Throwable t) {
                    Log.d("Retrofit", t.getMessage());


                }
            });
    }

    public void routeID(String startpoint_latitude, String startpoint_longitude, String endpoint_latitude, String endpoint_longitude, final RouteIDCallback callback) {

        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<List<RouteID>> routeID = apiInterface.getRouteID(startpoint_latitude, startpoint_longitude, endpoint_latitude, endpoint_longitude);

        routeID.enqueue(new Callback<List<RouteID>>() {
            @Override
            public void onResponse(Call<List<RouteID>> call, Response<List<RouteID>> response) {
                Log.d("RouteID Response", response.body().toString());
//                callback.routeIDCallback(response);
            }

            @Override
            public void onFailure(Call<List<RouteID>> call, Throwable t) {
                Log.e("Route ID Response", t.getMessage());
            }
        });
    }

}
