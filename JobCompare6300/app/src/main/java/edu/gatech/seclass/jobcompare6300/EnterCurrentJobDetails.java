package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterCurrentJobDetails extends AppCompatActivity {

    private EditText gbTitle;
    private EditText gbCompany;
    private EditText gbCity;
    private EditText gbState;
    private EditText gbCostOfLiving;
    private EditText gbYearlySalary;
    private EditText gbYearlyBonus;
    private EditText gbStockOptionShares;
    private EditText gbHomeBuyingFund;
    private EditText gbPersonalHolidays;
    private EditText gbInternetStipend;

    private Button save_btn;
    private Button cancel_btn;

    private Jobs currentJob;
    private FeedReaderDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_current_job);

        // UI components
        gbTitle = (EditText) findViewById(R.id.TitleTextID);
        gbCompany = (EditText) findViewById(R.id.CompanyTextID);
        gbCity = (EditText) findViewById(R.id.CityTextID);
        gbState = (EditText) findViewById(R.id.StateTextID);
        gbCostOfLiving = (EditText) findViewById(R.id.CostOfLivingNumberID);
        gbYearlySalary = (EditText) findViewById(R.id.YearlySalaryDecimalID);
        gbYearlyBonus = (EditText) findViewById(R.id.YearlyBonusDecimalID);
        gbStockOptionShares = (EditText) findViewById(R.id.StockOptionSharesDecimalID);
        gbHomeBuyingFund = (EditText) findViewById(R.id.HomeBuyingFundDecimalID);
        gbPersonalHolidays = (EditText) findViewById(R.id.PersonalChoiceHolidaysNumberID);
        gbInternetStipend = (EditText) findViewById(R.id.MonthlyInternetStipendNumberID);

        save_btn = (Button) findViewById(R.id.saveButtonID);
        cancel_btn = (Button) findViewById(R.id.cancelButtonID);

        //read from database
        dbHelper = new FeedReaderDbHelper(EnterCurrentJobDetails.this);
        try { // If there is a current job we can get it
            Toast.makeText(this, "Retrieving Current Job Data",Toast.LENGTH_SHORT).show();
            currentJob = getJobDetails();
            jobDetailsPopulate(this.currentJob);
        } catch(SQLiteException | CursorIndexOutOfBoundsException e) { // There is no current job
            Toast.makeText(this, "No Current Job Data Found",Toast.LENGTH_SHORT).show();
            currentJob = new Jobs();
            jobDetailsPopulate(this.currentJob);
        }

        // Button click listening
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveJobDetails();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public Jobs getJobDetails() {
        // Implement retrieval of job details from database
        // Return null if no job details found
        //FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(EnterCurrentJobDetails.this);
        this.currentJob = dbHelper.getCurrentJob();
        return this.currentJob;
    }

    private void jobDetailsPopulate(Jobs jobDetails) {
        gbTitle.setText(jobDetails.getTitle());
        gbCompany.setText(jobDetails.getCompany());
        gbCity.setText(jobDetails.getCity());
        gbState.setText(jobDetails.getState());
        gbCostOfLiving.setText(String.valueOf(jobDetails.getCostOfLiving()));
        gbYearlySalary.setText(String.valueOf(jobDetails.getYearlySalary()));
        gbYearlyBonus.setText(String.valueOf(jobDetails.getYearlyBonus()));
        gbStockOptionShares.setText(String.valueOf(jobDetails.getNumberOfStockOptionsSharesOffered()));
        gbHomeBuyingFund.setText(String.valueOf(jobDetails.getHomeBuyingProgramFund()));
        gbPersonalHolidays.setText(String.valueOf(jobDetails.getPersonalChoiceHolidays()));
        gbInternetStipend.setText(String.valueOf(jobDetails.getMonthlyInternetStipend()));
    }

    private void saveJobDetails() {
        Jobs jobDetailsSaved = new Jobs();
        boolean errorBool = false;

        String title = gbTitle.getText().toString();
        if(title.isEmpty()) {
            gbTitle.setError("Can't be an empty value!");
            errorBool = true;
        }
        else { gbTitle.setError(null); }

        String company = gbCompany.getText().toString();
        if(company.isEmpty()){
            gbCompany.setError("Can't be an empty value!");
            errorBool = true;
        }
        else { gbCompany.setError(null); }

        String city = gbCity.getText().toString();
        if(city.isEmpty()){
            gbCity.setError("Can't be an empty value!");
            errorBool = true;
        }
        else { gbCity.setError(null); }

        String state = gbState.getText().toString();
        if(state.isEmpty()){
            gbState.setError("Can't be an empty value!");
            errorBool = true;
        }
        else { gbState.setError(null); }

        String costOfLife = gbCostOfLiving.getText().toString();
        Integer costofliving = 0;
        if(costOfLife.isEmpty()){
            gbCostOfLiving.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            costofliving = Integer.parseInt(costOfLife);
            gbCostOfLiving.setError(null);
        }

        String yearlySalary = gbYearlySalary.getText().toString();
        Double salary = 0.0;
        if(yearlySalary.isEmpty()){
            gbYearlySalary.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            gbYearlySalary.setError(null);
            salary = Double.parseDouble(yearlySalary);
        }

        String yearlyBonus = gbYearlyBonus.getText().toString();
        Double bonus = 0.0;
        if(yearlyBonus.isEmpty()){
            gbYearlyBonus.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            gbYearlyBonus.setError(null);
            bonus = Double.parseDouble(yearlyBonus);
        }

        String stockOptions = gbStockOptionShares.getText().toString();
        Double stocks = 0.0;
        if(stockOptions.isEmpty()){
            gbStockOptionShares.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            gbStockOptionShares.setError(null);
            stocks = Double.parseDouble(stockOptions);
        }

        String house = gbHomeBuyingFund.getText().toString();
        Double homefund = 0.0;
        if(house.isEmpty()){
            Toast.makeText(this, "Warning! Home Buying Fund is empty! Setting to 0 as default.",Toast.LENGTH_SHORT).show();
        } // * needs validation: NGT 15% of salary
        else {
            //Toast.makeText(this, "test",Toast.LENGTH_SHORT).show();
            homefund = Double.parseDouble(house);
            if(homefund > (0.15 * salary) || homefund < 0.00){
                gbHomeBuyingFund.setError("Needs to be a value between 0 and 15% of the salary");
                errorBool = true;
            }
            else { gbHomeBuyingFund.setError(null); }
        }

        String holi = gbPersonalHolidays.getText().toString();
        Integer holidays = 0;
        if(holi.isEmpty()){
            Toast.makeText(this, "Warning! Holidays are empty! Setting to 0 as default.",Toast.LENGTH_SHORT).show();
        } // * needs validation: [0, 20]
        else {
            holidays = Integer.parseInt(holi);
            if(holidays > 20 || holidays < 0){
                gbPersonalHolidays.setError("Needs to be a value between 0 and 20");
                errorBool = true;
            }
            else { gbPersonalHolidays.setError(null); }
        }

        String inter = gbInternetStipend.getText().toString();
        Double internet = 0.0;
        if(inter.isEmpty()){
            Toast.makeText(this, "Warning! Internet Stipend is empty! Setting to $0 as default.",Toast.LENGTH_SHORT).show();
        } // * needs validation: [$0.00, $75.00]
        else {
            internet = Double.parseDouble(inter);
            if(internet > 75.00 || internet < 0.00){
                gbInternetStipend.setError("Needs to be a value between $0.00 and $75.00");
                errorBool = true;
            }
            else { gbInternetStipend.setError(null); }
        }

        if(!errorBool){
            jobDetailsSaved.setTitle(title);
            jobDetailsSaved.setCompany(company);
            jobDetailsSaved.setCity(city);
            jobDetailsSaved.setState(state);
            jobDetailsSaved.setCostOfLiving(costofliving);
            jobDetailsSaved.setYearlySalary(salary);
            jobDetailsSaved.setYearlyBonus(bonus);
            jobDetailsSaved.setNumberOfStockOptionsSharesOffered(stocks);
            jobDetailsSaved.setHomeBuyingProgramFund(homefund);
            jobDetailsSaved.setPersonalChoiceHolidays(holidays);
            jobDetailsSaved.setMonthlyInternetStipend(internet);
            jobDetailsSaved.setCurrentJob(true);
            // Gets the data repository in write mode
            //FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(EnterCurrentJobDetails.this);
            try { dbHelper.clearCurrentJob(); }
            catch(SQLiteException e) { } //do nothing

            dbHelper.addJob(
                    jobDetailsSaved.isTheCurrentJob(),
                    jobDetailsSaved.getTitle().trim(),
                    jobDetailsSaved.getCompany().trim(),
                    jobDetailsSaved.getCity().trim(),
                    jobDetailsSaved.getState().trim(),
                    jobDetailsSaved.getCostOfLiving(),
                    jobDetailsSaved.getYearlySalary(),
                    jobDetailsSaved.getYearlyBonus(),
                    jobDetailsSaved.getNumberOfStockOptionsSharesOffered(),
                    jobDetailsSaved.getHomeBuyingProgramFund(),
                    jobDetailsSaved.getPersonalChoiceHolidays(),
                    jobDetailsSaved.getMonthlyInternetStipend()
            );

            finish();
        }

    }

    public void navigateToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
