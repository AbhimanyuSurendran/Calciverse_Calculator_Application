package abhimanyu.surendran.calculatorapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void calculator_unclicked(View view) {
        ImageView calculator_unclicked = findViewById(R.id.calculator_unclicked);
        calculator_unclicked.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
            startActivity(intent);
        });
    }

    public void additional_clicked(View view) {
        ImageView additional_clicked = findViewById(R.id.additional_clicked);
        additional_clicked.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, MainActivity3.class);
            startActivity(intent);
        });
    }

    public void age_icon(View view) {
        ImageView age_icon = findViewById(R.id.age_icon);
        age_icon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, calculate_age.class);
            startActivity(intent);
        });
    }

    public void area_icon(View view) {
        ImageView area_icon = findViewById(R.id.area_icon);
        area_icon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, calculate_area.class);
            startActivity(intent);
        });
    }

    public void bmi_icon(View view) {
        ImageView bmi_icon = findViewById(R.id.bmi_icon);
        bmi_icon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, calculate_bmi.class);
            startActivity(intent);
        });
    }

    public void currency_icon(View view) {
        ImageView currency_icon = findViewById(R.id.currency_icon);
        currency_icon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, calculate_currency.class);
            startActivity(intent);
        });
    }

    public void data_icon(View view) {
        ImageView data_icon = findViewById(R.id.data_icon);
        data_icon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, calculate_data.class);
            startActivity(intent);
        });
    }

    public void length_icon(View view) {
        ImageView length_icon = findViewById(R.id.length_icon);
        length_icon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, calculate_length.class);
            startActivity(intent);
        });
    }

    public void numeral_system_icon(View view) {
        ImageView numeral_system_icon = findViewById(R.id.numeral_system_icon);
        numeral_system_icon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, calculate_numeral_system.class);
            startActivity(intent);
        });
    }

    public void speed_icon(View view) {
        ImageView speed_icon = findViewById(R.id.speed_icon);
        speed_icon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, calculate_speed.class);
            startActivity(intent);
        });
    }

    public void temperature_icon(View view) {
        ImageView temperature_icon = findViewById(R.id.temperature_icon);
        temperature_icon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, calculate_temperature.class);
            startActivity(intent);
        });
    }
}