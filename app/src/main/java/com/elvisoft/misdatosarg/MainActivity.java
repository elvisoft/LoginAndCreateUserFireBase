package com.elvisoft.misdatosarg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private EditText etxtPass;
    private EditText etxtDNI;
    private Button btnRegistrarsenew, btnlogin;
    private String pass;
    private String nameUser;
    private String dni;
    private boolean band=false;
    Usuario Allusers;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    public List<Usuario> listUser = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtPass = findViewById(R.id.txtpass);
        etxtDNI = findViewById(R.id.txtDNI);
        btnRegistrarsenew=findViewById(R.id.btnRegister);
        btnlogin=findViewById(R.id.btnlogin);
        inicializarFirebase();
        obtenerUsersNew();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dni=etxtDNI.getText().toString();
                pass=etxtPass.getText().toString();
                if(!dni.isEmpty() && !pass.isEmpty()){

                    if(existUser(dni,pass)){
                        Toast.makeText(MainActivity.this, "Usuario logueado correctamente!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this, UserDashboardActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("NameUser", nameUser);
                        i.putExtras(bundle);
                        startActivity(i);
                        finish();
                    }
                    else Toast.makeText(MainActivity.this, "Usted no esta registrado!", Toast.LENGTH_LONG).show();
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

    private boolean existUser(String dni, String pass) {
        boolean band2=false;
        for (Usuario usuario : listUser) {
            if(usuario.getDNI().equals(dni)){
                try{
                    String passuser=CodificadorAES.desencriptar(usuario.getPassword());
                    nameUser=usuario.getNombre();
                    if(passuser.equals(pass)){
                        band2=true;
                        break;
                    }
                    else Toast.makeText(this, "Su DNI esta registrado pero no coincide con su contraseña, dirigase a Recuperar Contraseña.", Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(this, "Error Codificacion: "+e.toString(), Toast.LENGTH_LONG).show();
                }

            }
        }
        return band2;
    }

    private void obtenerUsersNew(){
        //listUser.clear();
        databaseReference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot objSnaptshot : snapshot.getChildren()) {
                    Usuario u = objSnaptshot.getValue(Usuario.class);
                    listUser.add(u);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public void obtenerUsuarios() {

       databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot objeto : dataSnapshot.getChildren()) {
                    Allusers = objeto.getValue(Usuario.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void loginUser(){
        /*mAuth.signInWithEmailAndPassword(dni,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,UserDashboardActivity.class));
                    finish();
                }
                else Toast.makeText(MainActivity.this,"El Usuario y la contraseña no estan registrados!",Toast.LENGTH_LONG).show();
            }
        });*/
    }

}