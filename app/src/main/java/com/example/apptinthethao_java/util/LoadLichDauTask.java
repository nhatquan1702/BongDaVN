package com.example.apptinthethao_java.util;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.TranDau;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadLichDauTask extends AsyncTask<Void, Integer, ArrayList<Object>> {
    private SimpleAPI simpleAPI;
    public AsyncResponse delegate = null;
    ArrayList<String> ngayDauStringArrayList = new ArrayList<>();
    //set date current
    ArrayList<Object> mData = new ArrayList<>();


    @Override
    protected ArrayList<Object> doInBackground(Void... voids) {



        Log.d("mData", String.valueOf(mData.size()));
        return mData;
    }

    @Override
    protected void onPostExecute(ArrayList<Object> objects) {
        delegate.processFinish(objects);
    }
}
