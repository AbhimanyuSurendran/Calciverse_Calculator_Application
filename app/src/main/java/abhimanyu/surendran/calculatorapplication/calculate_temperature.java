package abhimanyu.surendran.calculatorapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;


public class calculate_temperature extends AppCompatActivity {

    private EditText fromEditText, toEditText;
    private Spinner fromSpinner, toSpinner;

    // Conversion factors for temperature units
    private static final HashMap<String, Double> temperatureConversionFactors = new HashMap<String, Double>() {{
        put("Celsius", 1.0);
        put("Fahrenheit", 1.8);
        put("Kelvin", 1.0);
        put("Rankine", 1.8);
        put("Reaumur", 0.8);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_temperature);

        fromEditText = findViewById(R.id.from_count);
        toEditText = findViewById(R.id.to_count);
        fromSpinner = findViewById(R.id.spinner_from);
        toSpinner = findViewById(R.id.spinner_to);

        // Populate spinners with temperature unit types
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter.add("Celsius");
        adapter.add("Fahrenheit");
        adapter.add("Kelvin");
        adapter.add("Rankine");
        adapter.add("Reaumur");

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        // Set listeners for spinners
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                convertTemperature();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        };

        fromSpinner.setOnItemSelectedListener(spinnerListener);
        toSpinner.setOnItemSelectedListener(spinnerListener);

        // Set text change listener for EditText
        fromEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertTemperature();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void convertTemperature() {
        String fromUnit = fromSpinner.getSelectedItem().toString();
        String toUnit = toSpinner.getSelectedItem().toString();

        String input = fromEditText.getText().toString();
        if (input.isEmpty()) {
            toEditText.setText("");
            return;
        }

        try {
            double inputValue = Double.parseDouble(input);
            double result;
            if (fromUnit.equals("Celsius")) {
                result = convertFromCelsius(inputValue, toUnit);
            } else if (fromUnit.equals("Fahrenheit")) {
                result = convertFromFahrenheit(inputValue, toUnit);
            } else if (fromUnit.equals("Kelvin")) {
                result = convertFromKelvin(inputValue, toUnit);
            } else if (fromUnit.equals("Rankine")) {
                result = convertFromRankine(inputValue, toUnit);
            } else { // Reaumur
                result = convertFromReaumur(inputValue, toUnit);
            }
            toEditText.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            // Handle invalid input
            toEditText.setText("");
        }
    }

    private double convertFromCelsius(double value, String toUnit) {
        if (toUnit.equals("Fahrenheit")) {
            return value * 9.0 / 5.0 + 32.0;
        } else if (toUnit.equals("Kelvin")) {
            return value + 273.15;
        } else if (toUnit.equals("Rankine")) {
            return (value + 273.15) * 9.0 / 5.0;
        } else if (toUnit.equals("Reaumur")) {
            return value * 4.0 / 5.0;
        } else { // Celsius
            return value;
        }
    }

    private double convertFromFahrenheit(double value, String toUnit) {
        if (toUnit.equals("Celsius")) {
            return (value - 32.0) * 5.0 / 9.0;
        } else if (toUnit.equals("Kelvin")) {
            return (value + 459.67) * 5.0 / 9.0;
        } else if (toUnit.equals("Rankine")) {
            return value + 459.67;
        } else if (toUnit.equals("Reaumur")) {
            return (value - 32.0) * 4.0 / 9.0;
        } else { // Fahrenheit
            return value;
        }
    }

    private double convertFromKelvin(double value, String toUnit) {
        if (toUnit.equals("Celsius")) {
            return value - 273.15;
        } else if (toUnit.equals("Fahrenheit")) {
            return value * 9.0 / 5.0 - 459.67;
        } else if (toUnit.equals("Rankine")) {
            return value * 9.0 / 5.0;
        } else if (toUnit.equals("Reaumur")) {
            return (value - 273.15) * 4.0 / 5.0;
        } else { // Kelvin
            return value;
        }
    }

    private double convertFromRankine(double value, String toUnit) {
        if (toUnit.equals("Celsius")) {
            return (value - 491.67) * 5.0 / 9.0;
        } else if (toUnit.equals("Fahrenheit")) {
            return value - 459.67;
        } else if (toUnit.equals("Kelvin")) {
            return value * 5.0 / 9.0;
        } else if (toUnit.equals("Reaumur")) {
            return (value - 491.67) * 4.0 / 9.0;
        } else { // Rankine
            return value;
        }
    }

    private double convertFromReaumur(double value, String toUnit) {
        if (toUnit.equals("Celsius")) {
            return value * 5.0 / 4.0;
        } else if (toUnit.equals("Fahrenheit")) {
            return value * 9.0 / 4.0 + 32.0;
        } else if (toUnit.equals("Kelvin")) {
            return value * 5.0 / 4.0 + 273.15;
        } else if (toUnit.equals("Rankine")) {
            return value * 9.0 / 4.0 + 491.67;
        } else { // Reaumur
            return value;
        }
    }

    public void back_btn(View view) {
        ImageView back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(calculate_temperature.this, MainActivity3.class);
            startActivity(intent);
        });
    }
}