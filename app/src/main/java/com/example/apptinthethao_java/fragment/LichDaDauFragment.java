package com.example.apptinthethao_java.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.LichDauAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.model.TranDau;
import com.example.apptinthethao_java.util.AsyncResponse;
import com.example.apptinthethao_java.util.Constants;
import com.example.apptinthethao_java.util.LoadLichDauTask;
import com.example.apptinthethao_java.view.LastView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichDaDauFragment extends Fragment implements LastView {

    private SwipeRefreshLayout mSwipeRefresh;
    private ProgressBar mProgressBar;
    private SimpleAPI simpleAPI;
    private ArrayList<Object> mData;
    private ArrayList<String> ngayDauStringArrayList;
    private ArrayList<CauLacBo> CLB;
    public String strDate;
    LichDauAdapter adapter;
    RecyclerView mRecyclerView;

    public LichDaDauFragment() {
        // Required empty public constructor
    }

    public static LichDaDauFragment newInstance() {
        LichDaDauFragment fragment = new LichDaDauFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lich_da_dau, container, false);
        mRecyclerView = rootView.findViewById(R.id.rv_last_match);
        mSwipeRefresh = rootView.findViewById(R.id.swipe_refresh);
        mProgressBar = rootView.findViewById(R.id.progress_bar);
        mData = new ArrayList<>();
        ngayDauStringArrayList = new ArrayList<>();
        LoadDataLichDau();
        HideLoading();
        return rootView;
    }

    private void LoadDataLichDau(){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        strDate = "'" + formatter.format(date) + "'";

        simpleAPI = Constants.instance();
        simpleAPI.getNgayDaDau(strDate).enqueue(new Callback<ArrayList<Object>>() {
            @Override
            public void onResponse(Call<ArrayList<Object>> call, Response<ArrayList<Object>> response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response.body().toArray());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i< Objects.requireNonNull(jsonArray).length(); i++) {

                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ngayDauStringArrayList.add(jsonObject.get("date_match").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("ngayDau",ngayDauStringArrayList.toString());
                int temp = 0;
                for(int i = 0;i<ngayDauStringArrayList.size();i++)
                {
                    Log.d("match_date",ngayDauStringArrayList.get(i));
                    strDate = "'" + ngayDauStringArrayList.get(i) + "'";
                    simpleAPI = Constants.instance();
//                    mData.add(ngayDauStringArrayList.get(i));
                    temp = i;
                    int finalTemp = temp;
                    simpleAPI.getLichTranDau(strDate).enqueue(new Callback<ArrayList<Object>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Object>> call, Response<ArrayList<Object>> response) {

                            JSONArray jsonArray = null;
                            try {
                                jsonArray = new JSONArray(response.body().toArray());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            for(int i = 0; i< Objects.requireNonNull(jsonArray).length(); i++) {

                                try {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    // chuyển chuỗi về đúng dạng ngày
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date parsedDate = sdf.parse(jsonObject.get("match_happen_time").toString());
                                    SimpleDateFormat print = new SimpleDateFormat("HH:mm");
                                    TranDau tranDau = new TranDau();

                                    tranDau.setMatch_time(print.format(parsedDate));
                                    tranDau.setClb_home_name(jsonObject.get("clb_home_name").toString());
                                    tranDau.setClb_guess_name(jsonObject.get("clb_guess_name").toString());
                                    tranDau.setMatch_result(jsonObject.get("match_result").toString());

                                    Log.d("match_parse", tranDau.getMatch_result());
                                    // lấy logo đội nhà
                                    simpleAPI = Constants.instance();
                                    simpleAPI.getChiTietCLB(jsonObject.get("clb_home_name").toString()).enqueue(new Callback<ArrayList<CauLacBo>>() {
                                        @Override
                                        public void onResponse(Call<ArrayList<CauLacBo>> call, Response<ArrayList<CauLacBo>> response) {
                                            CLB = new ArrayList<>();
                                            CLB = response.body();
                                            tranDau.setLogo_home_url(CLB.get(0).getLink());
                                            Log.d("home_logo",tranDau.getClb_home_name() + " " + tranDau.getLogo_home_url());
                                        }

                                        @Override
                                        public void onFailure(Call<ArrayList<CauLacBo>> call, Throwable t) {
                                            Log.d("failCall","lich tran dau: " + t.toString());
                                            t.printStackTrace();
                                        }
                                    });
                                    simpleAPI.getChiTietCLB(jsonObject.get("clb_guess_name").toString()).enqueue(new Callback<ArrayList<CauLacBo>>() {
                                        @Override
                                        public void onResponse(Call<ArrayList<CauLacBo>> call, Response<ArrayList<CauLacBo>> response) {
                                            CLB = new ArrayList<>();
                                            CLB = response.body();
                                            tranDau.setLogo_guess_url(CLB.get(0).getLink());
                                        }

                                        @Override
                                        public void onFailure(Call<ArrayList<CauLacBo>> call, Throwable t) {
                                            Log.d("failCall","lich tran dau: " + t.toString());
                                            t.printStackTrace();
                                        }
                                    });
                                    mData.add(tranDau);
                                } catch (JSONException | ParseException e) {
                                    e.printStackTrace();
                                    Log.d("fail", e.toString());
                                }
                            }
                            mData.add(ngayDauStringArrayList.get(finalTemp));
                            adapter = new LichDauAdapter(getContext(),mData);
                            mRecyclerView.setAdapter(adapter);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        }

                        @Override
                        public void onFailure(Call<ArrayList<Object>> call, Throwable t) {
                            Log.d("failCall","lich tran dau: " + t.toString());
                            t.printStackTrace();
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Object>> call, Throwable t) {
                Log.d("failCall",t.toString());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void ShowLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void HideLoading() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void ShowLastList() {

    }
}