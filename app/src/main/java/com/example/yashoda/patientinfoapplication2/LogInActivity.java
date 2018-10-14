package com.example.yashoda.patientinfoapplication2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.ResultSet;

import javax.security.auth.login.LoginException;

import static com.example.yashoda.patientinfoapplication2.CommonUtils.handleException;
import static com.example.yashoda.patientinfoapplication2.Connectivity.getResultSet;
import static java.lang.Thread.sleep;

public class LogInActivity extends AppCompatActivity {
    Connectivity connectivity = new Connectivity();

    Context context = LogInActivity.this;

    ProgressDialog progressDialog;

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
        connectivity.execute("");
    }

    private void createLoginBtn(Button btnLogin, final EditText editTextEmailAddress, final EditText editTextPassword) {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailAddress = editTextEmailAddress.getText().toString();
                final String password = editTextPassword.getText().toString();
                progressDialog = ProgressDialog.show(context,
                        "Logging in",
                        "Please be patient....", false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            LogIn(emailAddress, password);
                        } catch (final Exception e) {
                            progressDialog.cancel();
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    handleException(context, e, e.getMessage());
                                }
                            });
                        }
                    }
                }).start();
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

    private void LogIn(String emailAddress, String password) throws Exception {
        ResultSet rs = getResultSet(getLoginQuery(emailAddress, password));
        rs.next();
        if (rs.getInt("RECORDCOUNT") == 1) {
            goToInfoView();
        } else {
            throw new LoginException("Incorrect Log In Details");
        }
    }

    private void goToInfoView() throws InterruptedException {
        startActivity(new Intent(LogInActivity.this, ViewingActivity.class));
        sleep(1000);
        progressDialog.cancel();
    }

    private String getLoginQuery(String emailAddress, String password) {
        return "SELECT COUNT(*) AS RECORDCOUNT FROM PATIENT P WHERE P.EMAILADDRESS = '" + emailAddress + "' AND P.PASSWORD = '" + password + "'";
    }
}
