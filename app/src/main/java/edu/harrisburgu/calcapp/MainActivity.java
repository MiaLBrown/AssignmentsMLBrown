package edu.harrisburgu.calcapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView displayRow;
    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonEquals;
    private Button buttonMultiply;
    private Button buttonDivide;

    // keep track of the current math operation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayRow = findViewById(R.id.displayRow);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonEquals = findViewById(R.id.buttonEquals);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);


        setButtonClickListeners();
    }

    private void setButtonClickListeners() {
        OnClickListener numberClickListener = view -> {
            Button clickedButton = (Button) view;
            updateDisplayRow(clickedButton.getText().toString());
        };

        findViewById(R.id.button0).setOnClickListener(numberClickListener);
        findViewById(R.id.button1).setOnClickListener(numberClickListener);
        findViewById(R.id.button2).setOnClickListener(numberClickListener);
        findViewById(R.id.button3).setOnClickListener(numberClickListener);
        findViewById(R.id.button4).setOnClickListener(numberClickListener);
        findViewById(R.id.button5).setOnClickListener(numberClickListener);
        findViewById(R.id.button6).setOnClickListener(numberClickListener);
        findViewById(R.id.button7).setOnClickListener(numberClickListener);
        findViewById(R.id.button8).setOnClickListener(numberClickListener);
        findViewById(R.id.button9).setOnClickListener(numberClickListener);

        buttonPlus.setOnClickListener(view -> MainActivity.this.handleOperationButtonClick("+"));

        buttonMinus.setOnClickListener(view -> MainActivity.this.handleOperationButtonClick("-"));

        buttonMultiply.setOnClickListener(view -> MainActivity.this.handleOperationButtonClick("*"));

        buttonDivide.setOnClickListener(view -> MainActivity.this.handleOperationButtonClick("/"));

        buttonEquals.setOnClickListener(view -> MainActivity.this.calculateResult());

        // long click to clear display
        buttonEquals.setOnLongClickListener(v -> {
            MainActivity.this.clearDisplay();
            return true;
        });

    }

    @SuppressLint("SetTextI18n")
    private void updateDisplayRow(String value) {
        String currentText = displayRow.getText().toString(); //update the string val
        displayRow.setText(currentText + value);
    }

    @SuppressLint("SetTextI18n")
    private void handleOperationButtonClick(String operation) {
        displayRow.setText(displayRow.getText().toString() + operation);

    }

    private void calculateResult() {
        String currentText = displayRow.getText().toString();

        String[] numbers = currentText.split("[+\\-*/]"); //split to get numbers


        String[] operators = currentText.split("[0-9]+");//Split the input string to get the operators

        double result; //using double for ints not divisible for decimal


        if (numbers.length > 0 && operators.length > 0) {// Check if there are both numbers and operators and if divisible
            result = Double.parseDouble(numbers[0].trim());

            for (int i = 1; i < numbers.length; i++) {
                double num = Double.parseDouble(numbers[i].trim());
                String operator = operators[i].trim();

                switch (operator) {
                    case "+":
                        result += num;
                        break;
                    case "-":
                        result -= num;
                        break;
                    case "*":
                        result *= num;
                        break;
                    case "/":
                        if (num != 0) { //Check if the number is zero before dividing
                            result /= num;
                        } else {
                            Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                            return;
                        }

                }
            }

            displayRow.setText(String.valueOf(result));
        }
    }

    private void clearDisplay() { //clear display
        displayRow.setText("");
        Toast.makeText(this, "Display cleared", Toast.LENGTH_SHORT).show();
    }
}

