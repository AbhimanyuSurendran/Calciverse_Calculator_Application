package abhimanyu.surendran.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;

public class calculate_age extends AppCompatActivity {
    private DatePicker datePicker;
    private TextView showSelectedDate, showAge;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_age);

        datePicker = findViewById(R.id.date_picker);
        showSelectedDate = findViewById(R.id.show_selected_date);
        showAge = findViewById(R.id.show_age);
        calculateButton = findViewById(R.id.calculate_button);

        // Set the range of selectable dates from 1900 to current year
        Calendar minDate = Calendar.getInstance();
        minDate.set(1900, 0, 1); // January 1, 1900
        long minDateTime = minDate.getTimeInMillis();

        Calendar maxDate = Calendar.getInstance();
        int currentYear = maxDate.get(Calendar.YEAR);
        maxDate.set(currentYear, 11, 31); // December 31 of the current year
        long maxDateTime = maxDate.getTimeInMillis();

        datePicker.setMinDate(minDateTime);
        datePicker.setMaxDate(maxDateTime);

        // Set today's date as the default selected date in the DatePicker
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, month, dayOfMonth, (view, year1, monthOfYear, dayOfMonth1) -> {
            String selectedDate = dayOfMonth1 + "/" + (monthOfYear + 1) + "/" + year1;
            showSelectedDate.setText(selectedDate);
        });

        calculateButton.setOnClickListener(v -> calculateAge());
    }

    private void calculateAge() {
        int selectedYear = datePicker.getYear();
        int selectedMonth = datePicker.getMonth();
        int selectedDayOfMonth = datePicker.getDayOfMonth();

        Calendar dob = Calendar.getInstance();
        dob.set(selectedYear, selectedMonth, selectedDayOfMonth);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        showAge.setText("Your age is " + age + " years.");
    }

    public void back_btn(View view) {
        ImageView back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(calculate_age.this, MainActivity3.class);
            startActivity(intent);
        });
    }
}