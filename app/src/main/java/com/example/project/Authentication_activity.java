package com.example.project;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Authentication_activity extends AppCompatActivity {
    TextView ebt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_activity);

        ebt=findViewById(R.id.timej);

        Calendar calendar=Calendar.getInstance();

        String da= DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());


        Date c = Calendar.getInstance().getTime();


        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);
        //ebt.setText(formattedDate);
        ebt.setTextColor(Color.parseColor("#F23513"));

        // https://upload.wikimedia.org/wikipedia/commons/"

    }
}
