package edu.harrisburgu.expenseaspp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.harrisburgu.expenseaspp.R;

public class Expenses extends AppCompatActivity {

    private EditText expenseInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        expenseInput = findViewById(R.id.expense_total);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered expense
                String expense = expenseInput.getText().toString();

                // Pass the expense back to MainActivity
                Intent intent = new Intent();
                intent.putExtra("expense", expense);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Just finish the Expenses activity and go back to MainActivity
            }
        });
    }
}
