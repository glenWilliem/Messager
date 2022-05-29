package com.example.messager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.messager.adapter.MessageAdapter;
import com.example.messager.model.Message;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Vector;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvPhone;
    RecyclerView rvMessage;
    EditText etMessage;
    FloatingActionButton fabSend;
    public static Vector<Message> vMessage;
    public static MessageAdapter messageAdapter;
    SmsManager smsManager;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        tvPhone = findViewById(R.id.tv_phone);
        rvMessage = findViewById(R.id.rv_message);
        etMessage = findViewById(R.id.et_message);
        fabSend = findViewById(R.id.fab_send);

        vMessage = new Vector<>();
        vMessage.add(new Message("Text From Me", true));
        vMessage.add(new Message("Text from other",false));

        phone = getIntent().getStringExtra("phone");
        tvPhone.setText(phone);

        fabSend.setOnClickListener(this);

        messageAdapter = new MessageAdapter(this, vMessage);
        rvMessage.setAdapter(messageAdapter);
        rvMessage.setLayoutManager(new LinearLayoutManager(MessageActivity.this));

        smsManager = SmsManager.getDefault();

        checkUserPermission();

    }

    private void checkUserPermission() {
        int sendPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (sendPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},1);
        }
        int receivePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if (receivePermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS},1);
        }
        int readPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        if (readPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},1);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == fabSend){

            String message = etMessage.getText().toString();
            if(message.isEmpty()){
                return;
            }

            smsManager.sendTextMessage(phone,null,message,null,null);
            vMessage.add(new Message(message,true));
            messageAdapter.notifyDataSetChanged();
            etMessage.setText("");
        }
    }
}