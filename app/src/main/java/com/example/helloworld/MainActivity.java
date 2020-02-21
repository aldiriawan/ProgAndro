package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.Home;
import com.example.helloworld.R;
import com.example.helloworld.SignUp;

public class MainActivity extends AppCompatActivity {

    EditText ptEmail;
    EditText ptPassword;
    Button btnLogin;
    TextView tvDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ptEmail = findViewById(R.id.ptEmail);
        ptPassword = findViewById(R.id.ptPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvDaftar = findViewById(R.id.tvDaftar);

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Login Sukses !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
