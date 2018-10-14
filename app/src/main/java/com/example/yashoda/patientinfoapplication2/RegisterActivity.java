package com.example.yashoda.patientinfoapplication2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.ArrayList;

import static com.example.yashoda.patientinfoapplication2.Connectivity.getResultSet;

public class RegisterActivity extends AppCompatActivity {
    Connectivity connectivity = new Connectivity();

    Context context = RegisterActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnReg= findViewById(R.id.btnRegisterOnRegister);
        Button btnBackToLogin= findViewById(R.id.btnBackToLogin);
        final EditText editTextEmailAddress = findViewById(R.id.etRegisterEmail);
        final EditText editTextPassword = findViewById(R.id.etRegisterPass);

        connectivity.execute("");
    }



}
