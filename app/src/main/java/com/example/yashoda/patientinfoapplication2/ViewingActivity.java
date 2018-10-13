package com.example.yashoda.patientinfoapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing);

        Button btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewingActivity.this,UpdatingActivity.class));
            }
        });

        Button btnFees=(Button)findViewById(R.id.btnViewFees);
        btnFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewingActivity.this,ViewFeesActivity.class));
            }
        });
    }
}
