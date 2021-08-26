package com.example.apptinthethao_java.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.ItemClickInterface;
import com.example.apptinthethao_java.adapter.MenuItemRecyclerViewAdapter;
import com.example.apptinthethao_java.adapter.ViewPagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    NavigationView navigationView ;
    View headerView ;
    TextView navUsername ;
    Button btnLoginHeader;
    ImageView imgAVTHeader ;
    TextView iconToolbarMenu;
    FlowingDrawer drawerlayout;
    SearchView searchView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView textViewReFresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load dữ liệu tablayout & bắt sự kiện tương ứng mỗi tab với viewpager
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);
        LoadTabLayoutAndViewpager2();


        //xử lý đăng nhập
        navigationView = findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);
        navUsername = headerView.findViewById(R.id.tvHoTenNa_Header);
        btnLoginHeader = headerView.findViewById(R.id.btnHeaderLogin);
        imgAVTHeader = headerView.findViewById(R.id.imgAVT);
        DangNhap();

        //Load menu navigation khi vuốt cạnh trái hoặc click icon tool bar
        iconToolbarMenu = findViewById(R.id.iconToolbarMenu);
        drawerlayout = (FlowingDrawer) findViewById(R.id.drawerlayout);
        searchView = findViewById(R.id.searchView);
        LoadNavigation();
        //Log.d("tncnhan", "Load login done!");
        //Load menu recyclerView bên dưới
        LoadMenuReCyclerView();

        //Xử lý thanh search
        Search();

        textViewReFresh = findViewById(R.id.tvRefresh);
        textViewReFresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadTabLayoutAndViewpager2();
            }
        });
    }

    private void Search() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, SearchPostsActivity.class);
                intent.putExtra("searchText", query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void LoadMenuReCyclerView() {
        RecyclerView recyclerViewMenu = findViewById(R.id.recyclerViewMenu);
        ArrayList<String> arrayListMenuRecyclerview = new ArrayList<>();
        arrayListMenuRecyclerview.add("Bảng xếp hạng");
        arrayListMenuRecyclerview.add("Lịch thi đấu");
        arrayListMenuRecyclerview.add("Câu lạc bộ");
        arrayListMenuRecyclerview.add("Cầu thủ");

        MenuItemRecyclerViewAdapter menuItemRecyclerViewAdapter = new MenuItemRecyclerViewAdapter(arrayListMenuRecyclerview, getApplicationContext());
        recyclerViewMenu.setAdapter(menuItemRecyclerViewAdapter);
        menuItemRecyclerViewAdapter.setOnClickItemRecyclerView(new ItemClickInterface() {
            @Override
            public void onClick(View view, int position) {
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(MainActivity.this, BangXepHangActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, LichThiDauActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, CauLacBoActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, ListCauThuActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        recyclerViewMenu.setLayoutManager(new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.HORIZONTAL,false));

    }

    private void LoadNavigation() {
        iconToolbarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.toggleMenu();
            }
        });

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String role = sharedPreferences.getString("role", "-1");
        Menu menu = navigationView.getMenu();
        MenuItem adminItem = menu.findItem(R.id.ADMIN);
        MenuItem editItem = menu.findItem(R.id.CD);
        //Log.d("tncnhan", check);
        adminItem.setVisible(role.equals("1"));
        editItem.setVisible(!role.equals("-1"));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ADMIN: {
                        Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                        startActivity(intent);
                        return true;
                    }
                    case R.id.H: {
                        Toast.makeText(getApplicationContext(), "Quản lí bài viết!", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    case R.id.CS : {
                        Toast.makeText(getApplicationContext(), "Chia sẻ!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        String shareBody="Facebook: https://www.facebook.com/nhatquan99\n\nYoutube: https://www.youtube.com/channel/UCIyQPdxwhuLv0GaEXI5kOjA";
                        String shareSub="https://www.youtube.com/channel/UCIyQPdxwhuLv0GaEXI5kOjA";
                        intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                        intent.putExtra(Intent.EXTRA_TEXT,shareBody);
                        startActivity(Intent.createChooser(intent, "Share Using"));
                        return true;
                    }
                    case R.id.GY : {
                        Toast.makeText(getApplicationContext(), "Góp ý!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, GopYActivity.class);
                        startActivity(intent);
                        return true;
                    }
                    case R.id.CD : {
                        Intent intent = new Intent(MainActivity.this, EditUserActivity.class);

                        intent.putExtra("user_id", sharedPreferences.getString("email", "user"));
                        intent.putExtra("pass", sharedPreferences.getString("password", "pass"));
                        intent.putExtra("mode", "selfEdit");
                        intent.putExtra("role", role);
                        startActivity(intent);
                        return true;
                    }
                    case R.id.HT : {
                        Toast.makeText(getApplicationContext(), "Liên hệ!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LienHeActivity.class);
                        startActivity(intent);
                        return true;
                    }
                    case R.id.DGUD : {
                        Toast.makeText(getApplicationContext(), "Đánh giá ứng dụng!", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    case R.id.DKSD : {
                        Toast.makeText(getApplicationContext(), "Điều khoản sử dụng!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, DieuKhoanActivity.class);
                        startActivity(intent);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void LoadTabLayoutAndViewpager2() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        String[] titleTab = {"Tin mới", "Tin nóng", "Tin phổ biến", "Tin chuyển nhượng"};
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titleTab[position]);
            }
        }).attach();

    }
    // lưu ref các thông tin
    // email
    // password
    // role
    private void DangNhap(){
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String check = sharedPreferences.getString("role", "-1");


        if(check.equals("-1")){//chưa đăng nhập
            navUsername.setVisibility(View.GONE);
            btnLoginHeader.setText("Đăng nhập");
            imgAVTHeader.setImageResource(R.drawable.ic_launcher_foreground);
            btnLoginHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        else{//đã đăng nhập
            //onBackPressed()
            navUsername.setText(sharedPreferences.getString("email", "username"));
            btnLoginHeader.setText("Đăng xuất");
            imgAVTHeader.setImageResource(R.drawable.deappool);
            btnLoginHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    editor = sharedPreferences.edit();

                    editor.remove("email");
                    editor.remove("password");
                    editor.remove("role");

                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}