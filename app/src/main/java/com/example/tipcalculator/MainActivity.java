package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

     // Declarations
    final int INITIAL_TIP_PERCENTAGE = 15;
    int tipPercentage;
    EditText edtMeal;
    TextView txtPercentage;
    SeekBar seekBar;
    Button btnReset;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializations
        tipPercentage = INITIAL_TIP_PERCENTAGE;
        edtMeal = (EditText) findViewById(R.id.edtMeal);
        txtPercentage = (TextView) findViewById(R.id.txtPercentage);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        btnReset = (Button) findViewById(R.id.btnReset);
        txtResult = (TextView) findViewById(R.id.txtResult);

        // Setup text change listener
        edtMeal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                displayTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercentage = progress;
                displayPercentage();
                displayTotal();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Setup reset button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset meal cost
                edtMeal.setText(null);

                // Reset percentage
                tipPercentage = INITIAL_TIP_PERCENTAGE;
                seekBar.setProgress(tipPercentage);
                displayPercentage();

                // Reset total
                txtResult.setText("TOTAL");
            }
        });
    }

    public void displayPercentage() {
        txtPercentage.setText(tipPercentage + "%");
    }

    public void displayTotal() {
        double mealCostAsNumber = 0;
        String mealCostAsText = edtMeal.getText().toString();

        if(!mealCostAsText.isEmpty()) {
            mealCostAsNumber = Double.parseDouble(mealCostAsText);
        }

        double totalCost = mealCostAsNumber * (tipPercentage / 100.0 + 1);
        String messageTotal = String.format(Locale.getDefault(), "$ %.2f", totalCost);

        txtResult.setText(messageTotal);
    }

}