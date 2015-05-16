package com.example.markus.pam1_tugas3_672012608;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class BroadCast_SMS extends BroadcastReceiver {
    public BroadCast_SMS() {
    }

    public static final String SMS_BUNDLE = "pdus";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        Bundle bundle = intent.getExtras();
        if (bundle != null){
            Object[] sms = (Object[]) bundle.get("pdus");
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);
                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                smsMessageStr += "SMS FROM: "+address+"\n";
                smsMessageStr += smsBody + "\n";
            }
            Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();
            try {

                Read_SMS inst = Read_SMS.instance();
                inst.updateList(smsMessageStr);
            }catch (NullPointerException npe){
                npe.printStackTrace();
            }

        }
    }
}
