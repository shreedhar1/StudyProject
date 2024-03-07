package com.softcore.studyproject.Network;


import com.softcore.studyproject.Model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RemoteRepository {

    public static RemoteRepository repository;

    public static RemoteRepository getRepository(){
        if(repository==null)
        {
            repository=new RemoteRepository();
        }
        return repository;
    }

    public void getlogindetails(String DbName,String username,String password,Callback<List<UserModel>> callback){
        Call<List<UserModel>> call= Network.getNetwork().getservices().getLoginDetails(DbName,username,password);
        call.enqueue(callback);
        System.out.println("kk");
    }

}
