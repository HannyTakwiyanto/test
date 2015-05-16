package com.example.markus.pam1_tugas3_672012608;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Button tulis_sms;
    Button baca_pesan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       baca_pesan=(Button)findViewById(R.id.btn_read);
        tulis_sms=(Button)findViewById(R.id.btn_write);

        baca_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah_baca=new Intent(MainActivity.this,Read_SMS.class);
                startActivity(pindah_baca);
            }
        });
       tulis_sms.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent pindah_write=new Intent(MainActivity.this,Write_SMS.class);
               startActivity(pindah_write);
           }
       });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
