package abhimanyu.surendran.calculatorapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationView;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


public class MainActivity2 extends AppCompatActivity {
    private TextView resultTextView;
    private String currentNumber = "";
    private String operation = "";
    private double firstNumber = 0;
    private double secondNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DrawerLayout drawerLayout;
        NavigationView navigationView;
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_start);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here
                int id = item.getItemId();

                // You can perform different actions based on the item clicked
                Intent intent = null;
                if (id == R.id.nav_calculate_age) {
                    intent = new Intent(MainActivity2.this, calculate_age.class);
                } else if (id == R.id.nav_calculate_area) {
                    intent = new Intent(MainActivity2.this, calculate_area.class);
                } else if (id == R.id.nav_calculate_bmi) {
                    intent = new Intent(MainActivity2.this, calculate_bmi.class);
                } else if (id == R.id.nav_calculate_currency) {
                    intent = new Intent(MainActivity2.this, calculate_currency.class);
                } else if (id == R.id.nav_calculate_data) {
                    intent = new Intent(MainActivity2.this, calculate_data.class);
                } else if (id == R.id.nav_calculate_length) {
                    intent = new Intent(MainActivity2.this, calculate_length.class);
                } else if (id == R.id.nav_calculate_numeral_system) {
                    intent = new Intent(MainActivity2.this, calculate_numeral_system.class);
                } else if (id == R.id.nav_calculate_speed) {
                    intent = new Intent(MainActivity2.this, calculate_speed.class);
                } else if (id == R.id.nav_calculate_temperature) {
                    intent = new Intent(MainActivity2.this, calculate_temperature.class);
                } else if (id == R.id.nav_about_us) {
                    // Handle "About Us" item click here
                }

                if (intent != null) {
                    startActivity(intent);
                    // Highlight the selected item in the navigation drawer
                    item.setChecked(true);
                    // Close the navigation drawer
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true; // Indicate that the item click was handled
                }

                return false; // Indicate that the item click was not handled
            }
        });


        resultTextView = findViewById(R.id.displayTextView);

        Button button00 = findViewById(R.id.button00);
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDot = findViewById(R.id.buttonDot);
        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonModule = findViewById(R.id.buttonModule);
        Button buttonEquals = findViewById(R.id.buttonEquals);

        // Define pop click animation
        Animation popAnimation = AnimationUtils.loadAnimation(this, R.anim.button_click);
        popAnimation.setDuration(100);

        // Set onClickListener with pop animation for each button
        View.OnClickListener onClickAnimation = (View v) -> {
            v.startAnimation(popAnimation);
        };

        button00.setOnClickListener(v -> {
            numberClicked("00");
            onClickAnimation.onClick(v);
        });
        button0.setOnClickListener(v -> {
            numberClicked("0");
            onClickAnimation.onClick(v);
        });
        button1.setOnClickListener(v -> {
            numberClicked("1");
            onClickAnimation.onClick(v);
        });
        button2.setOnClickListener(v -> {
            numberClicked("2");
            onClickAnimation.onClick(v);
        });
        button3.setOnClickListener(v -> {
            numberClicked("3");
            onClickAnimation.onClick(v);
        });
        button4.setOnClickListener(v -> {
            numberClicked("4");
            onClickAnimation.onClick(v);
        });
        button5.setOnClickListener(v -> {
            numberClicked("5");
            onClickAnimation.onClick(v);
        });
        button6.setOnClickListener(v -> {
            numberClicked("6");
            onClickAnimation.onClick(v);
        });
        button7.setOnClickListener(v -> {
            numberClicked("7");
            onClickAnimation.onClick(v);
        });
        button8.setOnClickListener(v -> {
            numberClicked("8");
            onClickAnimation.onClick(v);
        });
        button9.setOnClickListener(v -> {
            numberClicked("9");
            onClickAnimation.onClick(v);
        });
        buttonDot.setOnClickListener(v -> {
            numberClicked(".");
            onClickAnimation.onClick(v);
        });

        buttonPlus.setOnClickListener(v -> {
            operationClicked("+");
            onClickAnimation.onClick(v);
        });
        buttonMinus.setOnClickListener(v -> {
            operationClicked("-");
            onClickAnimation.onClick(v);
        });
        buttonMultiply.setOnClickListener(v -> {
            operationClicked("*");
            onClickAnimation.onClick(v);
        });
        buttonDivide.setOnClickListener(v -> {
            operationClicked("/");
            onClickAnimation.onClick(v);
        });
        buttonModule.setOnClickListener(v -> {
            operationClicked("%");
            onClickAnimation.onClick(v);
        });

        buttonEquals.setOnClickListener(v -> {
            equalsClicked();
            onClickAnimation.onClick(v);
        });

        buttonBack.setOnClickListener(v -> {
            clearClicked();
            onClickAnimation.onClick(v);
        });

        buttonClear.setOnClickListener(v -> {
            clearClicked();
            onClickAnimation.onClick(v);
        });
    }
    private void numberClicked(String number) {
        currentNumber += number;
        resultTextView.setText(currentNumber);
    }
    private void operationClicked(String operation) {
        if (currentNumber.isEmpty()) { return; }
        this.operation = operation;
        firstNumber = Double.parseDouble(currentNumber);
        currentNumber = "";
    }
    private void equalsClicked() {
        if (currentNumber.isEmpty()) { return; }
        secondNumber = Double.parseDouble(currentNumber);

        switch (operation) {
            case "+":
                firstNumber += secondNumber;
                break;
            case "-":
                firstNumber -= secondNumber;
                break;
            case "*":
                firstNumber *= secondNumber;
                break;
            case "/":
                firstNumber /= secondNumber;
                break;
            case "%":
                firstNumber %= secondNumber;
                break;
        }
        currentNumber = String.valueOf(firstNumber);
        resultTextView.setText(currentNumber);
    }
    private void clearClicked() {
        currentNumber = "";
        operation = "";
        firstNumber = 0;
        secondNumber = 0;
        resultTextView.setText("0");
    }

    public void calculator_clicked(View view) {
        ImageView calculator_clicked = findViewById(R.id.calculator_clicked);
        calculator_clicked.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity2.class);
            startActivity(intent);
        });
    }

    public void additional_unclicked(View view) {
        ImageView additional_unclicked = findViewById(R.id.additional_unclicked);
        additional_unclicked.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent);
        });
    }

}