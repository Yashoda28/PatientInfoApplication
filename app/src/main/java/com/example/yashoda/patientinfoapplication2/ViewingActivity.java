package com.example.yashoda.patientinfoapplication2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yashoda.patientinfoapplication2.tables.Emergency;
import com.example.yashoda.patientinfoapplication2.tables.Patient;

import java.sql.ResultSet;
import java.util.Date;

import static com.example.yashoda.patientinfoapplication2.CommonUtils.handleException;

public class ViewingActivity extends AppCompatActivity {

    Connectivity connectivity = new Connectivity();

    Context context = ViewingActivity.this;

    ProgressDialog progressDialog;

    TextView pName;
    TextView surname;
    TextView idNum;
    TextView dob;
    TextView cellNumber;
    TextView bloodType;
    TextView eType;
    TextView eName;
    TextView eNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing);

        findViews();

        Button btnUpdate = findViewById(R.id.btnUpdateOnViewing);
        Button btnFees = findViewById(R.id.btnViewFeesOnViewing);

        createUpdateButton(btnUpdate);
        createViewFeesButton(btnFees);

        progressDialog = ProgressDialog.show(context,
                "Logging in",
                "Please be patient....", false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    populateViews();
                    progressDialog.cancel();
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

    private void findViews() {
        pName = findViewById(R.id.txtViewingPName);
        surname = findViewById(R.id.txtViewingSurname);
        idNum = findViewById(R.id.txtViewingID);
        dob = findViewById(R.id.txtViewingDOB);
        cellNumber = findViewById(R.id.txtViewingCellNum);
        bloodType = findViewById(R.id.txtViewingBType);
        eType = findViewById(R.id.txtViewingEType);
        eName = findViewById(R.id.txtViewingEName);
        eNum = findViewById(R.id.txtViewingENum);
    }

    private void populateViews() throws Exception {
        ResultSet rs = connectivity.getResultSet(getPatientViewingQuery());
        rs.next();
        Patient patient = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                rs.getString(6), rs.getDate(7), rs.getString(8), rs.getString(9));

        String pName1 = patient.getPatientName();
        pName.setText(pName1);
        String sur = patient.getSurname();
        surname.setText(sur);
        String idN = patient.getiDNumber();
        idNum.setText(idN);
        Date dateOB = patient.getDateofBirth();
        dob.setText(dateOB.toString());
        String cN = patient.getCellNumber();
        cellNumber.setText(cN);
        String bT = patient.getBloodType();
        bloodType.setText(bT);

        rs = connectivity.getResultSet(getEmergencyViewingQuery());
        rs.next();
        Emergency emergency = new Emergency(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));

        String eT = emergency.getContactType();
        eType.setText(eT);
        String eN = emergency.getName();
        eName.setText(eN);
        String eNU = emergency.getCellNumber();
        eNum.setText(eNU);
    }

    private void createViewFeesButton(Button btnFees) {
        btnFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ViewFeesActivity.class));
            }
        });
    }

    private void createUpdateButton(Button btnUpdate) {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, UpdatingActivity.class));
            }
        });
    }

    private String getPatientViewingQuery() {
        return "SELECT * FROM PATIENT";
    }

    private String getEmergencyViewingQuery() {
        return "SELECT * FROM EMERGENCY";
    }
}
