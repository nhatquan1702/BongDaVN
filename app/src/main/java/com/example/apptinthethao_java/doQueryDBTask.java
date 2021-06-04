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


    public doQueryDBTask(Activity contextParent) {
        this.contextParent = contextParent;
    }

    @Override
    protected ResultSet doInBackground(String... strings) {
        ResultSet mRs = null;
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.postgresql.Driver").newInstance ();

            //STEP 3: Open a connection
            conn = DriverManager.getConnection("jdbc:postgresql://ec2-52-71-231-37.compute-1.amazonaws.com:5432/d3pbrmjh5hgsb9",
                    "cuefzkwkvdoncc", "3b5a03ed19cb22bbb695c2b1763d6a7035e2beb9e97221330a9e209500e1c3b8");
            Log.d("connectOK", "Connecting to database...");
            st = conn.createStatement();
            Log.d("testSt", "Creating statement...");
            rs = st.executeQuery(strings[0]);
//            mRs = rs;
//            rs.close();
//            st.close();
//            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            //Handle errors for JDBC
            Log.d("connect", "connect fail!");
        }
//        finally
//        {
//            //finally block used to close resources
//            try{
//                if(st!=null)
//                    st.close();
//            }catch(SQLException se2){
//            }// nothing we can do
//            try{
//                if(conn!=null)
//                    conn.close();
//            }
//            catch(SQLException se){
//                se.printStackTrace();
//            }//end finally try
//        }
        return rs;
    }

    @Override
    protected void onPostExecute(ResultSet resultSet) {
        super.onPostExecute(resultSet);
        String emailTest = null;
        String pass = null;
        try{
            while (resultSet.next()) {
                //Retrieve by column name
                emailTest = resultSet.getString(1);
                pass = resultSet.getString(2);
//                    role = rs.getInt(3);
                Log.d("accountTest", emailTest + " " + pass);
            }
        } catch (SQLException e) {
            Log.d("e", "fail Query");
            e.printStackTrace();
        }
    }
}
