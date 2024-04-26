package edu.harrisburgu.expenseaspp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button savingGoalButton = findViewById(R.id.saving_goal);
        savingGoalButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Saving.class);
            startActivity(intent);
        });

        Button expensesButton = findViewById(R.id.expense);
        expensesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TotalExpenses.class);
            startActivity(intent);
        });
    }
}
