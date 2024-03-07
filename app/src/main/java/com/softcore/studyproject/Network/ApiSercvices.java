package com.softcore.studyproject.Network;


import com.softcore.studyproject.Model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiSercvices {

    @GET("emp_login")
    Call<List<UserModel>> getLoginDetails(@Query("DB_NAME") String DbName,@Query("UserName") String UserName, @Query("UserPassword") String Password);



}
