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

public class calculate_area extends AppCompatActivity {

    private Spinner spinnerFrom, spinnerTo;
    private EditText editTextFrom, editTextTo;

    // Conversion factors for area units
    private static final HashMap<String, Double> areaConversionFactors = new HashMap<String, Double>() {{
        put("Square Kilometer", 1000000.0);
        put("Hectare", 10000.0);
        put("Square Meter", 1.0);
        put("Square Decimeter", 0.01);
        put("Square Centimeter", 0.0001);
        put("Square Millimeter", 0.000001);
        put("Square Micron", 0.000000000001);
        put("Acre", 4046.8564224); // Corrected conversion factor
        put("Square Mile", 2589988.110336);
        put("Square Yard", 0.836127);
        put("Square Foot", 0.092903);
        put("Square Inch", 0.00064516);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_area);

        spinnerFrom = findViewById(R.id.spinner_from);
        spinnerTo = findViewById(R.id.spinner_to);
        editTextFrom = findViewById(R.id.from_count);
        editTextTo = findViewById(R.id.to_count);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll("Square Kilometer", "Hectare", "Square Meter", "Square Decimeter", "Square Centimeter", "Square Millimeter", "Square Micron", "Acre", "Square Mile", "Square Yard", "Square Foot", "Square Inch");
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        // Set listeners for spinners
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                convertArea();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        };

        spinnerFrom.setOnItemSelectedListener(spinnerListener);
        spinnerTo.setOnItemSelectedListener(spinnerListener);

        // Set text change listener for EditText
        editTextFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertArea(); // Call the conversion method when text changes
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void convertArea() {
        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        String input = editTextFrom.getText().toString();
        if (input.isEmpty()) {
            editTextTo.setText("");
            return;
        }

        if (areaConversionFactors.containsKey(fromUnit) && areaConversionFactors.containsKey(toUnit)) {
            try {
                double inputValue = Double.parseDouble(input);
                double result = convert(fromUnit, toUnit, inputValue);
                editTextTo.setText(String.valueOf(result));
            } catch (NumberFormatException e) {
                // Handle invalid input
                editTextTo.setText("");
            }
        } else {
            // Handle invalid units
            editTextTo.setText("");
        }
    }

    private double convert(String fromUnit, String toUnit, double value) {
        double fromFactor = areaConversionFactors.get(fromUnit);
        double toFactor = areaConversionFactors.get(toUnit);
        return (value * fromFactor) / toFactor;
    }

    public void back_btn(View view) {
        ImageView back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener((View v) -> {
            Intent intent = new Intent(calculate_area.this, MainActivity3.class);
            startActivity(intent);
        });
    }
}