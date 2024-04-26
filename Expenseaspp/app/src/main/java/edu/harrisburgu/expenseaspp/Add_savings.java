package edu.harrisburgu.expenseaspp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Add_savings extends AppCompatActivity {

    private LinearLayout parentLinearLayout; // Reference to the parent LinearLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_savings);

        parentLinearLayout = findViewById(R.id.parent_linear_layout); // Initialize the parent LinearLayout

        Button addSavingButton = findViewById(R.id.add_button);
        addSavingButton.setOnClickListener(v -> {
            addMoreInputFields(); // Call the method to add more input fields
        });

        // Your existing code for saving data...
        EditText savingEditText = findViewById(R.id.saving);
        EditText totalEditText = findViewById(R.id.total);
        EditText goalEditText = findViewById(R.id.goal);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> {
            String saving = savingEditText.getText().toString();
            String total = totalEditText.getText().toString();
            String goal = goalEditText.getText().toString();

            // Create an Intent to pass back the data to the Saving activity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("saving", saving);
            resultIntent.putExtra("total", total);
            resultIntent.putExtra("goal", goal);
            setResult(RESULT_OK, resultIntent);
            finish(); // Close the Add_savings activity
        });
    }

    // Method to dynamically add more input fields
    private void addMoreInputFields() {
        // Create new instances of EditText fields
        EditText newSavingEditText = new EditText(this);
        EditText newTotalEditText = new EditText(this);
        EditText newGoalEditText = new EditText(this);

        // Set properties for the new EditText fields (e.g., layout parameters, hint, input type)
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        newSavingEditText.setLayoutParams(params);
        newTotalEditText.setLayoutParams(params);
        newGoalEditText.setLayoutParams(params);
        newSavingEditText.setHint("Enter Saving");
        newTotalEditText.setHint("Enter Total");
        newGoalEditText.setHint("Enter Goal");
        newSavingEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        newTotalEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        newGoalEditText.setInputType(InputType.TYPE_CLASS_TEXT);

        // Add the new EditText fields to the parent LinearLayout
        parentLinearLayout.addView(newSavingEditText);
        parentLinearLayout.addView(newTotalEditText);
        parentLinearLayout.addView(newGoalEditText);
    }
}
