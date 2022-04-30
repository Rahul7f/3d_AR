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
    ImageView imageView;
    TextView textView;
    Button button;
    private final static int CAMERA_CODE = 111;
    private final static int READ_CODE = 222;
    private final static int WRITE_CODE = 333;
    ActivityResultLauncher<Intent> cameraLauncher;
    ActivityResultLauncher<Intent> galleryLauncher;
    InputImage inputImage;
    ImageLabeler labeler;
    ArrayList<String> list;
    Intent intent;

    void processImage() {
        labeler.process(inputImage).addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
            @Override
            public void onSuccess(List<ImageLabel> imageLabels) {
                list = new ArrayList<>();
                for (ImageLabel label : imageLabels) {
                    list.add(label.getText());
                }

                intent.putExtra("list",list.toArray());
                Toast.makeText(ObjectDetection.this, "Call", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);
        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                try {
                    Intent data = result.getData();
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    intent.putExtra("bitmap",bitmap);
                    imageView.setImageBitmap(bitmap);
                    inputImage = InputImage.fromBitmap(bitmap, 0);

                    processImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });
        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                try {
                    Intent data = result.getData();
                    inputImage = InputImage.fromFilePath(ObjectDetection.this, data.getData());
                    imageView.setImageURI(data.getData());
                    intent.putExtra("uri",data.getData());
                    processImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        intent  = new Intent(ObjectDetection.this,ShowDetails.class);
        binding = ActivityObjectDetectionBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        button = binding.button;
        textView = binding.textView;
        imageView = binding.imageView;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] options = {"Camera", "Gallery"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ObjectDetection.this);
                builder
                        .setTitle("Pick An Option")
                        .setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i) {
                                    case 0:
                                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        cameraLauncher.launch(cameraIntent);
                                        break;
                                    case 1:
                                        Intent storageIntent = new Intent();
                                        storageIntent.setType("image/*");
                                        storageIntent.setAction(Intent.ACTION_GET_CONTENT);
                                        galleryLauncher.launch(storageIntent);

                                }
                            }
                        }).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] options = {"Camera", "Gallery"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ObjectDetection.this);
                builder
                        .setTitle("Pick An Option")
                        .setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i) {
                                    case 0:
                                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        cameraLauncher.launch(cameraIntent);
                                        break;
                                    case 1:
                                        Intent storageIntent = new Intent();
                                        storageIntent.setType("image/*");
                                        storageIntent.setAction(Intent.ACTION_GET_CONTENT);
                                        galleryLauncher.launch(storageIntent);

                                }
                            }
                        }).show();
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

    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(ObjectDetection.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(ObjectDetection.this, new String[]{permission}, requestCode);
        }
    }
}