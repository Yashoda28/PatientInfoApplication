package com.example.yashoda.patientinfoapplication2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UpdatingActivity extends AppCompatActivity
{
    Connectivity connectivity = new Connectivity();

    Context context = UpdatingActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updating);

        Button btnUpdate = findViewById(R.id.btnUpdateOnUpdating);
        createUpdateButton(btnUpdate);

        connectivity.execute("");
    }

    private void createUpdateButton(Button btnUpdate) {
        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                startActivity(new Intent(context,AddingActivity.class));
            }
        });
    }
}
