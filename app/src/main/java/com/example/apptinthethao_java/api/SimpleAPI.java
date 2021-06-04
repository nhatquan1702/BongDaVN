package com.example.apptinthethao_java.api;

import com.example.apptinthethao_java.model.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SimpleAPI {
    @GET("quan/list_tinmoi")
    Call<ArrayList<Post>> getListTinMoi();
}
