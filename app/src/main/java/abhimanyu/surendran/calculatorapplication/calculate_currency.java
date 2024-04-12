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
import java.util.Map;

public class calculate_currency extends AppCompatActivity {

    private Spinner spinnerFrom, spinnerTo;
    private EditText fromCount, toCount;
    private Map<String, Double> conversionRates;

    // Define the array of currencies
    private String[] currenciesArray = {
            "United States (USD)",
            "European Union (EUR)",
            "United Kingdom (GBP)",
            "Japan (JPY)",
            "Australia (AUD)",
            "Canada (CAD)",
            "Switzerland (CHF)",
            "China (CNY)",
            "India (INR)",
            "South Korea (KRW)",
            "New Zealand (NZD)",
            "Russia (RUB)",
            "Brazil (BRL)",
            "Mexico (MXN)",
            "South Africa (ZAR)"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_currency);

        spinnerFrom = findViewById(R.id.spinner_from);
        spinnerTo = findViewById(R.id.spinner_to);
        fromCount = findViewById(R.id.from_count);
        toCount = findViewById(R.id.to_count);

        initializeConversionRates();

        // Initialize spinners with the array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currenciesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        // Set item select listeners
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertCurrency();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertCurrency();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Add TextChangedListener to fromCount EditText
        fromCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertCurrency();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void initializeConversionRates() {
        // Define conversion rates relative to USD
        conversionRates = new HashMap<>();
        conversionRates.put("United States (USD)", 1.0);
        conversionRates.put("European Union (EUR)", 0.94);
        conversionRates.put("United Kingdom (GBP)", 0.80);
        conversionRates.put("Japan (JPY)", 153.19);
        conversionRates.put("Australia (AUD)", 1.55);
        conversionRates.put("Canada (CAD)", 1.38);
        conversionRates.put("Switzerland (CHF)", 0.91);
        conversionRates.put("China (CNY)", 7.24);
        conversionRates.put("India (INR)", 83.60);
        conversionRates.put("South Korea (KRW)", 1382.53);
        conversionRates.put("New Zealand (NZD)", 1.68);
        conversionRates.put("Russia (RUB)", 92.90);
        conversionRates.put("Brazil (BRL)", 5.12);
        conversionRates.put("Mexico (MXN)", 16.67);
        conversionRates.put("South Africa (ZAR)", 18.90);
        // Add more currencies as needed...
    }

    private void convertCurrency() {
        String fromCurrency = spinnerFrom.getSelectedItem().toString();
        String toCurrency = spinnerTo.getSelectedItem().toString();

        String fromCountText = fromCount.getText().toString().trim();
        if (fromCountText.isEmpty()) {
            toCount.setText("");
            return;
        }

        double amount = Double.parseDouble(fromCountText);

        if (conversionRates.containsKey(fromCurrency) && conversionRates.containsKey(toCurrency)) {
            double fromRate = conversionRates.get(fromCurrency);
            double toRate = conversionRates.get(toCurrency);
            double result = (amount / fromRate) * toRate;
            toCount.setText(String.valueOf(result));
        }
    }

    public void back_btn(View view) {
        ImageView back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(calculate_currency.this, MainActivity3.class);
            startActivity(intent);
        });
    }
}
