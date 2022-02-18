package com.example.card;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, company_input, field_input, phone_input;
    EditText email_input, address_input, phone_input2, printer_input;
    Button update_button, btn_delete;

    String id, name, company, field, phone, email, address, phone2, printer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        company_input = findViewById(R.id.company_input2);
        field_input = findViewById(R.id.field_input2);
        phone_input = findViewById(R.id.phone_input1_2);
        email_input = findViewById(R.id.email_input2);
        address_input = findViewById(R.id.address_input2);
        phone_input2 = findViewById(R.id.phone_input2_2);
        printer_input = findViewById(R.id.printer_input2);

        update_button = findViewById(R.id.update_button);
        btn_delete = findViewById(R.id.btn_delete);
        // First we call this
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                company = company_input.getText().toString().trim();
                field = field_input.getText().toString().trim();
                phone = phone_input.getText().toString().trim();
                email = email_input.getText().toString().trim();
                address = address_input.getText().toString().trim();
                phone2 = phone_input2.getText().toString().trim();
                printer = printer_input.getText().toString().trim();
                myDB.updateData(id, name, company, field, phone, email, address, phone2, printer);
                finish();

            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("company") && getIntent().hasExtra("field") &&
                getIntent().hasExtra("phone") && getIntent().hasExtra("email")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            company = getIntent().getStringExtra("company");
            field = getIntent().getStringExtra("field");
            phone = getIntent().getStringExtra("phone");
            email = getIntent().getStringExtra("email");
            address = getIntent().getStringExtra("address");
            phone2 = getIntent().getStringExtra("phone2");
            printer = getIntent().getStringExtra("printer");

            //Setting Intent Data
            name_input.setText(name);
            company_input.setText(company);
            field_input.setText(field);
            phone_input.setText(phone);
            email_input.setText(email);
            address_input.setText(address);
            phone_input2.setText(phone2);
            printer_input.setText(printer);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("해당 명함을 삭제하시겠습니까?");
        builder.setMessage("삭제한 명함은 복구할 수 없습니다.");
        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });

        builder.create().show();
    }

}