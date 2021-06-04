package com.example.apptinthethao_java.api;

import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.example.apptinthethao_java.model.DetailPost;
import com.example.apptinthethao_java.model.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SimpleAPI {
    @GET("quan/list_tinmoi")
    Call<ArrayList<Post>> getListTinMoi();

    @GET("quan/list_baiviethot")
    Call<ArrayList<Post>> getListTinNong();

    @GET("quan/list_tinphobien")
    Call<ArrayList<Post>> getListTinPhoBien();

    @GET("quan/list_tinchuyennhuong")
    Call<ArrayList<Post>> getListTinChuyenNhuong();

    @GET("quan/chitietbaiviet/{post_id}")
    Call<ArrayList<DetailPost>> getDetailPost(@Path("post_id") String id);

    @GET("quan/list_clb")
    Call<ArrayList<CauLacBo>> getListCLB();

    @GET("quan/tatcacauthu_clb/{clb_id}")
    Call<ArrayList<CauThu_DoiHinh>> getListCauThu(@Path("clb_id") String id);
}
