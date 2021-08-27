package com.example.apptinthethao_java.util;

import com.example.apptinthethao_java.api.RetrofitInstance;
import com.example.apptinthethao_java.api.SimpleAPI;

public class Constants {
    private Constants(){}
    //public static final String BASE_URL = "https://nhatquanapi.herokuapp.com/"; //cũ
    //public static final String BASE_URL = "http://127.0.0.1:5000/"; //local
    public static final String BASE_URL = "https://nhatquan-app.herokuapp.com/"; //mới
    public static SimpleAPI instance() {
        return RetrofitInstance.GetClient(BASE_URL).create(SimpleAPI.class);
    }
}
