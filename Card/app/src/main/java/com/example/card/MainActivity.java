package com.example.card;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_make, btn_want, btn_stack, btn_setting;
    private Uri providerURI;
    private String imageFilePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_make = findViewById(R.id.btn_make);
        btn_want = findViewById(R.id.btn_want);
        btn_stack = findViewById(R.id.btn_stack);
        btn_setting = findViewById(R.id.btn_setting);

        btn_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyCardActivity.class);
                startActivity(intent);
            }
        });

        // want, stack, setting


    }
}