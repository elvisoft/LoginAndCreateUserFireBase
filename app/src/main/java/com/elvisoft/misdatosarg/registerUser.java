package com.elvisoft.misdatosarg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registerUser extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference dbref;
    private String pass;
    private String email, name;
    private EditText etxtName;
    private EditText etxtPass;
    private EditText etxtEmail;
    private Button btnRegistrarNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference();
        etxtPass = findViewById(R.id.editTextTextPassword);
        etxtEmail = findViewById(R.id.editTextTextEmailAddress);
        etxtName = findViewById(R.id.editTextTextPersonName);
        btnRegistrarNew=findViewById(R.id.btnregistrar);

        btnRegistrarNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=etxtEmail.getText().toString();
                pass=etxtPass.getText().toString();
                name=etxtName.getText().toString();
                if(!email.isEmpty() && !pass.isEmpty())
                {registerUser();}
                else  {Toast.makeText(registerUser.this,"Debe rellenar todos los datos!",Toast.LENGTH_LONG).show();}
            }
        });

    }

    private void registerUser() {

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            String id = mAuth.getCurrentUser().getUid();
                            Map<String,Object> map=new HashMap<>();
                            map.put("name",name);
                            map.put("email",email);
                            map.put("password",pass);

                            dbref.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task2) {
                                    if (task2.isSuccessful()){
                                        //llamar actividad dashboard user
                                        Toast.makeText(registerUser.this,"El Usuario se registro correctamente!",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(registerUser.this,UserDashboardActivity.class));
                                        finish();
                                    }
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(registerUser.this,"No se pudo registrar el Usuario",Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }
}