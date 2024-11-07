package edu.gatech.seclass.jobcompare6300;
//DEPRICATED : SEE REPLACEMENT AT FeedReaderContract.java
/*
// SOURCE: https://www.digitalocean.com/community/tutorials/android-sqlite-database-example-tutorial
// BEGIN CODE FROM ^

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "JOBS";

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

    static final String DB_NAME = "ALLJOBS.DB";

    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CURRENTJOB + "BOOLEAN, " + TITLE + " TEXT NOT NULL, " + COMPANY + " TEXT NOT NULL, " + CITY + " TEXT NOT NULL, " +
            STATE + " TEXT NOT NULL, " + LIVINGCOST + " DOUBLE NOT NULL, " + SALARY + " DOUBLE NOT NULL, " + BONUS + " DOUBLE NOT NULL, " +
            OPTIONS + " DOUBLE NOT NULL, " + HOME + " DOUBLE NOT NULL, " + HOLIDAYS + " INTEGER NOT NULL, " + INTERNET + " DOUBLE NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public List<Jobs> getAllJobs() {
        List<Jobs> jobList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Jobs job = new Jobs();
                job.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                job.setState(cursor.getString(cursor.getColumnIndexOrThrow(STATE)));
                job.setCity(cursor.getString(cursor.getColumnIndexOrThrow(CITY)));
                job.setYearlySalary(cursor.getDouble(cursor.getColumnIndexOrThrow(SALARY)));
                job.setYearlyBonus(cursor.getDouble(cursor.getColumnIndexOrThrow(BONUS)));
                job.setCostOfLiving(cursor.getInt(cursor.getColumnIndexOrThrow(LIVINGCOST)));
                job.setNumberOfStockOptionsSharesOffered(cursor.getDouble(cursor.getColumnIndexOrThrow(OPTIONS)));
                job.setHomeBuyingProgramFund(cursor.getInt(cursor.getColumnIndexOrThrow(HOME)));
                job.setPersonalChoiceHolidays(cursor.getInt(cursor.getColumnIndexOrThrow(HOLIDAYS)));
                job.setMonthlyInternetStipend(cursor.getInt(cursor.getColumnIndexOrThrow(INTERNET)));
                jobList.add(job);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return jobList;
    }



}
*/
