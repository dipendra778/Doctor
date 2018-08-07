package com.example.dipak.doctor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dipak.doctor.Register.RegisterActivity;


public class SigninActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Name = findViewById(R.id.etName);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.btnLogin);



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=Name.getText().toString();
                String password= Password.getText().toString();
                if((name.equals("dipak")) && (password.equals("12345")))
                {
                    Intent intent = new Intent(SigninActivity.this, RegisterActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
}