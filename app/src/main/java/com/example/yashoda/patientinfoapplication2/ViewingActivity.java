package com.example.yashoda.patientinfoapplication2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewingActivity extends AppCompatActivity {

    Connectivity connectivity = new Connectivity();

    Context context = ViewingActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing);

        Button btnUpdate= findViewById(R.id.btnUpdateOnViewing);
        Button btnFees= findViewById(R.id.btnViewFeesOnViewing);

        createUpdateButton(btnUpdate);
        createViewFeesButton(btnFees);

        connectivity.execute("");
    }

    private void createViewFeesButton(Button btnFees) {
        btnFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,ViewFeesActivity.class));
            }
        });
        connectivity.execute("");
    }

    private void createUpdateButton(Button btnUpdate) {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,UpdatingActivity.class));
            }
        });
    }
}
