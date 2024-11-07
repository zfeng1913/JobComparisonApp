package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " ("
                    + FeedReaderContract.FeedEntry._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FeedReaderContract.FeedEntry.CURRENTJOB + " INTEGER, "
                    + FeedReaderContract.FeedEntry.TITLE + " TEXT, "
                    + FeedReaderContract.FeedEntry.COMPANY + " TEXT, "
                    + FeedReaderContract.FeedEntry.CITY + " TEXT, "
                    + FeedReaderContract.FeedEntry.STATE + " TEXT, "
                    + FeedReaderContract.FeedEntry.LIVINGCOST + " INTEGER, "
                    + FeedReaderContract.FeedEntry.SALARY + " DOUBLE, "
                    + FeedReaderContract.FeedEntry.BONUS + " DOUBLE, "
                    + FeedReaderContract.FeedEntry.OPTIONS + " DOUBLE, "
                    + FeedReaderContract.FeedEntry.HOME + " DOUBLE, "
                    + FeedReaderContract.FeedEntry.HOLIDAYS + " INTEGER, "
                    + FeedReaderContract.FeedEntry.INTERNET + " DOUBLE);";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    //(another) Source for help: https://www.youtube.com/watch?v=hJPk50p7xwA&list=PLSrm9z4zp4mGK0g_0_jxYGgg3os9tqRUQ&index=1

    // If you change the database schema, you must increment the database version.
    private Context context;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addJob(
            Boolean isCurrentJob, String title, String company, String city,
            String state, Integer livingcost, Double salary, Double bonus,
            Double options, Double homefund, Integer holidays, Double internet
            ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d(title, isCurrentJob.toString());
        int isCurrentJobSql = 0;
        if(isCurrentJob) { isCurrentJobSql = 1; }
        values.put(FeedReaderContract.FeedEntry.CURRENTJOB,isCurrentJobSql);
        values.put(FeedReaderContract.FeedEntry.TITLE,title);
        values.put(FeedReaderContract.FeedEntry.COMPANY,company);
        values.put(FeedReaderContract.FeedEntry.CITY, city);
        values.put(FeedReaderContract.FeedEntry.STATE, state);
        values.put(FeedReaderContract.FeedEntry.LIVINGCOST, livingcost);
        values.put(FeedReaderContract.FeedEntry.SALARY, salary);
        values.put(FeedReaderContract.FeedEntry.BONUS, bonus);
        values.put(FeedReaderContract.FeedEntry.OPTIONS, options);
        values.put(FeedReaderContract.FeedEntry.HOME, homefund);
        values.put(FeedReaderContract.FeedEntry.HOLIDAYS, holidays);
        values.put(FeedReaderContract.FeedEntry.INTERNET, internet);

        long result = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,values);
        if(result == -1) {
            Toast.makeText(context,"Error saving job.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Job entry added.", Toast.LENGTH_SHORT).show();
        }
    }

    public List<Jobs> getAllJobs() {
        List<Jobs> jobList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Jobs job = new Jobs();
                boolean iscurrjob = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.CURRENTJOB)) == 1;
                Log.d( cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.TITLE)),
                        Integer.toString(cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.CURRENTJOB))) );
                job.setCurrentJob(iscurrjob);
                job.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.TITLE)));
                job.setCompany(cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COMPANY)));
                job.setState(cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.STATE)));
                job.setCity(cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.CITY)));
                job.setCostOfLiving(cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.LIVINGCOST)));
                job.setYearlySalary(cursor.getDouble(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.SALARY)));
                job.setYearlyBonus(cursor.getDouble(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.BONUS)));
                job.setNumberOfStockOptionsSharesOffered(cursor.getDouble(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.OPTIONS)));
                job.setHomeBuyingProgramFund(cursor.getDouble(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.HOME)));
                job.setPersonalChoiceHolidays(cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.HOLIDAYS)));
                job.setMonthlyInternetStipend(cursor.getDouble(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.INTERNET)));
                jobList.add(job);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return jobList;
    }

    public Jobs getCurrentJob() {
        Jobs currentJob = new Jobs();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntry.CURRENTJOB + " = TRUE";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            currentJob.setCurrentJob(true);
            currentJob.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.TITLE)));
            currentJob.setCompany(cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COMPANY)));
            currentJob.setState(cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.STATE)));
            currentJob.setCity(cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.CITY)));
            currentJob.setCostOfLiving(cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.LIVINGCOST)));
            currentJob.setYearlySalary(cursor.getDouble(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.SALARY)));
            currentJob.setYearlyBonus(cursor.getDouble(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.BONUS)));
            currentJob.setNumberOfStockOptionsSharesOffered(cursor.getDouble(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.OPTIONS)));
            currentJob.setHomeBuyingProgramFund(cursor.getDouble(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.HOME)));
            currentJob.setPersonalChoiceHolidays(cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.HOLIDAYS)));
            currentJob.setMonthlyInternetStipend(cursor.getDouble(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.INTERNET)));
        }
        return currentJob;
    }

    public long getCurrentJobID(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + FeedReaderContract.FeedEntry._ID + " FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntry.CURRENTJOB + " = TRUE";
        Cursor cursor = db.rawQuery(query, null);
        long id = 0;
        if(cursor.moveToFirst()) { id = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID)); }
        else { id = -1; }
        return id;
    }

    public void clearCurrentJob() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "DELETE FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntry.CURRENTJOB + " = TRUE";
        db.execSQL(query);
    }

}
