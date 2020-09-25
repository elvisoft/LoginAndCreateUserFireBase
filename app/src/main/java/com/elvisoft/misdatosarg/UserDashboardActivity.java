package com.elvisoft.misdatosarg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class UserDashboardActivity extends AppCompatActivity {
    private Button btnlogout;
    private String usernameActivo;
    TextView texBienvenida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        texBienvenida=findViewById(R.id.textVBienvenido);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            usernameActivo = bundle.getString("NameUser");
            texBienvenida.setText("Bienvenido, "+usernameActivo);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu ){
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menudashboard, menu);
        return  true;
    }
}