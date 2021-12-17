package com.example.wedding;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class YesActivity extends Activity {
    LinearLayout layoutScrollView;
    int numOfBtnDin;
    int counterEtFilled =0;
    String sBefore ="", sAfter="";
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes);
        EditText etBtn = findViewById(R.id.numOfP);
        Button continBtn = findViewById(R.id.conti);
        LinearLayout btnL = findViewById(R.id.btnsL);
        Button send1btn = findViewById(R.id.send);
        send1btn.setVisibility(View.GONE);
        layoutScrollView = findViewById(R.id.btnsL1);
        setHightScrollView();

        continBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                String numOfBtn = etBtn.getText().toString();

                if (!numOfBtn.equals("") && !numOfBtn.equals("0")) {
                    numOfBtnDin = Integer.parseInt(numOfBtn);
                    send1btn.setVisibility(View.VISIBLE);

                    if (numOfBtnDin > 0) {
                        ArrayList<String> meal = new ArrayList<String>();

                        btnL.removeAllViews();

                        for (int i = 0; i < numOfBtnDin; i++) {

                            EditText e = new EditText(YesActivity.this);
                            Spinner s = new Spinner(YesActivity.this);
                            s.setAdapter(null); //continue btn
                            LinearLayout l = new LinearLayout(YesActivity.this);

                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            e.setLayoutParams(lp);
                            s.setLayoutParams(lp);
                            l.setOrientation(LinearLayout.HORIZONTAL);

                            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(YesActivity.this, R.array.meal, android.R.layout.simple_spinner_item);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(adapter);

                            e.setHint(R.string.name);
                            e.getHint();

                            e.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }

                            });
                            s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                   if (parent.getItemAtPosition(position).toString().matches(getString(R.string.vegan)) && flag==false ) {
                                        flag = true;
                                    }

                                    else if ((!parent.getItemAtPosition(position).toString().matches(getString(R.string.vegan))) && flag==true) {
                                        Toast.makeText(YesActivity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                                    e.addTextChangedListener(textwacher);
                            l.addView(e);
                            l.addView(s);
                            btnL.addView(l);
                        }
                    }
                }
                else{
                    Toast.makeText(YesActivity.this, R.string.enternum, Toast.LENGTH_SHORT).show();
                }
            }
        });


        send1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int childCount = btnL.getChildCount();
                if (childCount == counterEtFilled){
                    Intent intent = new Intent(YesActivity.this, carpoolActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(YesActivity.this, R.string.names, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void setHightScrollView() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        int heightScrollView = screenHeight / 2;

        layoutScrollView.getLayoutParams().height = heightScrollView;
        layoutScrollView.requestLayout();
    }

    private TextWatcher textwacher = new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            sBefore = s.toString();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            sAfter = s.toString();
            if (sBefore.equals("") && !sAfter.equals("")){
                counterEtFilled++;
            }
            else if (!sBefore.equals("") && sAfter.equals("")){
                counterEtFilled--;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
