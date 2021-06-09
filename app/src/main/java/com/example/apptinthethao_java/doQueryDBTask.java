package com.example.apptinthethao_java;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class doQueryDBTask extends AsyncTask<String, Integer, ResultSet> {

    Activity contextParent;
    private Connection connection;
    private final String host = "ec2-52-71-231-37.compute-1.amazonaws.com";
    private final String database = "d3pbrmjh5hgsb9";
    private final int port = 5432;
    private final String user = "cuefzkwkvdoncc";
    private final String pass = "3b5a03ed19cb22bbb695c2b1763d6a7035e2beb9e97221330a9e209500e1c3b8";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;


    public doQueryDBTask(Activity contextParent) {
        this.contextParent = contextParent;
    }

    @Override
    protected ResultSet doInBackground(String... strings) {
        this.url = String.format(this.url, this.host, this.port, this.database);

        Statement st;
        ResultSet rs = null;
        try {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, pass);
                status = true;
                System.out.println("connected:" + status);
            } catch (Exception e) {
                status = false;
                System.out.print(e.getMessage());
                e.printStackTrace();
            }
            st = connection.createStatement();
            Log.d("testSt", "Creating statement...");
            rs = st.executeQuery(strings[0]);
        } catch (SQLException e) {
            //Handle errors for JDBC
            Log.d("connect", "connect fail!");
        }
        return rs;
    }

    @Override
    protected void onPostExecute(ResultSet resultSet) {
        super.onPostExecute(resultSet);
        String emailTest = null;
        String pass = null;
        try{
            while (resultSet.next()) {
                emailTest = resultSet.getString(1);
//                pass = resultSet.getString(2);
                Log.d("accountTest", emailTest);
            }
        } catch (SQLException e) {
            Log.d("e", "fail Query");
            e.printStackTrace();
        }
    }
}
