package com.rsin.js_api_ar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import com.rsin.js_api_ar.databinding.ActivityObjectDetectionBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObjectDetection extends AppCompatActivity {
    ActivityObjectDetectionBinding binding;
    private final static int CAMERA_CODE = 111;
    private final static int READ_CODE = 222;
    private final static int WRITE_CODE = 333;
    ActivityResultLauncher<Intent> cameraLauncher;
    ActivityResultLauncher<Intent> galleryLauncher;
    InputImage inputImage;//for google ml kit
    ImageLabeler labeler;
    ArrayList<String> list;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityObjectDetectionBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        //initialization START
        labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS); //google image detection or ml kit
        intent = new Intent(ObjectDetection.this, ShowDetails.class);//to start show detail

        //to get Data (Image)
        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                try {
                    Intent data = result.getData();
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    intent.putExtra("bitmap", bitmap);
                    intent.putExtra("VALUE", "camera");

                    inputImage = InputImage.fromBitmap(bitmap, 0);

                    processImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });//get image from camera
        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                try {
                    Intent data = result.getData();
                    inputImage = InputImage.fromFilePath(ObjectDetection.this, data.getData());
                    intent.putExtra("uri", data.getData());
                    intent.putExtra("VALUE", "gallery");
                    processImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });//get image from gallery
        //initialization END

        //Linking Buttons With Action START

        //FROM CAMERA
        binding.cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraLauncher.launch(cameraIntent);
            }
        });
        //FROM GALLERY
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent storageIntent = new Intent();
                storageIntent.setType("image/*");
                storageIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryLauncher.launch(storageIntent);
            }
        });
        //WIKITUDE
        binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ObjectDetection.this, MainActivity.class);
                intent1.putExtra("PATH","file:///android_asset/test_image_on_target/index.html");
                startActivity(intent1);
            }
        });

        binding.cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ObjectDetection.this, MainActivity.class);
                intent1.putExtra("PATH","file:///android_asset/tracking_2d_solar_system/index.html");
                startActivity(intent1);
            }
        });
        binding.cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ObjectDetection.this, MainActivity.class);
                intent1.putExtra("PATH","file:///android_asset/ModelOnTarget/index.html");
                startActivity(intent1);
            }
        });
        binding.cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ObjectDetection.this, MainActivity.class);
                intent1.putExtra("PATH","file:///android_asset/sample_video/index.html");
                startActivity(intent1);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkPermission(Manifest.permission.CAMERA, CAMERA_CODE);
        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, READ_CODE);
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    //checking Permissions and Accepting If not
    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(ObjectDetection.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(ObjectDetection.this, new String[]{permission}, requestCode);
        }
    }
    //getting labels and start the show details activity
    void processImage() {
        labeler.process(inputImage).addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
            @Override
            public void onSuccess(List<ImageLabel> imageLabels) {
                list = new ArrayList<>();
                for (ImageLabel label : imageLabels) {
                    list.add(label.getText());
                }
                intent.putExtra("list", list);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }
}