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

public class calculate_numeral_system extends AppCompatActivity {

    private EditText fromEditText, toEditText;
    private Spinner fromSpinner, toSpinner;

    // Conversion factors for numeral systems
    private static final HashMap<String, Integer> numeralSystemsBase = new HashMap<String, Integer>() {{
        put("BIN", 2);
        put("OCT", 8);
        put("DEC", 10);
        put("HEX", 16);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_numeral_system);

        fromEditText = findViewById(R.id.from_count);
        toEditText = findViewById(R.id.to_count);
        fromSpinner = findViewById(R.id.spinner_from);
        toSpinner = findViewById(R.id.spinner_to);

        // Populate spinners with numeral system types
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("BIN");
        adapter.add("OCT");
        adapter.add("DEC");
        adapter.add("HEX");

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        // Set listeners for spinners
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                convertNumber();
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
                convertNumber();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void convertNumber() {
        String fromSystem = fromSpinner.getSelectedItem().toString();
        String toSystem = toSpinner.getSelectedItem().toString();

        String input = fromEditText.getText().toString();
        if (input.isEmpty()) {
            toEditText.setText("");
            return;
        }

        try {
            int inputValue = Integer.parseInt(input, numeralSystemsBase.get(fromSystem));
            String result = Integer.toString(inputValue, numeralSystemsBase.get(toSystem));
            toEditText.setText(result.toUpperCase());
        } catch (NumberFormatException e) {
            // Handle invalid input
            toEditText.setText("");
        }
    }

    public void back_btn(View view) {
        ImageView back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(calculate_numeral_system.this, MainActivity3.class);
            startActivity(intent);
        });
    }
}