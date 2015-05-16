package com.example.markus.pam1_tugas3_672012608;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Read_SMS extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private static Read_SMS inst;
    ListView smsListView;
    ArrayAdapter arrayAdapter;
    ArrayList<String> smsMessageList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read__sms);

        smsListView = (ListView) findViewById(R.id.list_sms);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, smsMessageList);
        smsListView.setAdapter(arrayAdapter);
        smsListView.setOnItemClickListener(this);
        refreshSmsInbox();
    }


    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read__sm, menu);
        return true;
    }
    public static Read_SMS instance(){

        return inst;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        try {


            String[] smsMessages = smsMessageList.get(position).split("\n");
            String address = smsMessages[0];
            String smsMessage = "";
            StringBuffer Enskripsi=new StringBuffer();
            StringBuffer Deskripsi=new StringBuffer();

            for (int i = 1; i < smsMessages.length; ++i) {

                smsMessage += smsMessages[i];
                Enskripsi.append(kripto(smsMessage.toCharArray(),i));
            }

            for(int j=0;j<smsMessage.length();j++){
                Deskripsi.append(kripto(smsMessage.toCharArray(),j));
            }
            String smsMessageStr = address + "\n";
            smsMessageStr += smsMessage;
            Toast.makeText(this, smsMessageStr, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void refreshSmsInbox() {
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
        arrayAdapter.clear();
        do {
            String str = "SMS From: " + smsInboxCursor.getString(indexAddress) +
                    "\n" + smsInboxCursor.getString(indexBody) + "\n";
            arrayAdapter.add(str);
        } while (smsInboxCursor.moveToNext());
    }

    public void updateList(String smsMessage){
        arrayAdapter.insert(smsMessage, 0);
        arrayAdapter.notifyDataSetChanged();
    }

    public char kripto(char []data,int j){
        char ascii = ' ';
        int nilai;
        int ROT13=13;
        int karakter=data[j] ;  /*konversi karakter kedalam bentuk desimal */
        if (karakter >='a' && karakter <='z' || karakter >='A' && karakter <='Z') {
            if (karakter >='a' && karakter <='z') {
                if ( (nilai =karakter + ROT13) <= 'z' ) { /*Jika Karakter plus 13 kurang dari z*/
                    ascii = (char) nilai;  /*Chasting kedalam bentuk Karakter*/
                }
                else {
                    nilai =karakter - ROT13; /*Jika Karakter lebih dari z maka dikurangi 13*/
                    ascii = (char) nilai; /*Chasting kedalam bentuk Karakter*/
                }
            }
            else if (karakter >='A' && karakter <='Z')  {
                if ( (nilai =karakter + ROT13) <= 'Z' ) { /*Jika Karakter plus 13 kurang dari z*/
                    ascii = (char) nilai;  /*Chasting kedalam bentuk Karakter*/
                }
                else {
                    nilai =karakter - ROT13; /*Jika Karakter lebih dari z maka dikurangi 13*/
                    ascii = (char) nilai; /*Chasting kedalam bentuk Karakter*/
                }
            }

        }
        return ascii;
    }

}
