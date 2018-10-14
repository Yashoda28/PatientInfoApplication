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

        createRegisterButton(btnReg, editTextEmailAddress, editTextPassword);
        createBackToLoginButton(btnBackToLogin);

        connectivity.execute("");
    }

    private void createRegisterButton(Button btnReg,final EditText editTextEmailAddress, final EditText editTextPassword) {

        btnReg.setOnClickListener(new View.OnClickListener()
        {
            final String emailAddress = editTextEmailAddress.getText().toString();
            final String password = editTextPassword.getText().toString();
            @Override
            public void onClick(View v)
            {


                startActivity(new Intent(context,AddingActivity.class));
            }
        });
    }

    private void Register(String emailAddress, String password) throws Exception {
        ResultSet rs = getResultSet(getRegisterQuery(emailAddress, password));
        ArrayList<String> data;

        data = new ArrayList<>();
        while (rs.next())
        {
             //data.add(rs.getInt( 1),null, null, rs.getString(2),rs.getString(3), null, null, null, null, null);
        }
    }

    private void createBackToLoginButton(Button btnBackToLogin) {
        btnBackToLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,LogInActivity.class));
            }
        });
    }

    private String getRegisterQuery(String emailAddress, String password) {
        return "INSERT INTO FROM PATIENT P WHERE P.EMAILADDRESS = '" + emailAddress + "' AND P.PASSWORD = '" + password + "'";
    }

}
