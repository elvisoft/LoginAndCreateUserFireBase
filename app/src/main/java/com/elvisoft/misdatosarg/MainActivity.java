package com.elvisoft.misdatosarg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private EditText etxtPass;
    private EditText etxtEmail;
    private Button btnRegistrarsenew, btnlogin;
    private String pass;
    private String email;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtPass = findViewById(R.id.eTextPass);
        etxtEmail = findViewById(R.id.eTextEmail);
        btnRegistrarsenew=findViewById(R.id.btnRegistrarse);
        btnlogin=findViewById(R.id.btnlogin);

        mAuth = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=etxtEmail.getText().toString();
                pass=etxtPass.getText().toString();
                if(!email.isEmpty() && !pass.isEmpty()){
                    loginUser();
                }
                else Toast.makeText(MainActivity.this,"Debe ingresar email y contraseña!",Toast.LENGTH_LONG).show();
            }
        });

        btnRegistrarsenew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,registerUser.class));
                finish();
            }
        });
    }

    private void loginUser(){
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,UserDashboardActivity.class));
                    finish();
                }
                else Toast.makeText(MainActivity.this,"El Usuario y la contraseña no estan registrados!",Toast.LENGTH_LONG).show();
            }
        });
    }

}