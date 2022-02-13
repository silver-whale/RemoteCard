package com.example.card;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MakePopUpActivity extends AppCompatActivity {
    private Button btn_addInfo, btn_takeCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makepopup);

        btn_addInfo = findViewById(R.id.btn_addInfo);
        btn_takeCard = findViewById(R.id.btn_takeCard);

        btn_addInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MakePopUpActivity.this, AddInfoActivity.class);
                startActivity(intent);
            }
        });

        btn_takeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MakePopUpActivity.this, TakeCardActivity.class);
                startActivity(intent);
            }
        });


    }


}
