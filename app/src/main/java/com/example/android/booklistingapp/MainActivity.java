package com.example.android.booklistingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.valider);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String search;
                EditText research = (EditText) findViewById(R.id.autitle);
                search = research.getText().toString();
                if (search.isEmpty()) {
                    Toast.makeText(MainActivity.this, getString(R.string.complete), Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, Results.class);
                    intent.putExtra("ss", search);
                    startActivity(intent);
                }
            }
        });
    }
}
