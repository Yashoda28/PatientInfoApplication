package com.example.yashoda.patientinfoapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogInActivity extends AppCompatActivity {
    Connectivity connectivity;

    public LogInActivity() {
        connectivity = new Connectivity();
    }

    Connection connection;
    private static final String FAILED_TO_CONNECT_ERROR_MESSAGE = "Failed to connect, Please check Internet Connection";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnReg = findViewById(R.id.btnReg);
        final EditText editTextEmailAddress = findViewById(R.id.etEmail);
        final EditText editTextPassword = findViewById(R.id.etPass);

        createLoginBtn(btnLogin, editTextEmailAddress, editTextPassword);
        createRegistrationBtn(btnReg);

        connection = getDBConnection();
    }

    private void createLoginBtn(Button btnLogin, final EditText editTextEmailAddress, final EditText editTextPassword) {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = editTextEmailAddress.getText().toString();
                String password = editTextPassword.getText().toString();
                try {
                    LogIn(emailAddress, password);
                } catch (Exception e) {
                    Log(e);
                    Toast.makeText(LogInActivity.this, "Error Logging In", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createRegistrationBtn(Button btnReg) {
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegistration();
            }
        });
    }

    private void goToRegistration() {
        startActivity(new Intent(LogInActivity.this, RegisterActivity.class));
    }

    private void LogIn(String emailAddress, String password) throws SQLException {
        ResultSet rs = getLogInResultSet(emailAddress, password);
        rs.next();
        if (rs.getInt("RECORDCOUNT") == 1) {
            goToInfoView();
        }
        else{
            Toast.makeText(LogInActivity.this, "Incorrect Log In Details", Toast.LENGTH_LONG).show();
        }
    }

    private void goToInfoView() {
        startActivity(new Intent(LogInActivity.this, ViewingActivity.class));
    }

    private ResultSet getLogInResultSet(String emailAddress, String password) throws SQLException {
        String query = getLoginQuery(emailAddress, password);
        Statement sm = connection.createStatement();
        return sm.executeQuery(query);
    }

    private String getLoginQuery(String emailAddress, String password) {
        return "SELECT COUNT(*) as RECORDCOUNT FROM PATIENT P WHERE P.EMAILADDRESS = '" + emailAddress + "' AND P.PASSWORD = '" + password + "'";
    }

    private Connection getDBConnection() {
        try {
            return connectivity.getConnection();
        } catch (Exception e) {
            Log(e);
            Toast.makeText(LogInActivity.this, FAILED_TO_CONNECT_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
            getDBConnection();
            return null;
        }
    }

    private static void Log(Exception se) {
        Log.e(se.getClass().getName(), se.getMessage());
    }
}
