package com.example.yashoda.patientinfoapplication2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yashoda.patientinfoapplication2.tables.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.yashoda.patientinfoapplication2.CommonUtils.handleException;

public class AddingActivity extends AppCompatActivity {
    Connectivity connectivity = new Connectivity();

    Context context = AddingActivity.this;

    ProgressDialog progressDialog;

    EditText etPName;
    EditText etSurname;
    EditText etEmailAddress;
    EditText etPassword;
    EditText etIDNumber;
    EditText etDateOfBirth;
    EditText etCellNumber;
    EditText etBloodType;
    EditText etEmergencyType;
    EditText etEmergencyName;
    EditText etEmergencyNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        Button btnRegister= findViewById(R.id.btnRegisterOnAdding);
        Button btnBack= findViewById(R.id.btnBackToLoginOnAdding);

        findViews();

        createRegisterButton(btnRegister, etPName, etSurname, etEmailAddress, etPassword, etIDNumber, etDateOfBirth, etCellNumber, etBloodType,
                etEmergencyType, etEmergencyName, etEmergencyNumber);
        createBackToLoginButton(btnBack);
    }

    private void findViews() {
        etPName = findViewById(R.id.etAddingPName);
        etSurname = findViewById(R.id.etAddingSurname);
        etEmailAddress = findViewById(R.id.etAddingEmailAddress);
        etPassword = findViewById(R.id.etAddingPassword);
        etIDNumber = findViewById(R.id.etAddingID);
        etDateOfBirth = findViewById(R.id.etAddingDOB);
        etCellNumber = findViewById(R.id.etAddingCellNum);
        etBloodType = findViewById(R.id.etAddingBType);
        etEmergencyType = findViewById(R.id.etAddingEType);
        etEmergencyName = findViewById(R.id.etAddingEName);
        etEmergencyNumber = findViewById(R.id.etAddingENum);
    }

    private void createRegisterButton(Button btnReg,final EditText editTextPName,final EditText editTextSurname,final EditText editTextEmailAddress, final EditText editTextPassword,
                                final EditText editTextID,final EditText editTextDOB,final EditText editTextCellNum,final EditText editTextBloodType,final EditText editTextEType,
                                final EditText editTextEName,final EditText editTextENum) {
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pName = editTextPName.getText().toString();
                final String surname = editTextSurname.getText().toString();
                final String emailAddress = editTextEmailAddress.getText().toString();
                final String password = editTextPassword.getText().toString();
                final String iDNumber = editTextID.getText().toString();
                final Date dateOfBirth;
                try {
                    dateOfBirth = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(editTextDOB.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                final String cellNumber = editTextCellNum.getText().toString();
                final String bloodType = editTextBloodType.getText().toString();
                final String emergencyType = editTextEType.getText().toString();
                final String emergencyName = editTextEName.getText().toString();
                final String emergencyNumber = editTextENum.getText().toString();
                progressDialog = ProgressDialog.show(context,
                        "Logging in",
                        "Please be patient....", false);
                new Thread(new Runnable() {
                    public void run()
                    {
                        try {
                            Register(pName, surname, emailAddress, password, iDNumber, dateOfBirth, cellNumber, bloodType, emergencyType, emergencyName, emergencyNumber);
                        }
                        catch (final Exception e)
                        {
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

    private String Register(String pName, String surname, String emailAddress, String password, String iDNumber, Date dateOfBirth, String cellNumber, String bloodType,
                            String emergencyType, String emergencyName, String emergencyNumber) throws SQLException
    {
       /* ResultSet rs;
        rs = connectivity.insertUpdateOrDelete(getPatientQuery(pName, surname, emailAddress, password, iDNumber, dateOfBirth, cellNumber, bloodType));
        rs.rowin
        rs = connectivity.insertUpdateOrDelete(getEmergencyQuery(emergencyType, emergencyName, emergencyNumber));
        return rs;*/
    }

    private String getPatientQuery(String pName, String surname, String emailAddress, String password, String iDNumber, Date dateOfBirth, String cellNumber, String bloodType)
    {
        return "INSERT INTO FROM PATIENT P WHERE P.PATIENTNAME = '"+ pName + "' P.SURNAME = '" + surname + "' P.EMAILADDRESS = '" + emailAddress + "' AND P.PASSWORD = '" + password +
                "' P.IDNUMBER = '" + iDNumber + "' P.DATEOFBIRTH = '" + dateOfBirth + "' P.CELLNUMBER = '" + cellNumber + "' P.BLOODTYPE = '" + bloodType +
                "' P.CONTACTTYPE = '" + "'";
    }

    private String getEmergencyQuery(String emergencyType, String emergencyName, String emergencyNumber)
    {
        return "INSERT INTO EMERGENCY E WHERE E.CONTACTTYPE = '" + emergencyType + "' E.NAME = '" + emergencyName + "' E.CELLNUMBER = '" + emergencyNumber + "'";
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

}
