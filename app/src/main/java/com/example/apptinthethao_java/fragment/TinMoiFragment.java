package com.example.apptinthethao_java.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.PostAdapter;
import com.example.apptinthethao_java.model.Post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class TinMoiFragment extends Fragment {
    private ArrayList<Post> postArrayList;
    private ListView listViewTinMoi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tin_moi, container, false);
        listViewTinMoi = view.findViewById(R.id.listViewTinMoi);

        LoadDataTinMoi();

        return view;
    }
    private void LoadDataTinMoi() {
        Connection conn = null;
        Statement st = null;

        postArrayList = new ArrayList<>();

        try{
            //STEP 2: Register JDBC driver
            Class.forName("org.postgresql.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:postgresql://ec2-52-71-231-37.compute-1.amazonaws.com:5432/d3pbrmjh5hgsb9",
                    "cuefzkwkvdoncc", "3b5a03ed19cb22bbb695c2b1763d6a7035e2beb9e97221330a9e209500e1c3b8");
            Log.d("quan", "fsdfsdf");
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            st = conn.createStatement();
            String query = "select post.post_id, post.post_title, post.post_img, post.post_create_time, post.post_view \n" +
                    "from post\n" +
                    "order by post.post_create_time desc";

            ResultSet rs = st.executeQuery(query);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id = rs.getInt(1);


                String title = rs.getString(2);
                String img = rs.getString(3);
                String create_time = rs.getString(4);
                Toast.makeText(getContext(), img, Toast.LENGTH_SHORT).show();

                Post post = new Post(id, title, img, create_time);
                postArrayList.add(post);
                PostAdapter postAdapter = new PostAdapter(getContext(), postArrayList);
                listViewTinMoi.setAdapter(postAdapter);
                listViewTinMoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getContext(), String.valueOf(post.getPost_id()), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            //STEP 6: Clean-up environment
            rs.close();
            st.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally
        {
            //finally block used to close resources
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }
    }
}