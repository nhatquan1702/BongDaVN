package com.example.apptinthethao_java.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.activity.CauLacBoActivity;
import com.example.apptinthethao_java.activity.ChiTietCLBActivity;
import com.example.apptinthethao_java.adapter.ChiTietCLBAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.example.apptinthethao_java.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCauThuFragment extends Fragment {
    private ArrayList<CauThu_DoiHinh> cauThu_doiHinhArrayList;
    private ChiTietCLBAdapter chiTietCLBAdapter;
    private ListView listViewDSCauThu;
    private SimpleAPI simpleAPI;
    private View view;
    private CauLacBoActivity cauLacBoActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_cau_thu, container, false);
        listViewDSCauThu = view.findViewById(R.id.listViewDSCauThu);
        cauThu_doiHinhArrayList = new ArrayList<>();
        ChiTietCLBActivity chiTietCLBActivity = (ChiTietCLBActivity) getActivity();
        String id_clb = chiTietCLBActivity.getId_clb();
        LoadDSCauThu(id_clb);
        return view;
    }

    private void LoadDSCauThu(String id_clb) {
        simpleAPI = Constants.instance();
        simpleAPI.getListCauThu(id_clb).enqueue(new Callback<ArrayList<CauThu_DoiHinh>>() {
            @Override
            public void onResponse(Call<ArrayList<CauThu_DoiHinh>> call, Response<ArrayList<CauThu_DoiHinh>> response) {
                cauThu_doiHinhArrayList = response.body();
                chiTietCLBAdapter = new ChiTietCLBAdapter(cauThu_doiHinhArrayList);
                listViewDSCauThu.setAdapter(chiTietCLBAdapter);
                listViewDSCauThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getContext(), cauThu_doiHinhArrayList.get(position).getIdCauThu(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<CauThu_DoiHinh>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}