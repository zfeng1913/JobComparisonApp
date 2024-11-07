package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

//BEGIN CODE FROM: https://developer.android.com/training/data-storage/sqlite#java
public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "jobOffers";
        public static final String _ID = "_ID";
        public static final String CURRENTJOB = "currentjob";
        public static final String TITLE = "title";
        public static final String COMPANY = "company";
        public static final String CITY = "city";
        public static final String STATE = "state";
        public static final String LIVINGCOST = "cost_of_living";
        public static final String SALARY = "yearly_salary";
        public static final String BONUS = "yearly_bonus";
        public static final String OPTIONS = "number_of_stock_option_shares_offered";
        public static final String HOME = "home_buying_program_fund";
        public static final String HOLIDAYS = "personal_choice_holidays";
        public static final String INTERNET = "monthly_internet_stipend";
    }
}

//END CODE FROM: https://developer.android.com/training/data-storage/sqlite#java