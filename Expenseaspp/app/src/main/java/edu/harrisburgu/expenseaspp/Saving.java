package edu.harrisburgu.expenseaspp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Saving extends AppCompatActivity {

    // Declare REQUEST_CODE constant
    private static final int REQUEST_CODE_ADD_SAVINGS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);

        // Start Add_savings activity when "Add Saving" button is clicked
        Button addSavingButton = findViewById(R.id.add_saving);
        addSavingButton.setOnClickListener(v -> {
            Intent intent = new Intent(Saving.this, Add_savings.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_SAVINGS);
        });
        Button button= findViewById(R.id.back_button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Saving.this, MainActivity.class);
            startActivity(intent);
        });

        // Initialize the TextViews
        TextView savingTextView = findViewById(R.id.savingTextView);
        TextView totalTextView = findViewById(R.id.totalTextView);
        TextView goalTextView = findViewById(R.id.goalTextView);

        // Check if there are any values passed from the previous activity
        Intent intent = getIntent();
        if (intent != null) {
            String saving = intent.getStringExtra("saving");
            String total = intent.getStringExtra("total");
            String goal = intent.getStringExtra("goal");

            // Display the input values in the TextViews
            savingTextView.setText(String.format("Saving: %s", saving));
            totalTextView.setText(String.format("Total: %s", total));
            goalTextView.setText(String.format("Goal: %s", goal));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Inside onActivityResult method
        if (requestCode == REQUEST_CODE_ADD_SAVINGS && resultCode == RESULT_OK) {
            // Retrieve the added savings data from the intent
            String saving = data.getStringExtra("saving");
            String total = data.getStringExtra("total");
            String goal = data.getStringExtra("goal");

            // Create a new TextView for the new saving entry
            TextView newSavingTextView = new TextView(this);
            newSavingTextView.setText(String.format("Saving: %s\nTotal: %s\nGoal: %s\n", saving, total, goal));

            // Add the new TextView to the LinearLayout
            LinearLayout container = findViewById(R.id.container);
            container.addView(newSavingTextView);
        }


    }
}
