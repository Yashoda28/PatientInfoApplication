package com.example.yashoda.patientinfoapplication2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yashoda.patientinfoapplication2.tables.Emergency;
import com.example.yashoda.patientinfoapplication2.tables.Patient;

import java.sql.ResultSet;
import java.util.Date;

import static com.example.yashoda.patientinfoapplication2.CommonUtils.handleException;

public class UpdatingActivity extends AppCompatActivity
{
    Connectivity connectivity = new Connectivity();

    Context context = UpdatingActivity.this;

    ProgressDialog progressDialog;

    EditText pName;
    EditText surname;
    EditText idNum;
    EditText dob;
    EditText cellNumber;
    EditText bloodType;
    EditText eType;
    EditText eName;
    EditText eNum;

    int patientID;
    int emergencyID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updating);

        findViews();

        Button btnUpdate = findViewById(R.id.btnUpdateOnUpdating);
        Button btnFees = findViewById(R.id.btnViewFeesOnViewing);

        createUpdateButton(btnUpdate);
        createViewFeesButton(btnFees);

        progressDialog = ProgressDialog.show(context,
                "Logging in",
                "Please be patient....", false);
        new Thread(new Runnable()
        {
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
        pName = findViewById(R.id.etUpdatingPName);
        surname = findViewById(R.id.etUpdatingSurname);
        idNum = findViewById(R.id.etUpdatingID);
        dob = findViewById(R.id.etUpdatingDOB);
        cellNumber = findViewById(R.id.etUpdatingCellNum);
        bloodType = findViewById(R.id.etUpdatingBType);
        eType = findViewById(R.id.etUpdatingEType);
        eName = findViewById(R.id.etUpdatingEName);
        eNum = findViewById(R.id.etUpdatingENum);
    }

    private void populateViews() throws Exception {
        ResultSet rs = connectivity.getResultSet(getPatientViewingQuery());
        rs.next();
        Patient patient = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                rs.getString(6), rs.getDate(7), rs.getString(8), rs.getString(9));

        patientID = patient.getPatientID();
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

        emergencyID = emergency.getEmergencyID();
        String eT = emergency.getContactType();
        eType.setText(eT);
        String eN = emergency.getName();
        eName.setText(eN);
        String eNU = emergency.getCellNumber();
        eNum.setText(eNU);
    }

    private String getPatientViewingQuery() {
        return "SELECT * FROM PATIENT";
    }

    private String getEmergencyViewingQuery() {
        return "SELECT * FROM EMERGENCY";
    }

    private void createViewFeesButton(Button btnFees) {
        btnFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ViewFeesActivity.class));
            }
        });
    }

    private void createUpdateButton(Button btnUpdate)
    {
        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(context,AddingActivity.class));
            }
        });
    }

    private String getPatientUpdatingQuery(int patientID, String pName, String surname, String iDNumber, Date dateOfBirth, String cellNumber, String bloodType) {
        return "UPDATE PATIENT P SET P.PATIENTNAME = '"+ pName + "' P.SURNAME = '" + surname + "' P.IDNUMBER = '" + iDNumber + "' P.DATEOFBIRTH = '" +
                dateOfBirth + "' P.CELLNUMBER = '" + cellNumber + "' P.BLOODTYPE = '" + bloodType + "' P.CONTACTTYPE = '" + "' WHERE P.PATIENTID = '" + patientID +"'";
    }

    private String getEmergencyUpdatingQuery(int emergencyID, String emergencyType, String emergencyName, String emergencyNumber) {
        return "UPDATE EMERGENCY E SET E.CONTACTTYPE = '" + emergencyType + "' E.NAME = '" + emergencyName + "' E.CELLNUMBER = '" + emergencyNumber +
                "' WHERE E.EMERGENCYID = '" +  emergencyID +"'";
    }
}
