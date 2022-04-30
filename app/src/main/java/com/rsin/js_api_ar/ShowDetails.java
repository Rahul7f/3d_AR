package com.rsin.js_api_ar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowDetails extends AppCompatActivity {
    ImageView imageView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        imageView = findViewById(R.id.imageView);
        String[] list  = getIntent().getStringArrayExtra("list");
        listView.setAdapter(new ArrayAdapter<String>(ShowDetails.this, android.R.layout.simple_list_item_1,list));

        try {
            imageView.setImageBitmap(getIntent().getParcelableExtra("bitmap"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            imageView.setImageBitmap(getIntent().getParcelableExtra("uri"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}