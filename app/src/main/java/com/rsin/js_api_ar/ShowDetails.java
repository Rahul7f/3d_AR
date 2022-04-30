package com.rsin.js_api_ar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

public class ShowDetails extends AppCompatActivity {
    Bitmap bitmap;
    String[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        bitmap = getIntent().getParcelableExtra("bitmap");
        arr = getIntent().getStringArrayExtra("bitmap");
    }
}