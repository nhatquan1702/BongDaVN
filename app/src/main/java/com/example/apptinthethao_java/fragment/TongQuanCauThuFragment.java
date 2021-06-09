package com.example.apptinthethao_java.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.activity.ChiTietCauThuActivity;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauThuDetail;
import com.example.apptinthethao_java.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TongQuanCauThuFragment extends Fragment {
    private View view;
    private ImageView imgCauThu;
    private SimpleAPI simpleAPI;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tong_quan_cau_thu, container, false);

        ChiTietCauThuActivity chiTietCauThuActivity = (ChiTietCauThuActivity) getActivity();
        String player_id = chiTietCauThuActivity.getPlayer_id();
        imgCauThu = view.findViewById(R.id.imgViewCauThu);
        loadData(player_id);
        return view;
    }

    private void loadData(String player_id){
        simpleAPI = Constants.instance();
        simpleAPI.getThongTinCauThu(player_id).enqueue(new Callback<ArrayList<CauThuDetail>>() {
            @Override
            public void onResponse(Call<ArrayList<CauThuDetail>> call, Response<ArrayList<CauThuDetail>> response) {
                ArrayList<CauThuDetail> cauThuDetailArrayList = response.body();
                CauThuDetail cauThuDetail = cauThuDetailArrayList.get(0);
                Log.d("Array:", cauThuDetail.getTenCauThu());
                Picasso.get()
                        .load(cauThuDetail.getImgCauThu())
                        .placeholder(R.drawable.gallery)
                        .error(R.drawable.gallery)
                        .into(imgCauThu);
                ((TextView) view.findViewById(R.id.txtTenPlayer)).setText(cauThuDetail.getTenCauThu());
                ((TextView) view.findViewById(R.id.txtPlayerClub)).append(cauThuDetail.getClb_name());
                ((TextView) view.findViewById(R.id.txtAssist)).append(String.valueOf(cauThuDetail.getAssist()));
                ((TextView) view.findViewById(R.id.txtGoal)).append(String.valueOf(cauThuDetail.getGoal()));
                ((TextView) view.findViewById(R.id.txtDOB)).append(cauThuDetail.getDob());
                ((TextView) view.findViewById(R.id.txtRaThay)).append(String.valueOf(cauThuDetail.getIn()));
                ((TextView) view.findViewById(R.id.txtDaChinh)).append(String.valueOf(cauThuDetail.getMain()));
                ((TextView) view.findViewById(R.id.txtQuocTich)).append(String.valueOf(cauThuDetail.getNationality()));
                ((TextView) view.findViewById(R.id.txtVaoCho)).append(String.valueOf(cauThuDetail.getOut()));
                ((TextView) view.findViewById(R.id.txtFoot)).append(String.valueOf(cauThuDetail.getPlayer_foot()));
                ((TextView) view.findViewById(R.id.txtPlayerHeight)).append(cauThuDetail.getPlayer_height());
                ((TextView) view.findViewById(R.id.txtTheDo)).append(String.valueOf(cauThuDetail.getRed()));
                ((TextView) view.findViewById(R.id.txtSoAo)).append(String.valueOf(cauThuDetail.getSoAo()));
                ((TextView) view.findViewById(R.id.txtDuBi)).append(String.valueOf(cauThuDetail.getSub()));
                ((TextView) view.findViewById(R.id.txtPosition)).append(cauThuDetail.getViTri());
                ((TextView) view.findViewById(R.id.txtTheVang)).append(String.valueOf(cauThuDetail.getYellow()));
            }

            @Override
            public void onFailure(Call<ArrayList<CauThuDetail>> call, Throwable t) {
                imgCauThu.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.icon_team));
            }
        });

    }
}
