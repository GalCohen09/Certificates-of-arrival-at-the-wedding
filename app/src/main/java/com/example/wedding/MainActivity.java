package com.example.wedding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView waze;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waze = findViewById(R.id.waze);
        RadioGroup yesNo = findViewById(R.id.yes_no);
        RadioButton yesRadioButton = findViewById(R.id.yes);
        RadioButton noRadioButton = findViewById(R.id.no);
        RadioButton checkedRadioButton = (RadioButton)yesNo.findViewById(yesNo.getCheckedRadioButtonId());

        //yes in radio box
        yesRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YesActivity.class);
                startActivity(intent);
            }
        });

        //no in radio box
        noRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.cont)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //print bye
                                Toast.makeText(MainActivity.this, R.string.bye , Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                             //continue
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });

        waze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "waze://?ll=31.988441, 34.767267&navigate=yes";
                startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        });
    }

}