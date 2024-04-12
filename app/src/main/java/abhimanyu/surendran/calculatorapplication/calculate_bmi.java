package abhimanyu.surendran.calculatorapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class calculate_bmi extends AppCompatActivity {
    private EditText weightEditText, heightEditText;
    private Button goButton;
    private TextView bmiTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);

        weightEditText = findViewById(R.id.weight);
        heightEditText = findViewById(R.id.height);
        goButton = findViewById(R.id.go_btn);
        bmiTextView = findViewById(R.id.show_bmi);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightEditText.getText().toString();
        String heightStr = heightEditText.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            bmiTextView.setText("Please enter both weight and height.");
            return;
        }

        float weight = Float.parseFloat(weightStr);
        float height = Float.parseFloat(heightStr) / 100; // Convert height to meters

        float bmi = weight / (height * height);

        String bmiResult;
        if (bmi < 18.5) {
            bmiResult = "Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            bmiResult = "Normal weight";
        } else if (bmi >= 25 && bmi < 30) {
            bmiResult = "Overweight";
        } else {
            bmiResult = "Obese";
        }

        bmiTextView.setText(String.format("Your BMI: %.2f (%s)", bmi, bmiResult));
    }
    public void back_btn(View view) {
        ImageView back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(calculate_bmi.this, MainActivity3.class);
            startActivity(intent);
        });
    }
}