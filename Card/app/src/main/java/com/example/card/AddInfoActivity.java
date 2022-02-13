package com.example.card;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class AddInfoActivity extends AppCompatActivity {

    private Button btn_addPic, btn_ok, btn_cancel;
    private EditText name_input, company_input, field_input;
    private EditText phone_input, address_input, email_input, phone_input2, printer_input;

    ImageView imgView;
    Uri uri;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinfo);

        name_input = findViewById(R.id.name_input);
        company_input = findViewById(R.id.company_input);
        field_input = findViewById(R.id.field_input);
        phone_input = findViewById(R.id.phone_input);
        email_input = findViewById(R.id.email_input);
        address_input = findViewById(R.id.address_input);
        phone_input2 = findViewById(R.id.phone_input2);
        printer_input = findViewById(R.id.printer_input);


        btn_addPic = findViewById(R.id.btn_addPic);
        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);

        btn_addPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TedPermission.with(getApplicationContext())
                        .setPermissionListener(permissionListener)
                        .setRationaleMessage("사진 및 파일을 저장하기 위하여 접근 권한이 필요합니다.")
                        .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있습니다.")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                launcher.launch(intent);
            }
        });



        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper((AddInfoActivity.this));
                myDB.addCard(name_input.getText().toString().trim(),
                        company_input.getText().toString().trim(),
                        field_input.getText().toString().trim(),
                        Integer.valueOf(phone_input.getText().toString().trim()),
                        email_input.getText().toString().trim());
                Intent intent = new Intent(AddInfoActivity.this, MyCardActivity.class);
                startActivity(intent);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            //Toast.makeText(getApplicationContext(), "권한이 허용됨", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            //Toast.makeText(getApplicationContext(), "권한이 거부됨", Toast.LENGTH_SHORT).show();
        }
    };

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    if (result.getResultCode() == RESULT_OK)
                    {
                        Intent intent = result.getData();
                        uri = intent.getData();
                        imgView.setImageURI(uri);
                    }
                }
            });
}
