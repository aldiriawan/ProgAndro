package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText ptEmail;
    EditText ptPassword;
    Button btnLogin;
    TextView tvDaftar;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ptEmail = findViewById(R.id.ptEmail);
        ptPassword = findViewById(R.id.ptPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvDaftar = findViewById(R.id.tvDaftar);

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ptEmailValue = ptEmail.getText().toString();
                String ptPasswordValue = ptPassword.getText().toString();

                String emailTerdaftar = preferences.getString("ptEmail", "");
                String passwordTerdaftar = preferences.getString("ptPassword", "");

                if(ptEmailValue.equals(emailTerdaftar) && ptPasswordValue.equals(passwordTerdaftar)){
                    Toast.makeText(getApplicationContext(),
                            "Login Sukses !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "Silahkan masukkan Email dan Password anda", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
