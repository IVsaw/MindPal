package com.example.mindpal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {
    ImageView imgLogo;
    Button btnProf, btnNotif, btnLogOut;
    ImageButton btnProfile;
    TextView txtWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgLogo = findViewById(R.id.imgLogo);
        btnProf = findViewById(R.id.btnProf);
        btnNotif = findViewById(R.id.btnNotif);
        btnProfile = findViewById(R.id.btnProfile);
        btnLogOut = findViewById(R.id.btnLogOut);
        txtWelcome = findViewById(R.id.txtWelcome);

        btnProf.setOnClickListener(v -> {
            Intent intent = new Intent(this, Professional.class);
            startActivity(intent);
        });

        btnNotif.setOnClickListener(v -> {
            Intent intent = new Intent(this, Notification.class);
            startActivity(intent);
        });

        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        });


    }
}