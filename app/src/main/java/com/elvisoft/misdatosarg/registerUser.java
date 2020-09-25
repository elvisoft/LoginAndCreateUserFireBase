package com.elvisoft.misdatosarg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.FirebaseApp;

import java.util.UUID;

public class registerUser extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference dbref;
    private String pass;
    private String email, name;
    private EditText etxtName;
    private EditText etxtPass;
    private EditText etxtEmail;
    private EditText extxdni;
    private EditText etxtpassrepit;
    private EditText etxtrespuestasecret;
    private Button btnRegistrarNew;
    private Spinner spinPregutas;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        String[] arraySpinner = new String[] {
                "Seleccione una pregunta a responder","Cual es tu comida favorita?", "Cual es tu deportista favorito?", "Cual es el nombre de tu mascota?", "Cual es el apellido de soltera de tu mama?"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinnermodel,arraySpinner);
        spinPregutas=findViewById(R.id.spinnerPregunta);
        spinPregutas.setAdapter(adapter);
        //mAuth = FirebaseAuth.getInstance();
        //dbref = FirebaseDatabase.getInstance().getReference();
        inicializarFirebase();
        etxtPass = findViewById(R.id.eTextPassword);
        etxtEmail = findViewById(R.id.eTextEmail);
        etxtName = findViewById(R.id.eTextName);
        etxtpassrepit=findViewById(R.id.eTextPasswordRep);
        extxdni=findViewById(R.id.eTextDNI);
        btnRegistrarNew=findViewById(R.id.btnregistrar);
        etxtrespuestasecret=findViewById(R.id.eTextRespuestaSecret);

        btnRegistrarNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validacion())
                {   RegisterNew();  }
                else  {Toast.makeText(registerUser.this,"No se pudo registrar verifique sus datos ingresados porfavor!", Toast.LENGTH_LONG).show();}
            }
        });

    }

    private boolean validacion() {
        boolean resul=true;
        String nombre = etxtName.getText().toString();
        String correo = etxtEmail.getText().toString();
        String password = etxtPass.getText().toString();
        String passwordrep = etxtpassrepit.getText().toString();
        String resp = etxtrespuestasecret.getText().toString();
        if (nombre.equals("")){
            etxtName.setError("Requerido");
            resul=false;
        }
        else if (passwordrep.equals("")){
            etxtpassrepit.setError("Requerido");
            resul=false;
        }
        else if (correo.equals("")){
            etxtEmail.setError("Requerido");
            resul=false;
        }
        else if (password.equals("")){
            etxtPass.setError("Requerido");
            resul=false;
        }
        else if (resp.equals("")){
            etxtrespuestasecret.setError("Requerido");
            resul=false;
        }
        else if (!password.equals(passwordrep)){
            etxtrespuestasecret.setError("Las contraseñas no coinciden!");
            resul=false;
        }
        return resul;
    }

    private void limpiarCajas() {
        etxtName.setText("");
        etxtEmail.setText("");
        etxtPass.setText("");
        etxtpassrepit.setText("");
        etxtrespuestasecret.setText("");
    }

    private void RegisterNew(){
        String nombre = etxtName.getText().toString();
        String correo = etxtEmail.getText().toString();
        String password = etxtPass.getText().toString();
        String strDni =extxdni.getText().toString();
        //String passwordrep = etxtpassrepit.getText().toString();
        String respuestsecret = etxtrespuestasecret.getText().toString();

        Usuario u = new Usuario();
        u.setUid(UUID.randomUUID().toString());
        u.setNombre(nombre);
        u.setDNI(strDni);
        u.setCorreo(correo);
        try{
            u.setPassword(CodificadorAES.encriptar(password));
            u.setRespuestasecret(CodificadorAES.encriptar(respuestsecret));
            databaseReference.child("Users").child(u.getUid()).setValue(u);
            Toast.makeText(this, "Se registro correctamente", Toast.LENGTH_LONG).show();
            startActivity(new Intent(registerUser.this,UserDashboardActivity.class));
            finish();
        }
        catch (Exception e){
            Toast.makeText(this, "Error: "+e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    private void registerUser() {

        /*mAuth.createUserWithEmailAndPassword(email, pass)
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
                });*/
    }
}