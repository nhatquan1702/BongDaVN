package com.example.apptinthethao_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.apptinthethao_java.activity.CauLacBoActivity;
import com.example.apptinthethao_java.activity.ChiTietCLBActivity;
import com.example.apptinthethao_java.adapter.CauLacBoAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.util.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaoTranDauSapDienRaActivity extends AppCompatActivity {
    Calendar cal;
    Date dateFinish;
    Date hourFinish;
    private Spinner spinnerDoiNha, spinnerDoiKhach;
    private TextView textViewChonNgay, textViewChonGio;
    private CardView cardViewTao;
    private ArrayList<CauLacBo> cauLacBoArrayList;
    private CauLacBoAdapter cauLacBoAdapter;
    private SimpleAPI simpleAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_tran_dau_sap_dien_ra);
        spinnerDoiNha = findViewById(R.id.spinnerChonDoiNha);
        spinnerDoiKhach = findViewById(R.id.spinnerChonDoiKhach);
        textViewChonNgay = findViewById(R.id.textViewChonNgay);
        textViewChonGio = findViewById(R.id.textViewChonGio);
        cal=Calendar.getInstance();
        SimpleDateFormat dft=null;
        //Định dạng ngày / tháng /năm
        dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate=dft.format(cal.getTime());
        //hiển thị lên giao diện
        textViewChonNgay.setText(strDate);
        //Định dạng giờ phút am/pm
        dft=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime=dft.format(cal.getTime());
        //đưa lên giao diện
        textViewChonGio.setText(strTime);
        //lấy giờ theo 24 để lập trình theo Tag
        dft=new SimpleDateFormat("HH:mm",Locale.getDefault());
        textViewChonGio.setTag(dft.format(cal.getTime()));

        //gán cal.getTime() cho ngày hoàn thành và giờ hoàn thành
        dateFinish=cal.getTime();
        hourFinish=cal.getTime();

        textViewChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        textViewChonGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        LoadSpinner();
    }

    private void LoadSpinner() {
        cauLacBoArrayList = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        simpleAPI = Constants.instance();
        simpleAPI.getListCLB().enqueue(new Callback<ArrayList<CauLacBo>>() {
            @Override
            public void onResponse(Call<ArrayList<CauLacBo>> call, Response<ArrayList<CauLacBo>> response) {
                cauLacBoArrayList = response.body();
                cauLacBoAdapter = new CauLacBoAdapter(cauLacBoArrayList);
                for(int i=0; i<cauLacBoArrayList.size(); i++){
                    arrayList.add(cauLacBoArrayList.get(i).getTenCLB());
                }
                ArrayAdapter adapter = new ArrayAdapter<String>(TaoTranDauSapDienRaActivity.this, android.R.layout.simple_spinner_item, arrayList);
                adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                spinnerDoiNha.setAdapter(adapter);
                spinnerDoiNha.setSelection(0);
                spinnerDoiNha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(TaoTranDauSapDienRaActivity.this, cauLacBoArrayList.get(position).getTenCLB(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinnerDoiKhach.setAdapter(adapter);
                spinnerDoiKhach.setSelection(0);
                spinnerDoiKhach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(TaoTranDauSapDienRaActivity.this, cauLacBoArrayList.get(position).getTenCLB(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<CauLacBo>> call, Throwable t) {
                Toast.makeText(TaoTranDauSapDienRaActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void showDatePickerDialog()
    {
        DatePickerDialog.OnDateSetListener callback=new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                textViewChonNgay.setText(
                        (dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                //Lưu vết lại biến ngày hoàn thành
                cal.set(year, monthOfYear, dayOfMonth);
                dateFinish=cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s=textViewChonNgay.getText()+"";
        String strArrtmp[]=s.split("/");
        int ngay=Integer.parseInt(strArrtmp[0]);
        int thang=Integer.parseInt(strArrtmp[1])-1;
        int nam=Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic=new DatePickerDialog(
                TaoTranDauSapDienRaActivity.this,
                callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày");
        pic.show();
    }
    public void showTimePickerDialog()
    {
        TimePickerDialog.OnTimeSetListener callback=new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view,
                                  int hourOfDay, int minute) {
                //Xử lý lưu giờ và AM,PM
                String s=hourOfDay +":"+minute;
                int hourTam=hourOfDay;
                if(hourTam>12)
                    hourTam=hourTam-12;
                textViewChonGio.setText
                        (hourTam +":"+minute +(hourOfDay>12?" PM":" AM"));
                //lưu giờ thực vào tag
                textViewChonGio.setTag(s);
                //lưu vết lại giờ vào hourFinish
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                hourFinish=cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong TimePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s=textViewChonGio.getTag()+"";
        String strArr[]=s.split(":");
        int gio=Integer.parseInt(strArr[0]);
        int phut=Integer.parseInt(strArr[1]);
        TimePickerDialog time=new TimePickerDialog(
                TaoTranDauSapDienRaActivity.this,
                callback, gio, phut, true);
        time.setTitle("Chọn giờ");
        time.show();
    }
}