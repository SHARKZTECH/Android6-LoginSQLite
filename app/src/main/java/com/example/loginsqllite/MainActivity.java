package com.example.loginsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    Button signUp;
    MaterialButton toLogin;
    EditText name,password,password1;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toLogin=findViewById(R.id.toLogin);
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        password1=findViewById(R.id.password1);
        signUp=findViewById(R.id.singUp);
        DB=new DBHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=name.getText().toString();
                String password_=password.getText().toString();
                String password1_=password1.getText().toString();

                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password_) || TextUtils.isEmpty(password1_))  {
                    Toast.makeText(MainActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                }else {
                    if(password_.equals(password1_)){
                        Boolean checkuser=DB.checkusername(username);
                        if(checkuser == false){
                            Boolean insert=DB.insertData(username,password_);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity( new Intent(getApplicationContext(),HomeActivity.class));
                            }else {
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Username Already Taken!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Passwords Don`t match!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}