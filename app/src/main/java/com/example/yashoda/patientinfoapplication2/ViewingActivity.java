package com.example.yashoda.patientinfoapplication2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yashoda.patientinfoapplication2.tables.Emergency;
import com.example.yashoda.patientinfoapplication2.tables.Patient;

import java.sql.ResultSet;
import java.util.ArrayList;

import static com.example.yashoda.patientinfoapplication2.CommonUtils.handleException;
import static com.example.yashoda.patientinfoapplication2.Connectivity.getResultSet;
public class ViewingActivity extends AppCompatActivity {

    Connectivity connectivity = new Connectivity();

    Context context = ViewingActivity.this;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing);

        connectivity.execute("");

        Button btnUpdate= findViewById(R.id.btnUpdateOnViewing);
        Button btnFees= findViewById(R.id.btnViewFeesOnViewing);
        final TextView pName = findViewById(R.id.txtViewingPName);
        final TextView surname = findViewById(R.id.txtViewingSurname);
        final TextView idNum = findViewById(R.id.txtViewingID);
        final TextView dob = findViewById(R.id.txtViewingDOB);
        final TextView cellNumber = findViewById(R.id.txtViewingCellNum);
        final TextView bloodType = findViewById(R.id.txtViewingBType);
        final TextView eType = findViewById(R.id.txtViewingEType);
        final TextView eName = findViewById(R.id.txtViewingEName);
        final TextView eNum = findViewById(R.id.txtViewingENum);

        createUpdateButton(btnUpdate);
        createViewFeesButton(btnFees);

        ArrayList<Patient> data = getPatients();

        String pName1 = data.get(0).toString();
        pName.setText(pName1);
        String sur = data.get(0).toString();
        surname.setText(sur);
        String idN = data.get(0).toString();
        idNum.setText(idN);
        String dateOB = data.get(0).toString();
        dob.setText(dateOB);
        String cN = data.get(0).toString();
        cellNumber.setText(cN);
        String bT = data.get(0).toString();
        bloodType.setText(bT);

        /*ArrayList<Emergency> emerg = getEmergency();
        String eT = emerg.get(0).toString();
        eType.setText(eT);
        String eN = emerg.get(0).toString();
        eName.setText(eN);
        String eNU = emerg.get(0).toString();
        eNum.setText(eNU);*/

    }

    @NonNull
    private ArrayList<Patient> getPatients()
    {
        final ArrayList<Patient> data = new ArrayList<>();
        progressDialog = ProgressDialog.show(context,
                "Logging in",
                "Please be patient....", false);
        new Thread(new Runnable() {
            public void run()
            {
                try {
                    addPatientDataToArray(data);
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
        return data;
    }

    private void addPatientDataToArray(ArrayList<Patient> data) throws Exception {
        ResultSet rs = getResultSet(getViewingQuery());
        while (rs.next())
        {
            Patient u1 = new Patient(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getInt(10));
            data.add(u1);
        }
    }

    /*@NonNull
    private ArrayList<Emergency> getEmergency()
    {
        ArrayList<Emergency> emerg = new ArrayList<>();
        try {
            ResultSet rs = getResultSet(getViewingQuery2());
            while (rs.next())
            {
                Emergency u1 = new Emergency(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                emerg.add(u1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emerg;
    }*/

    private void createViewFeesButton(Button btnFees) {
        btnFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,ViewFeesActivity.class));
            }
        });
    }

    private void createUpdateButton(Button btnUpdate) {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,UpdatingActivity.class));
            }
        });
    }

    private String getViewingQuery() {
        return "SELECT * FROM PATIENT";
    }

    /*private String getViewingQuery2() {
        return "SELECT * FROM EMERGENCY";
    }*/
}
