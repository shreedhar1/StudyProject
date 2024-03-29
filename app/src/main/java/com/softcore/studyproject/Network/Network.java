package com.softcore.studyproject.Network;


import com.softcore.studyproject.AppUtils.AppConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    Retrofit retrofit;
    static ApiSercvices apiSercvices;
    static Network network;

    Network(){
        if(retrofit==null)
        {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(40, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
             //       .addInterceptor(MainActivity.mChuckInterceptor)
                    .build();

            retrofit=new Retrofit.Builder().baseUrl(AppConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        }
        apiSercvices=retrofit.create(ApiSercvices.class);
    }

    public static Network getNetwork()
    {
        if(network==null)
        {
            network=new Network();
        }
        return network;
    }

    public ApiSercvices getservices()
    {
        return apiSercvices;
    }

}
