package com.example.yashoda.patientinfoapplication2;

import android.annotation.SuppressLint;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

import static android.os.StrictMode.*;

class Connectivity {
    //Declaration of connection parameters
    static private final String ip = "143.128.146.30";
    static private final String db = "hon01";
    static private final String un = "hon01";
    static private final String password = "12q26";
    static private final String ConnURL = "jdbc:jtds:sqlserver://" + ip + "/" + ";db=" + db + ";user=" + un + ";password=" + password + ";"; //Connection String

    static private final ThreadPolicy policy = new ThreadPolicy.Builder().permitAll().build();

    private Connection connection;

    @SuppressLint("NewApi")
    private Connection connect() throws Exception {
        setThreadPolicy(policy);
        connectToDB();//Instantiating JDBC Drivers
        return connection;
    }

    private void connectToDB() throws Exception {
        connection = DriverManager.getConnection(ConnURL);
    }

    public Connection getConnection() throws Exception {
        return connect();
    }
}
