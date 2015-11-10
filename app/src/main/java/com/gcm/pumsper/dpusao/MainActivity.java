package com.gcm.pumsper.dpusao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String link ="";
    private String type ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button1); b1.setOnClickListener(this);
        Button b2 = (Button) findViewById(R.id.button2); b2.setOnClickListener(this);
        Button b3 = (Button) findViewById(R.id.button3); b3.setOnClickListener(this);
        Button b4 = (Button) findViewById(R.id.button4); b4.setOnClickListener(this);
        Button b5 = (Button) findViewById(R.id.button5); b5.setOnClickListener(this);
        Button b6 = (Button) findViewById(R.id.button6); b6.setOnClickListener(this);
        Button b7 = (Button) findViewById(R.id.button7); b7.setOnClickListener(this);
        Button b8 = (Button) findViewById(R.id.button8); b8.setOnClickListener(this);
        Button b9 = (Button) findViewById(R.id.button9); b9.setOnClickListener(this);
        Button b10 = (Button) findViewById(R.id.button10); b10.setOnClickListener(this);
        Button b11 = (Button) findViewById(R.id.button11); b11.setOnClickListener(this);
        Button b12 = (Button) findViewById(R.id.button12); b12.setOnClickListener(this);



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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1 : link = "news_api.php?type=na"; type="news"; break;
            case R.id.button2 : link = "data_api.php?type=spt"; type="data"; break;
            case R.id.button3 : link = ""; type=""; break;
            case R.id.button4 : link = ""; type=""; break;
            case R.id.button5 : link = ""; type=""; break;
            case R.id.button6 : link = ""; type=""; break;
            case R.id.button7 : link = ""; type=""; break;
            case R.id.button8 : link = ""; type=""; break;
            case R.id.button9 : link = ""; type=""; break;
            case R.id.button10 : link = ""; type=""; break;
            case R.id.button11 : link = ""; type=""; break;
            case R.id.button12 : link = ""; type=""; break;

        }

        Intent intent = new Intent(this, showlist.class);
        intent.putExtra("link", link);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
