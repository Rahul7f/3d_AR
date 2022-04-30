package com.rsin.js_api_ar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ShowDetails extends AppCompatActivity {
    ImageView imageView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        imageView = findViewById(R.id.imageView);
        ArrayList<String> list = getIntent().getStringArrayListExtra("list");
        listView = findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<String>(ShowDetails.this, android.R.layout.simple_list_item_1, list));
        String value = getIntent().getStringExtra("VALUE");
        if (value.equals("camera")) {
            try {
                imageView.setImageBitmap(getIntent().getParcelableExtra("bitmap"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                imageView.setImageURI(getIntent().getParcelableExtra("uri"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (list.get(i))
                if (list.get(i).toLowerCase(Locale.ROOT).equals("moon"))
                {
                    // object avaliable
                    Intent intent = new Intent(getApplicationContext(),View3DActivity.class);
                    intent.putExtra("OBEJCT",list.get(i));
                    startActivity(intent);
                }
                else {
                    getAlertDialog("sorry this object is not available");
                }
                Toast.makeText(ShowDetails.this, list.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getAlertDialog(String message) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Close", (dialog, id) -> {
                    dialog.dismiss();
                });
        AlertDialog alert = builder.create();
        alert.setTitle("Error");
        alert.show();
    }

}