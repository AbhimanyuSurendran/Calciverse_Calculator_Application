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

public class calculate_length extends AppCompatActivity {

    private EditText fromCountEditText, toCountEditText;
    private Spinner fromSpinner, toSpinner;

    // Array of length units
    private static final String[] lengthUnits = {"Millimeter", "Centimeter", "Meter", "Kilometer", "Inch", "Foot", "Yard", "Mile", "Nautical Mile", "Micrometer", "Nanometer", "Picometer", "Mile (US)"};

    // Conversion factors for length units
    private static final HashMap<String, Double> lengthConversionFactors = new HashMap<String, Double>() {{
        put("Millimeter", 1.0);
        put("Centimeter", 10.0);
        put("Meter", 1000.0);
        put("Kilometer", 1000000.0);
        put("Inch", 25.4);
        put("Foot", 304.8);
        put("Yard", 914.4);
        put("Mile", 1609344.0);
        put("Nautical Mile", 1852000.0);
        put("Micrometer", 0.001);
        put("Nanometer", 0.000001);
        put("Picometer", 0.000000001);
        put("Mile (US)", 1609344.0);
    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_length);

        fromCountEditText = findViewById(R.id.from_count);
        toCountEditText = findViewById(R.id.to_count);
        fromSpinner = findViewById(R.id.spinner_from);
        toSpinner = findViewById(R.id.spinner_to);

        // Populate spinners with length units
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lengthUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        // Set listener for "from" Spinner
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertLength();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Set text change listener for "from" EditText
        fromCountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertLength();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void convertLength() {
        String fromUnit = fromSpinner.getSelectedItem().toString();
        String toUnit = toSpinner.getSelectedItem().toString();

        // Get the input value
        String inputValueStr = fromCountEditText.getText().toString();
        if (inputValueStr.isEmpty()) {
            toCountEditText.setText("");
            return;
        }
        double inputValue = Double.parseDouble(inputValueStr);

        // Convert input value to millimeters
        double millimeters = inputValue * lengthConversionFactors.get(fromUnit);

        // Convert millimeters to the desired output unit
        double result = millimeters / lengthConversionFactors.get(toUnit);

        // Set the result to the "to" EditText
        toCountEditText.setText(String.valueOf(result));
    }

    public void back_btn(View view) {
        ImageView back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(calculate_length.this, MainActivity3.class);
            startActivity(intent);
        });
    }
}