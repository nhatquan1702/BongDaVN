package com.example.apptinthethao_java.api;

import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.example.apptinthethao_java.model.DetailPost;
import com.example.apptinthethao_java.model.DienBienTranDau;
import com.example.apptinthethao_java.model.Post;
import com.example.apptinthethao_java.model.TranDau;

import java.util.ArrayList;
import java.util.Date;

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

    @GET("quan/chitietclb/{clb_id}")
    Call<ArrayList<CauLacBo>> getChiTietCLB(@Path("clb_id") String id);

    @GET("quan/tatcacauthu_clb/{clb_id}")
    Call<ArrayList<CauThu_DoiHinh>> getListCauThu(@Path("clb_id") String id);

    @GET("quan/trandau/{match_id}")
    Call<ArrayList<DienBienTranDau>> getTranDau(@Path("match_id") String id);

    @GET("quan/trandau_home_main/{match_id}")
    Call<ArrayList<CauThu_DoiHinh>> getDoiHinhChinhDoiNha(@Path("match_id") String id);

    @GET("quan/trandau_home_sub/{match_id}")
    Call<ArrayList<CauThu_DoiHinh>> getDoiHinhDuBiDoiNha(@Path("match_id") String id);

    @GET("quan/trandau_guess_main/{match_id}")
    Call<ArrayList<CauThu_DoiHinh>> getDoiHinhChinhDoiKhach(@Path("match_id") String id);

    @GET("quan/trandau_guess_sub/{match_id}")
    Call<ArrayList<CauThu_DoiHinh>> getDoiHinhDuBiDoiKhach(@Path("match_id") String id);

    @GET("khai/getNgaySapDau/{date}")
    Call<ArrayList<Object>> getNgaySapDau(@Path("date") String date);

    @GET("khai/getNgayDaDau/{date}")
    Call<ArrayList<Object>> getNgayDaDau(@Path("date") String date);

    @GET("khai/getTranDau/{date}")
    Call<ArrayList<Object>> getLichTranDau(@Path("date") String date);
}
