package com.example.assigment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText unitInput;
    private EditText rebateInput;
    private Button calculateButton;
    private Button profileButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitInput = findViewById(R.id.unit_input);
        rebateInput = findViewById(R.id.rebate_input);
        calculateButton = findViewById(R.id.calculate_button);
        profileButton = findViewById(R.id.profile_button);
        resultTextView = findViewById(R.id.result_text_view);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });
    }

    private void calculateBill() {
        String unitsString = unitInput.getText().toString();
        String rebateString = rebateInput.getText().toString();

        if (TextUtils.isEmpty(unitsString) || TextUtils.isEmpty(rebateString)) {
            Toast.makeText(this, "Please fill in all the input fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int units = Integer.parseInt(unitsString);
        double rebatePercentage = Double.parseDouble(rebateString) / 100;

        double totalCharges = 0.0;

        if (units <= 200) {
            totalCharges = units * 0.218;
        } else if (units <= 300) {
            totalCharges = 200 * 0.218 + (units - 200) * 0.334;
        } else if (units <= 600) {
            totalCharges = 200 * 0.218 + 100 * 0.334 + (units - 300) * 0.516;
        } else if (units <= 900) {
            totalCharges = 200 * 0.218 + 100 * 0.334 + 300 * 0.516 + (units - 600) * 0.546;
        }

        double finalCost = totalCharges - (totalCharges * rebatePercentage);

        resultTextView.setText(String.format("Final Cost: RM %.2f", finalCost));
    }

    private void openProfile() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}
