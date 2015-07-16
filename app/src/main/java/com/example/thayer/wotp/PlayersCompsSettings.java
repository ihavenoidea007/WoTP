package com.example.thayer.wotp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class PlayersCompsSettings extends Activity {
RadioButton[] hrads= new RadioButton[6];
    RadioButton[] crads= new RadioButton[6];
Integer totalmax = 6;
    Integer total = 0 ;
    Integer humans = 0;
    Integer comps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_comps_settings);
        hrads[0] = (RadioButton) findViewById(R.id.radioButton);
        hrads[1] = (RadioButton) findViewById(R.id.radioButton2);
        hrads[2] = (RadioButton) findViewById(R.id.radioButton3);
        hrads[3] = (RadioButton) findViewById(R.id.radioButton4);
        hrads[4] = (RadioButton) findViewById(R.id.radioButton5);
        hrads[5] = (RadioButton) findViewById(R.id.radioButton6);

        crads[0] = (RadioButton) findViewById(R.id.radioButton8);
        crads[1] = (RadioButton) findViewById(R.id.radioButton9);
        crads[2] = (RadioButton) findViewById(R.id.radioButton10);
        crads[3] = (RadioButton) findViewById(R.id.radioButton11);
        crads[4] = (RadioButton) findViewById(R.id.radioButton12);
        crads[5] = (RadioButton) findViewById(R.id.radioButton13);

        for (int i = 0; i < 6; i++){
            final int finalI = i;
            hrads[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    humans = finalI +1;
                    total = 0;

                    total = comps+humans;
                    if (total > 6){
                        hrads[total-7].performClick();
                        Toast.makeText(getApplicationContext(), "No More Than 6 Players", Toast.LENGTH_LONG).show();

                    }
                }
            });
            final int finalI1 = i;
            crads[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comps = finalI1 + 1;
                    total= 0;
                    total = comps+humans;
                    if (total > 6){
                        crads[total-7].performClick();
                          Toast.makeText(getApplicationContext(), "No More Than 6 Players", Toast.LENGTH_LONG).show();

                    }
                }
            });

            Button startbutton = (Button) findViewById(R.id.button3);
            startbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    writesettings();
                    Intent intent = new Intent(PlayersCompsSettings.this, Bluetooth.class);
                    startActivity(intent);


                    finish();

                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_players_comps_settings, menu);
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
    void writesettings(){
        File Settings = new File(getFilesDir() + "Game.settings");


        try {
            Settings.createNewFile();


            Settings.setReadable(true);
            Settings.setWritable(true);
            FileWriter fw = new FileWriter(Settings);
            BufferedWriter bw = new BufferedWriter(fw);

           bw.write("humans " + humans.toString());
            bw.newLine();
            bw.write("comps " + comps.toString());
            bw.newLine();
            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
