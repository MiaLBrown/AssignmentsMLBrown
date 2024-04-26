package edu.harrisburgu.expenseaspp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TotalExpenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_expenses);

        Button addexpense = findViewById(R.id.add_expense);
        addexpense.setOnClickListener(v -> {
            Intent intent = new Intent(TotalExpenses.this, Expenses.class);
            startActivity(intent);
        });

    }

}