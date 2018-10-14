package com.example.yashoda.patientinfoapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        Button btnUpdate= findViewById(R.id.btnUpdateOnAdding);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddingActivity.this,UpdatingActivity.class));
            }
        });

        Button btnFees= findViewById(R.id.btnViewFeesOnAdding);
        btnFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddingActivity.this,ViewFeesActivity.class));
            }
        });
    }
}
