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

import javax.xml.namespace.QName;

public class LoginActivity extends AppCompatActivity {

    Button login;
    MaterialButton toSignUp;
    EditText name,password;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        login=findViewById(R.id.singIn);
        toSignUp=findViewById(R.id.toRegister);
        DB=new DBHelper(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=name.getText().toString();
                String password_=password.getText().toString();
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password_)){
                    Toast.makeText(LoginActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserpass=DB.checkusernamepassword(username,password_);
                    if(checkuserpass ==true){
                        Toast.makeText(LoginActivity.this, "Login successfully! ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Incorrect Username or Password!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }
}