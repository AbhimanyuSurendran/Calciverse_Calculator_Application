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

public class calculate_data extends AppCompatActivity {

    private EditText fromCountEditText, toCountEditText;
    private Spinner fromSpinner, toSpinner;

    // Array of data units
    private static final String[] dataUnits = {"Byte", "Kilobyte", "Megabyte", "Gigabyte", "Terabyte", "Petabyte"};

    // Conversion factors for data units
    private static final HashMap<String, Double> dataConversionFactors = new HashMap<String, Double>() {{
        put("Byte", 1.0);
        put("Kilobyte", 1024.0);
        put("Megabyte", 1024.0 * 1024.0);
        put("Gigabyte", 1024.0 * 1024.0 * 1024.0);
        put("Terabyte", 1024.0 * 1024.0 * 1024.0 * 1024.0);
        put("Petabyte", 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0);
    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_data);

        fromCountEditText = findViewById(R.id.from_count);
        toCountEditText = findViewById(R.id.to_count);
        fromSpinner = findViewById(R.id.spinner_from);
        toSpinner = findViewById(R.id.spinner_to);

        // Populate spinners with data units
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        // Set listeners for spinners
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        fromSpinner.setOnItemSelectedListener(listener);
        toSpinner.setOnItemSelectedListener(listener);

        // Set text change listener for EditText
        fromCountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertData();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void convertData() {
        String fromUnit = fromSpinner.getSelectedItem().toString();
        String toUnit = toSpinner.getSelectedItem().toString();

        // Get the input value
        String inputValueStr = fromCountEditText.getText().toString();
        if (inputValueStr.isEmpty()) {
            toCountEditText.setText("");
            return;
        }
        double inputValue = Double.parseDouble(inputValueStr);

        // Convert input value to bytes
        double bytes = inputValue * dataConversionFactors.get(fromUnit);

        // Convert bytes to the desired output unit
        double result = bytes / dataConversionFactors.get(toUnit);

        // Set the result to the "to" EditText
        toCountEditText.setText(String.valueOf(result));
    }

    public void back_btn(View view) {
        ImageView back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(calculate_data.this, MainActivity3.class);
            startActivity(intent);
        });
    }
}