package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditWeightsSettings extends AppCompatActivity {
    private EditText yearly_salary_weight;
    private EditText yearly_bonus_weight;
    private EditText stock_weight;
    private EditText home_weight;
    private EditText holiday_weight;
    private EditText stipend_weight;

    private double final_yearly_salary_weight = 1.0 / 6.0;
    private double final_yearly_bonus_weight = 1.0 / 6.0;
    private double final_stock_weight = 1.0 / 6.0;
    private double final_home_weight = 1.0 / 6.0;
    private double final_holiday_weight = 1.0 / 6.0;
    private double final_stipend_weight = 1.0 / 6.0;

    private Button save_weights_btn;
    private Button cancel_weights_btn;
    private Button reset_weights_btn;

    weightsReaderDB.WeightsReaderDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_weights_settings);
        //UI
        yearly_salary_weight = (EditText) findViewById(R.id.yearly_salary_weight);
        yearly_bonus_weight = (EditText) findViewById(R.id.yearly_bonus_weight);
        stock_weight = (EditText) findViewById(R.id.stock_weight);
        home_weight = (EditText) findViewById(R.id.home_weight);
        holiday_weight = (EditText) findViewById(R.id.holiday_weight);
        stipend_weight = (EditText) findViewById(R.id.stipend_weight);

        save_weights_btn = (Button) findViewById(R.id.saveButtonID);
        cancel_weights_btn = (Button) findViewById(R.id.navigate_to_main_menu);
        reset_weights_btn = (Button) findViewById(R.id.resetweightsbutton);

        dbHelper = new weightsReaderDB.WeightsReaderDbHelper(EditWeightsSettings.this);
        try {
            populateWeights();
        }
        catch (SQLiteException e) {
            yearly_salary_weight.setText(String.valueOf(1));
            yearly_bonus_weight.setText(String.valueOf(1));
            stock_weight.setText(String.valueOf(1));
            home_weight.setText(String.valueOf(1));
            holiday_weight.setText(String.valueOf(1));
            stipend_weight.setText(String.valueOf(1));
        }

        save_weights_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        cancel_weights_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        reset_weights_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.resetWeights();
                yearly_salary_weight.setText(String.valueOf(1));
                yearly_bonus_weight.setText(String.valueOf(1));
                stock_weight.setText(String.valueOf(1));
                home_weight.setText(String.valueOf(1));
                holiday_weight.setText(String.valueOf(1));
                stipend_weight.setText(String.valueOf(1));
            }
        });

    }

    public void populateWeights() {
        // This isn't working for some reason.
        int[] weights = dbHelper.getWeights();
        System.out.println(weights.toString());

        yearly_salary_weight.setText(String.valueOf(weights[0]));
        yearly_bonus_weight.setText(String.valueOf(weights[1]));
        stock_weight.setText(String.valueOf(weights[2]));
        home_weight.setText(String.valueOf(weights[3]));
        holiday_weight.setText(String.valueOf(weights[4]));
        stipend_weight.setText(String.valueOf(weights[5]));

        final_yearly_salary_weight = weights[0] / 6.0;
        final_yearly_bonus_weight = weights[1] / 6.0;
        final_stock_weight = weights[2] / 6.0;
        final_home_weight = weights[3] / 6.0;
        final_holiday_weight = weights[4] / 6.0;
        final_stipend_weight = weights[5] / 6.0;
    }

    //private void showError(String message) {
    //    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    //}
    public void save(){
        boolean errorBool = false;

        String AYS = yearly_salary_weight.getText().toString();
        Integer annual_yearly_salary = 0;
        if(AYS.isEmpty()){
            yearly_salary_weight.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            annual_yearly_salary = Integer.parseInt(AYS);
            yearly_salary_weight.setError(null);
        }

        String AYB = yearly_bonus_weight.getText().toString();
        Integer annual_yearly_bonus = 0;
        if(AYB.isEmpty()){
            yearly_bonus_weight.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            annual_yearly_bonus = Integer.parseInt(AYB);
            yearly_bonus_weight.setError(null);
        }

        String CSO = stock_weight.getText().toString();
        Integer company_stock_offers = 0;
        if(CSO.isEmpty()){
            stock_weight.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            company_stock_offers = Integer.parseInt(CSO);
            stock_weight.setError(null);
        }

        String HBP = home_weight.getText().toString();
        Integer home_buying_program = 0;
        if(HBP.isEmpty()){
            home_weight.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            home_buying_program = Integer.parseInt(HBP);
            home_weight.setError(null);
        }

        String PCH = holiday_weight.getText().toString();
        Integer personal_choice_holidays = 0;
        if(PCH.isEmpty()){
            holiday_weight.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            personal_choice_holidays = Integer.parseInt(PCH);
            holiday_weight.setError(null);
        }

        String MIS = stipend_weight.getText().toString();
        Integer monthly_internet_stipend = 0;
        if(MIS.isEmpty()){
            stipend_weight.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            monthly_internet_stipend = Integer.parseInt(MIS);
            stipend_weight.setError(null);
        }

        if(!errorBool) {
            //weightsReaderDB.WeightsReaderDbHelper dbHelper = new weightsReaderDB.WeightsReaderDbHelper(EditWeightsSettings.this);
            dbHelper.addWeights(
                    annual_yearly_salary,
                    annual_yearly_bonus,
                    company_stock_offers,
                    home_buying_program,
                    personal_choice_holidays,
                    monthly_internet_stipend
            );

            final_yearly_salary_weight = annual_yearly_salary / 6.0;
            final_yearly_bonus_weight = annual_yearly_bonus / 6.0;
            final_stock_weight = company_stock_offers / 6.0;
            final_home_weight = home_buying_program / 6.0;
            final_holiday_weight = personal_choice_holidays / 6.0;
            final_stipend_weight = monthly_internet_stipend / 6.0;

            finish();
        }


        /*

        String yearly_salary_weight_input = yearly_salary_weight.getText().toString().trim();
        String yearly_bonus_weight_input = yearly_bonus_weight.getText().toString().trim();
        String stock_weight_input = stock_weight.getText().toString().trim();
        String home_weight_input = home_weight.getText().toString().trim();
        String holiday_weight_input = holiday_weight.getText().toString().trim();
        String stipend_weight_input = stipend_weight.getText().toString().trim();

        if (yearly_salary_weight_input.isEmpty() || yearly_bonus_weight_input.isEmpty() || stock_weight_input.isEmpty() || home_weight_input.isEmpty() || holiday_weight_input.isEmpty() || stipend_weight_input.isEmpty()) {
            showError("Please enter all numbers.");
            return;
        }

        try {
            double ys_weight = Double.parseDouble(yearly_salary_weight_input);
            double yb_weight = Double.parseDouble(yearly_bonus_weight_input);
            double st_weight = Double.parseDouble(stock_weight_input);
            double hom_weight = Double.parseDouble(home_weight_input);
            double hol_weight = Double.parseDouble(holiday_weight_input);
            double sti_weight = Double.parseDouble(stipend_weight_input);

            double sum = ys_weight + yb_weight + st_weight + hom_weight + hol_weight + sti_weight;

            if (sum != 1.0) {
                showError("Sum of numbers must be 1.");
            } else {
                final_yearly_salary_weight = ys_weight;
                final_yearly_bonus_weight = yb_weight;
                final_stock_weight = st_weight;
                final_stipend_weight = sti_weight;
                final_holiday_weight = hol_weight;
                final_home_weight = hom_weight;
                Toast.makeText(this, "Numbers are valid! You have saved those number!", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            showError("Invalid number format. Please enter valid numbers.");
        }
        */
    }

    public double getFinalYearlySalaryWeight() {
        return final_yearly_salary_weight;
    }
    public double getFinalYearlyBonusWeight() {
        return final_yearly_bonus_weight;
    }
    public double getFinalStockWeight() {
        return final_stock_weight;
    }
    public double getFinalHolidayWeight() {
        return final_holiday_weight;
    }
    public double getFinalHomeWeight() {
        return final_home_weight;
    }
    public double getFinalStipendWeight() {
        return final_stipend_weight;
    }

    public void navigateToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
