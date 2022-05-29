package com.example.messager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etPhone;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();

        btnSend.setOnClickListener(this);

    }

    private void inits() {
    etPhone = findViewById(R.id.et_phone);
    btnSend = findViewById(R.id.btn_send);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSend){
            String phone = etPhone.getText().toString();
            if (phone.isEmpty()){
                Toast.makeText(this, "Input Phone Number", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this,MessageActivity.class);
            intent.putExtra("phone",phone);
            startActivity(intent);
        }
    }
}