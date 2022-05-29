package com.example.messager.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.messager.MessageActivity;
import com.example.messager.model.Message;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (intent == null){
            Log.d("INFO", "Null");
        }
        else if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            Object pdu = ((Object[]) bundle.get("pdus"))[0];
            SmsMessage[] smsMessage = new SmsMessage[1];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                smsMessage[0] = SmsMessage.createFromPdu((byte[]) pdu, format);
            }
            else{
                smsMessage[0] = SmsMessage.createFromPdu((byte[]) pdu);
            }
            MessageActivity.vMessage.add(new Message(smsMessage[0].getMessageBody(), false));
            MessageActivity.messageAdapter.notifyDataSetChanged();
        }
    }
}