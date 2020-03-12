package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText ptEmail, ptPassword;
    Button btnSignUp;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ptEmail = findViewById(R.id.ptEmail);
        ptPassword = findViewById(R.id.ptPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        preferences = getSharedPreferences("Userinfo", 0);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ptEmailValue = ptEmail.getText().toString();
                String ptPasswordValue = ptPassword.getText().toString();
                if(ptEmailValue.length()>1){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("ptEmail", ptEmailValue);
                    editor.putString("ptPassword", ptPasswordValue);
                    editor.apply();
                    Toast.makeText(getApplicationContext(),
                            "Daftar Sukses!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "Isi setiap kolom yang ada!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void emptyField(){
        ptEmail.setText("");
        ptPassword.setText("");
    }
}
