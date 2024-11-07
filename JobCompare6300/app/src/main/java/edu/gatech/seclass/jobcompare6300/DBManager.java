package edu.gatech.seclass.jobcompare6300;
/*

DEPRICATED : SEE REPLACEMENT AT FeedReaderContract.java

// SOURCE: https://www.digitalocean.com/community/tutorials/android-sqlite-database-example-tutorial
// BEGIN CODE FROM ^

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Boolean iscurrentjob, String title, String company, String city, String state, int cost_of_living, double yearly_salary, double yearly_bonus,
                       double number_of_stock_option_shares_offered, double home_buying_program_fund, double personal_choice_holidays,
                       double monthly_internet_stipend) {
        ContentValues contentValue = new ContentValues();

        contentValue.put(DatabaseHelper.CURRENTJOB, iscurrentjob);
        contentValue.put(DatabaseHelper.TITLE, title);
        contentValue.put(DatabaseHelper.COMPANY, company);
        contentValue.put(DatabaseHelper.CITY, city);
        contentValue.put(DatabaseHelper.STATE, state);
        contentValue.put(DatabaseHelper.LIVINGCOST, cost_of_living);
        contentValue.put(DatabaseHelper.SALARY, yearly_salary);
        contentValue.put(DatabaseHelper.BONUS, yearly_bonus);
        contentValue.put(DatabaseHelper.OPTIONS, number_of_stock_option_shares_offered);
        contentValue.put(DatabaseHelper.HOME, home_buying_program_fund);
        contentValue.put(DatabaseHelper.HOLIDAYS, personal_choice_holidays);
        contentValue.put(DatabaseHelper.INTERNET, monthly_internet_stipend);

        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TITLE, DatabaseHelper.COMPANY,
                DatabaseHelper.CITY , DatabaseHelper.STATE , DatabaseHelper.LIVINGCOST , DatabaseHelper.SALARY , DatabaseHelper.BONUS ,
                DatabaseHelper.OPTIONS , DatabaseHelper.HOME , DatabaseHelper.HOLIDAYS , DatabaseHelper.INTERNET};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, Boolean iscurrentjob, String title, String company, String city, String state, int cost_of_living, double yearly_salary, double yearly_bonus,
                      double number_of_stock_option_shares_offered, double home_buying_program_fund, double personal_choice_holidays,
                      double monthly_internet_stipend) {
        ContentValues contentValues = new ContentValues();


        contentValues.put(DatabaseHelper.CURRENTJOB, iscurrentjob);
        contentValues.put(DatabaseHelper.TITLE, title);
        contentValues.put(DatabaseHelper.COMPANY, company);
        contentValues.put(DatabaseHelper.CITY, city);
        contentValues.put(DatabaseHelper.STATE, state);
        contentValues.put(DatabaseHelper.LIVINGCOST, cost_of_living);
        contentValues.put(DatabaseHelper.SALARY, yearly_salary);
        contentValues.put(DatabaseHelper.BONUS, yearly_bonus);
        contentValues.put(DatabaseHelper.OPTIONS, number_of_stock_option_shares_offered);
        contentValues.put(DatabaseHelper.HOME, home_buying_program_fund);
        contentValues.put(DatabaseHelper.HOLIDAYS, personal_choice_holidays);
        contentValues.put(DatabaseHelper.INTERNET, monthly_internet_stipend);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

    // SOURCE: https://www.digitalocean.com/community/tutorials/android-sqlite-database-example-tutorial
// BEGIN CODE FROM ^

}

 */