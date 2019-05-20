package com.example.mobileid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn,saveData,getData,clear,nxtActivity;
    EditText edt,userID,userName;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "MYDATA";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        edt=findViewById(R.id.edt);
        userID=findViewById(R.id.userID);
        userName=findViewById(R.id.userName);
        saveData=findViewById(R.id.saveData);
        getData=findViewById(R.id.getData);
        nxtActivity=findViewById(R.id.nextActivity);

        nxtActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPreferences.contains(Name)){


                    String st=sharedPreferences.getString(Name,"");
                    if(st.equals(userID.getText().toString()))
                    {
                            startActivity(new Intent(MainActivity.this,TestActivity.class));
                    }

                }
            }
        });
        clear=findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName.setText("");
                userID.setText("");
            }
        });
        sharedPreferences=getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(Name))
        {
            userName.setText(sharedPreferences.getString(Name, ""));

        }
        if (sharedPreferences.contains(Email)) {
            userID.setText(sharedPreferences.getString(Email, ""));

        }
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = userID.getText().toString();
                String e = userName.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Name, n);
                editor.putString(Email, e);
                editor.commit();

            }
        });

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);

                if (sharedPreferences.contains(Name)) {
                    userID.setText(sharedPreferences.getString(Name, ""));
                }
                if (sharedPreferences.contains(Email)) {
                    userName.setText(sharedPreferences.getString(Email, ""));


                }

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String androidId = Settings.Secure.getString(getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                edt.setText("mobile ID="+androidId);


            }
        });
    }
}
