package com.example.yashoda.patientinfoapplication2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.yashoda.patientinfoapplication2.CommonUtils.handleException;

public class AddingActivity extends AppCompatActivity {
    Connectivity connectivity = new Connectivity();

    Context context = AddingActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        Button btnRegister= findViewById(R.id.btnRegisterOnAdding);
        Button btnBack= findViewById(R.id.btnBackToLoginOnAdding);
        final EditText editTextPName = findViewById(R.id.etAddingPName);
        final EditText editTextSurname = findViewById(R.id.etAddingSurname);
        final EditText editTextEmailAddress = findViewById(R.id.etAddingEmailAddress);
        final EditText editTextPassword = findViewById(R.id.etAddingPassword);
        final EditText editTextID = findViewById(R.id.etAddingID);
        final EditText editTextDOB = findViewById(R.id.etAddingDOB);
        final EditText editTextCellNum = findViewById(R.id.etAddingCellNum);
        final EditText editTextBloodType= findViewById(R.id.etAddingBType);
        final EditText editTextEType= findViewById(R.id.etAddingEType);
        final EditText editTextEName= findViewById(R.id.etAddingEName);
        final EditText editTextENum= findViewById(R.id.etAddingENum);

        createRegisterButton(btnRegister, editTextPName, editTextSurname, editTextEmailAddress, editTextPassword,editTextID,editTextDOB,editTextCellNum,editTextBloodType,
                editTextEType,editTextEName,editTextENum);
        createBackToLoginButton(btnBack);
    }

    private void createRegisterButton(Button btnReg,final EditText editTextPName,final EditText editTextSurname,final EditText editTextEmailAddress, final EditText editTextPassword,
                                      final EditText editTextID,final EditText editTextDOB,final EditText editTextCellNum,final EditText editTextBloodType,final EditText editTextEType,
                                      final EditText editTextEName,final EditText editTextENum) {

        try {
            btnReg.setOnClickListener(new View.OnClickListener()
            {
                final String PName = editTextPName.getText().toString();
                final String Surname = editTextSurname.getText().toString();
                final String emailAddress = editTextEmailAddress.getText().toString();
                final String password = editTextPassword.getText().toString();
                final String IDNum = editTextID.getText().toString();
                final Date date1= (Date) new SimpleDateFormat("dd/MM/yyyy").parse(editTextDOB.getText().toString());
                final String cellNum = editTextCellNum.getText().toString();
                final String BType = editTextBloodType.getText().toString();
                final String EType = editTextEType.getText().toString();
                final String EName = editTextEName.getText().toString();
                final String ENum = editTextENum.getText().toString();
                @Override
                public void onClick(View v)
                {
                    //Register();
                    startActivity(new Intent(context,ViewingActivity.class));
                }
            });
        } catch (final ParseException e) {
            runOnUiThread(new Runnable() {
                public void run() {
                    handleException(context, e, "Please enter valid Date");
                }
            });
        }
    }

    /*private String Register() {
        ArrayList<Patient> data = new ArrayList<>();

        ResultSet rs = getResultSet(getRegisterQuery(emailAddress, password));

        data = new ArrayList<>();
        while (rs.next())
        {
            //data.add(rs.getInt( 1),null, null, rs.getString(2),rs.getString(3), null, null, null, null, null);
        }
    }*/

    private String getRegisterQuery(String emailAddress, String password) {
        return "INSERT INTO FROM PATIENT P WHERE P.EMAILADDRESS = '" + emailAddress + "' AND P.PASSWORD = '" + password + "'";
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
