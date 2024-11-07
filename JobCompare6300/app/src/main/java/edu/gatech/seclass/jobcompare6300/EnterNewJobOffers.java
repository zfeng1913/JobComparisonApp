package edu.gatech.seclass.jobcompare6300;

import static java.security.AccessController.getContext;

import android.content.ContentValues;
import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnterNewJobOffers extends AppCompatActivity {

    private Button save_offer_button;
    private Button cancel_offer_btn;
    private EditText offerTitle;
    private EditText offerCompany;
    private EditText offerCity;
    private EditText offerState;
    private EditText offerCostOfLiving;
    private EditText offerYearlySalary;
    private EditText offerYearlyBonus;
    private EditText offerStockOptionShares;
    private EditText offerHomeBuyingFund;
    private EditText offerPersonalHolidays;
    private EditText offerInternetStipend;
    private Jobs currentJob;
    private FeedReaderDbHelper dbHelper;
    //private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_job_offers);

        // UI components
        offerTitle = (EditText) findViewById(R.id.OfferTitleEntry);
        offerCompany = (EditText) findViewById(R.id.OfferCompanyEntry);
        offerCity = (EditText) findViewById(R.id.OfferCityEntry);
        offerState = (EditText) findViewById(R.id.OfferStateEntry);
        offerCostOfLiving = (EditText) findViewById(R.id.OfferLivingEntry);
        offerYearlySalary = (EditText) findViewById(R.id.OfferSalaryEntry);
        offerYearlyBonus = (EditText) findViewById(R.id.OfferBonusEntry);
        offerStockOptionShares = (EditText) findViewById(R.id.OfferStockEntry);
        offerHomeBuyingFund = (EditText) findViewById(R.id.offerHouseEntry);
        offerPersonalHolidays = (EditText) findViewById(R.id.OfferholidaysEntry);
        offerInternetStipend = (EditText) findViewById(R.id.OfferInternetEntry);

        save_offer_button = (Button) findViewById(R.id.navigate_to_page_transfer);
        cancel_offer_btn = (Button) findViewById(R.id.navigate_to_main_menu);

        dbHelper = new FeedReaderDbHelper(EnterNewJobOffers.this);


        // Button click listening
        save_offer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveJobOfferDetails(view);
            }
        });
        cancel_offer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private void saveJobOfferDetails(View view){

        //SOURCE: https://stackoverflow.com/questions/14721397/checking-if-a-string-is-empty-or-null-in-java
        //str != null && !str.trim().isEmpty()

        // First get all the values:
        Jobs thisOffer = new Jobs();
        boolean errorBool = false;

        //Toast.makeText(this, "test",Toast.LENGTH_SHORT).show();
        // null != ""

        String title = offerTitle.getText().toString();
        if(title.isEmpty()) {
            offerTitle.setError("Can't be an empty value!");
            errorBool = true;
        }
        else { offerTitle.setError(null); }

        String company = offerCompany.getText().toString();
        if(company.isEmpty()){
            offerCompany.setError("Can't be an empty value!");
            errorBool = true;
        }
        else { offerCompany.setError(null); }

        String city = offerCity.getText().toString();
        if(city.isEmpty()){
            offerCity.setError("Can't be an empty value!");
            errorBool = true;
        }
        else { offerCity.setError(null); }

        String state = offerState.getText().toString();
        if(state.isEmpty()){
            offerState.setError("Can't be an empty value!");
            errorBool = true;
        }
        else { offerState.setError(null); }

        String costOfLife = offerCostOfLiving.getText().toString();
        Integer costofliving = 0;
        if(costOfLife.isEmpty()){
            offerCostOfLiving.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            costofliving = Integer.parseInt(costOfLife);
            offerCostOfLiving.setError(null);
        }

        String yearlySalary = offerYearlySalary.getText().toString();
        Double salary = 0.0;
        if(yearlySalary.isEmpty()){
            offerYearlySalary.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            offerYearlySalary.setError(null);
            salary = Double.parseDouble(yearlySalary);
        }

        String yearlyBonus = offerYearlyBonus.getText().toString();
        Double bonus = 0.0;
        if(yearlyBonus.isEmpty()){
            offerYearlyBonus.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            offerYearlyBonus.setError(null);
            bonus = Double.parseDouble(yearlyBonus);
        }

        String stockOptions = offerStockOptionShares.getText().toString();
        Double stocks = 0.0;
        if(stockOptions.isEmpty()){
            offerStockOptionShares.setError("Can't be an empty value!");
            errorBool = true;
        }
        else {
            offerStockOptionShares.setError(null);
            stocks = Double.parseDouble(stockOptions);
        }

        String house = offerHomeBuyingFund.getText().toString();
        Double homefund = 0.0;
        if(house.isEmpty()){
            Toast.makeText(this, "Warning! Home Buying Fund is empty! Setting to 0 as default.",Toast.LENGTH_SHORT).show();
        } // * needs validation: NGT 15% of salary
        else {
            //Toast.makeText(this, "test",Toast.LENGTH_SHORT).show();
            homefund = Double.parseDouble(house);
            if(homefund > (0.15 * salary) || homefund < 0.00){
                offerHomeBuyingFund.setError("Needs to be a value between 0 and 15% of the salary");
                errorBool = true;
            }
            else { offerHomeBuyingFund.setError(null); }
        }

        String holi = offerPersonalHolidays.getText().toString();
        Integer holidays = 0;
        if(holi.isEmpty()){
            Toast.makeText(this, "Warning! Holidays are empty! Setting to 0 as default.",Toast.LENGTH_SHORT).show();
        } // * needs validation: [0, 20]
        else {
            holidays = Integer.parseInt(holi);
            if(holidays > 20 || holidays < 0){
                offerPersonalHolidays.setError("Needs to be a value between 0 and 20");
                errorBool = true;
            }
            else { offerPersonalHolidays.setError(null); }
        }

        String inter = offerInternetStipend.getText().toString();
        Double internet = 0.0;
        if(inter.isEmpty()){
            Toast.makeText(this, "Warning! Internet Stipend is empty! Setting to $0 as default.",Toast.LENGTH_SHORT).show();
        } // * needs validation: [$0.00, $75.00]
        else {
            internet = Double.parseDouble(inter);
            if(internet > 75.00 || internet < 0.00){
                offerInternetStipend.setError("Needs to be a value between $0.00 and $75.00");
                errorBool = true;
            }
            else { offerInternetStipend.setError(null); }
        }

        // IF it passes all those checks, then we can save it!
        if(!errorBool){
            thisOffer.setCurrentJob(false);
            thisOffer.setTitle(title);
            thisOffer.setCompany(company);
            thisOffer.setCity(city);
            thisOffer.setState(state);
            thisOffer.setCostOfLiving(costofliving);
            thisOffer.setYearlySalary(salary);
            thisOffer.setYearlyBonus(bonus);
            thisOffer.setNumberOfStockOptionsSharesOffered(stocks);
            thisOffer.setHomeBuyingProgramFund(homefund);
            thisOffer.setPersonalChoiceHolidays(holidays);
            thisOffer.setMonthlyInternetStipend(internet);

            // Gets the data repository in write mode
            dbHelper.addJob(

                    thisOffer.isTheCurrentJob(),
                    thisOffer.getTitle().trim(),
                    thisOffer.getCompany().trim(),
                    thisOffer.getCity(),
                    thisOffer.getState().trim(),
                    thisOffer.getCostOfLiving(),
                    thisOffer.getYearlySalary(),
                    thisOffer.getYearlyBonus(),
                    thisOffer.getNumberOfStockOptionsSharesOffered(),
                    thisOffer.getHomeBuyingProgramFund(),
                    thisOffer.getPersonalChoiceHolidays(),
                    thisOffer.getMonthlyInternetStipend()
            );

            navigateToPageTransfer(view);
            finish();
        }
    }

    public void navigateToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void navigateToPageTransfer(View view) {
        Intent intent = new Intent(this, PageTransfer.class);
        startActivity(intent);
    }



}
