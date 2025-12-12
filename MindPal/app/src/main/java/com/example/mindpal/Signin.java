package com.example.mindpal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Signin extends AppCompatActivity {

    ImageView imgLogo;
    TextView txtWelcome, txtLogin;
    Button btnSignIn;
    TextInputLayout layoutEmail, layoutPass, layoutPass2;
    TextInputEditText editEmail, editPass, editPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgLogo = findViewById(R.id.imgLogo);
        txtWelcome = findViewById(R.id.txtWelcome);
        txtLogin = findViewById(R.id.txtLogin);
        btnSignIn = findViewById(R.id.btnSignIn);
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        editPass2 = findViewById(R.id.editPass2);
        layoutEmail = findViewById(R.id.layoutEmail);
        layoutPass = findViewById(R.id.layoutPass);
        layoutPass2 = findViewById(R.id.layoutPass2);

        txtLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });

        btnSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);

            Toast.makeText(this, "Please Verify Your Account Via Email", Toast.LENGTH_SHORT).show();
        });




    }
}