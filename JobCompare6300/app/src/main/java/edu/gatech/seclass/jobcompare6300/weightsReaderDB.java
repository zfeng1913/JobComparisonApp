package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

//Inspired FROM: https://developer.android.com/training/data-storage/sqlite#java
public class weightsReaderDB {
    private weightsReaderDB() {}

    public static class weightsEntry implements BaseColumns {
        //AYS + AYB + (CSO/3) + HBP + (PCH * AYS / 260) + (MIS*12)
        public static final String TABLE_NAME = "weights";
        public static final String _ID = "_ID";
        public static final String AYS = "annual_yearly_salary_weight";
        public static final String AYB = "annual_yearly_bonus_weight";
        public static final String CSO = "company_stock_offers_weight";
        public static final String HBP = "home_buying_program_fund_weight";
        public static final String PCH = "personal_choice_holidays_weight";
        public static final String MIS = "monthly_internet_stipend_weight";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + weightsEntry.TABLE_NAME + " (" +
                    weightsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + weightsEntry.AYS + " INTEGER, "
                    + weightsEntry.AYB + " INTEGER, "
                    + weightsEntry.CSO + " INTEGER, "
                    + weightsEntry.HBP + " INTEGER, "
                    + weightsEntry.PCH + " INTEGER, "
                    + weightsEntry.MIS + " INTEGER);";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + weightsEntry.TABLE_NAME;

    public static class WeightsReaderDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        private Context context;
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "weightsReader.db";

        public WeightsReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);

            //SET DEFAULT VALUES TO 1!
            ContentValues values = new ContentValues();
            values.put(weightsEntry.AYS, 1);
            values.put(weightsEntry.AYB, 1);
            values.put(weightsEntry.CSO, 1);
            values.put(weightsEntry.HBP, 1);
            values.put(weightsEntry.PCH, 1);
            values.put(weightsEntry.MIS, 1);
            db.insert(weightsReaderDB.weightsEntry.TABLE_NAME, null, values);
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

        public void addWeights(int AYS, int AYB, int CSO, int HBP, int PCH, int MIS) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(SQL_DELETE_ENTRIES);
            db.execSQL(SQL_CREATE_ENTRIES);
            ContentValues values = new ContentValues();

            values.put(weightsEntry.AYS, AYS);
            values.put(weightsEntry.AYB, AYB);
            values.put(weightsEntry.CSO, CSO);
            values.put(weightsEntry.HBP, HBP);
            values.put(weightsEntry.PCH, PCH);
            values.put(weightsEntry.MIS, MIS);

            long result = db.insert(weightsReaderDB.weightsEntry.TABLE_NAME, null, values);
            if (result == -1) {
                Toast.makeText(context, "Error saving weights.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Weights entered", Toast.LENGTH_SHORT).show();
            }
        }

        public int[] getWeights() {
            int[] weights = new int[6];
            SQLiteDatabase db = this.getReadableDatabase();

            //Toast.makeText(context, "Retrieving Weights", Toast.LENGTH_SHORT).show();

            String query = "SELECT * FROM " + weightsEntry.TABLE_NAME; //+ " WHERE " + weightsEntry._ID + " = 1";

            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()) {
                weights[0] = cursor.getInt(cursor.getColumnIndexOrThrow(weightsEntry.AYS));
                weights[1] = cursor.getInt(cursor.getColumnIndexOrThrow(weightsEntry.AYB));
                weights[2] = cursor.getInt(cursor.getColumnIndexOrThrow(weightsEntry.CSO));
                weights[3] = cursor.getInt(cursor.getColumnIndexOrThrow(weightsEntry.HBP));
                weights[4] = cursor.getInt(cursor.getColumnIndexOrThrow(weightsEntry.PCH));
                weights[5] = cursor.getInt(cursor.getColumnIndexOrThrow(weightsEntry.MIS));
                //Toast.makeText(context, "Retrieved Weights", Toast.LENGTH_SHORT).show();
            }

            return weights;
        }

        public void resetWeights() {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(SQL_DELETE_ENTRIES);
            db.execSQL(SQL_CREATE_ENTRIES);
            ContentValues values = new ContentValues();

            values.put(weightsEntry.AYS, 1);
            values.put(weightsEntry.AYB, 1);
            values.put(weightsEntry.CSO, 1);
            values.put(weightsEntry.HBP, 1);
            values.put(weightsEntry.PCH, 1);
            values.put(weightsEntry.MIS, 1);

            long result = db.insert(weightsReaderDB.weightsEntry.TABLE_NAME, null, values);

            if (result == -1) {
                Toast.makeText(context, "Error resetting weights.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Weights all reset to 1.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //(another) Source for help: https://www.youtube.com/watch?v=hJPk50p7xwA&list=PLSrm9z4zp4mGK0g_0_jxYGgg3os9tqRUQ&index=1

}
