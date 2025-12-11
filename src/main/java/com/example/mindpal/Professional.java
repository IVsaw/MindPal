package com.example.mindpal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class Professional extends AppCompatActivity {
    Button btnBack;
    TextView txtProf;
    ListView listProf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profesional);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        txtProf = findViewById(R.id.txtProf);
        listProf = findViewById(R.id.listProf);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        });

        List<ProfItem> professionals = List.of(
                new ProfItem("Dr. Coki", "Review: ⭐⭐⭐⭐⭐", R.drawable.drcoki),
                new ProfItem("Dr. John", "Review: ⭐⭐⭐⭐⭐", R.drawable.drjohn),
                new ProfItem("Dr. Mank", "Review: ⭐⭐⭐⭐⭐", R.drawable.drmank),
                new ProfItem("Dr. Nyian", "Review: ⭐⭐⭐⭐⭐", R.drawable.drnyian),
                new ProfItem("Dr. Wong", "Review: ⭐⭐⭐⭐⭐", R.drawable.drwong)
        );

        ProfessionalAdapter adapter = new ProfessionalAdapter(this, professionals);

        listProf.setAdapter(adapter); //penghubung ke ListView
    }
}