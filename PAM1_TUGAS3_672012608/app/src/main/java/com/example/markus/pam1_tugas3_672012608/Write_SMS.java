package com.example.markus.pam1_tugas3_672012608;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Write_SMS extends ActionBarActivity implements View.OnClickListener{
    EditText pesan,nomer_hp;
    Button kirim_pesan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write__sms);
        nomer_hp=(EditText)findViewById(R.id.text_alamat);
        pesan=(EditText)findViewById(R.id.text_pesan);

        kirim_pesan=(Button)findViewById(R.id.btn_kirim);
        kirim_pesan.setOnClickListener(this);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_write__sm, menu);
        return true;
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
    public void onClick(View v) {
      sendSMS();
    }
    public void sendSMS(){
       StringBuffer Enskripsi=new StringBuffer();
       StringBuffer Deskripsi=new StringBuffer();

       String nomer_tujuan=nomer_hp.getText().toString();
        String isi=pesan.getText().toString();

        try{

            SmsManager smsManager = SmsManager.getDefault();
            for(int i=0;i<isi.length();i++){
                Enskripsi.append(kripto(isi.toCharArray(),i));
            }

            String balik = String.valueOf(Enskripsi);

//           for(int j=0;j<isi.length();j++){
//               Deskripsi.append(kripto(isi.toCharArray(),j));
//           }

//            String hasil = String.valueOf(Deskripsi);
            smsManager.sendTextMessage(nomer_tujuan, null,balik,null, null);
            Toast.makeText(this, "SMS Sent", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

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
