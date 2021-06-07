package com.example.apptinthethao_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectDB {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://ec2-52-71-231-37.compute-1.amazonaws.com:5432/d3pbrmjh5hgsb9";

    //  Database credentials
    static final String USER = "cuefzkwkvdoncc";
    static final String PASS = "3b5a03ed19cb22bbb695c2b1763d6a7035e2beb9e97221330a9e209500e1c3b8";

    public static void main(String[] args)
    {
        Connection conn = null;
        Statement st = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("org.postgresql.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:postgresql://ec2-52-71-231-37.compute-1.amazonaws.com:5432/d3pbrmjh5hgsb9",
                    "cuefzkwkvdoncc", "3b5a03ed19cb22bbb695c2b1763d6a7035e2beb9e97221330a9e209500e1c3b8");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            st = conn.createStatement();
            String sql;
            sql = "SELECT * From Match WhERE DATE(match_happen_time) = '2021-05-07' ORDER BY match_happen_time ASC";
            ResultSet rs = st.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                String first = rs.getString(1);
//                String last = rs.getString(2);

                //Display values
                System.out.println("result: " + first);
//                System.out.println(", pass: " + last);
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