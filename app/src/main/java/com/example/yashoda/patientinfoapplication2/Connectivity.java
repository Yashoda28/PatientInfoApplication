package com.example.yashoda.patientinfoapplication2;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static android.os.StrictMode.*;
import static com.example.yashoda.patientinfoapplication2.CommonUtils.Log;

class Connectivity extends AsyncTask {
    //Declaration of connection parameters
    private static final String ip = "143.128.146.30";
    private static final String db = "hon01";
    private static final String un = "hon01";
    private static final String password = "12q26";
    private static final String ConnURL = "jdbc:jtds:sqlserver://" + ip + "/" + ";db=" + db + ";user=" + un + ";password=" + password + ";"; //Connection String

    private static final String FAILED_TO_CONNECT_ERROR_MESSAGE = "Failed to connect, Please check Internet Connection";

    private static final ThreadPolicy policy = new ThreadPolicy.Builder().permitAll().build();

    private static Connection connection;

    @Override
    protected Object doInBackground(Object[] objects) {
        while (connection == null) {
            connect();
            if(isCancelled()){
                break;
            }
        }
        return null;
    }

    void connect() {
        setThreadPolicy(policy);
        try {
            connection = DriverManager.getConnection(ConnURL);
        } catch (SQLException e) {
            Log(e);
        }
    }

    synchronized ResultSet getResultSet(String query) throws SQLException {
            if(connection != null) {
                Statement sm = connection.createStatement();
                return sm.executeQuery(query);
            }
            throw new SQLException(FAILED_TO_CONNECT_ERROR_MESSAGE);
    }

    synchronized void insertUpdateOrDelete(String query) throws SQLException {
        if(connection != null) {
            Statement sm = connection.createStatement();
            sm.executeQuery(query);
        }
        throw new SQLException(FAILED_TO_CONNECT_ERROR_MESSAGE);
    }

}
