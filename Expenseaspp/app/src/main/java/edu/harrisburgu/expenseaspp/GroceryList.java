package edu.harrisburgu.expenseaspp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class GroceryList extends AppCompatActivity {

    private LinearLayout parentLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        parentLinearLayout = findViewById(R.id.parent_linear_layout);

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> addEditTextAndCheckBox());
    }

    private void addEditTextAndCheckBox() {
        // Create a new LinearLayout to hold the EditText and CheckBox
        LinearLayout newLayout = new LinearLayout(this);
        newLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Create a new EditText
        EditText editText = new EditText(this);
        editText.setHint("Enter text here");
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        editText.setLayoutParams(editTextParams);

        // Create a new CheckBox
        CheckBox checkBox = new CheckBox(this);
        LinearLayout.LayoutParams checkBoxParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        checkBox.setLayoutParams(checkBoxParams);

        // Add the EditText and CheckBox to the new LinearLayout
        newLayout.addView(editText);
        newLayout.addView(checkBox);

        // Add the new LinearLayout to the parent LinearLayout
        parentLinearLayout.addView(newLayout);
    }
}
