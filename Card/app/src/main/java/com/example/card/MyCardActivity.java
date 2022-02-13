package com.example.card;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyDatabaseHelper myDB;
    private ArrayList<String> card_id, card_name, card_company, card_field, card_phone, card_email;
    private CustomAdapter customAdapter;
    private Button btn_add, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycard);

        btn_add = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.recyclerView);


        myDB = new MyDatabaseHelper(MyCardActivity.this);
        card_id = new ArrayList<>();
        card_name = new ArrayList<>();
        card_company = new ArrayList<>();
        card_field = new ArrayList<>();
        card_phone = new ArrayList<>();
        card_email = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MyCardActivity.this, MyCardActivity.this, card_id, card_name, card_company, card_field, card_phone, card_email);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyCardActivity.this));


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyCardActivity.this, MakePopUpActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                card_id.add(cursor.getString(0));
                card_name.add(cursor.getString(1));
                card_company.add(cursor.getString(2));
                card_field.add(cursor.getString(3));
                card_phone.add(cursor.getString(4));
                card_email.add(cursor.getString(5));
            }
        }
    }



}
