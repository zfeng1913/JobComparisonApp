package edu.gatech.seclass.jobcompare6300;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobComparison extends AppCompatActivity {
    private TableLayout tableLayoutJobs_all;
    private TableLayout tableLayoutJobs_selected;
    private ArrayList<Jobs> selectedJobs = new ArrayList<>();
    private FeedReaderDbHelper jobsDBHelper;
    private weightsReaderDB.WeightsReaderDbHelper weightsDBHelper;
    private int[] weights = new int[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_comparison);
        tableLayoutJobs_all = findViewById(R.id.tableLayoutJobs);
        tableLayoutJobs_selected = findViewById(R.id.tableLayoutJobs_selected);

        //Step 1, retrieve all jobs from the database
        jobsDBHelper = new FeedReaderDbHelper(JobComparison.this);
        weightsDBHelper = new weightsReaderDB.WeightsReaderDbHelper(JobComparison.this);
        List<Jobs> all_jobs = jobsDBHelper.getAllJobs();
        weights = weightsDBHelper.getWeights();

        //Step 2, rank all jobs from the database
        List<Jobs> rankedJobs = rank_all_jobs(all_jobs);

        //Step 3, allow for user to select 2 jobs and move onto next screen

        // Display the first two job details in the table
        displayalljobs(rankedJobs);
    }

    public void navigateToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void displayalljobs(List<Jobs> jobList) {

        TableRow headerRow = new TableRow(this);
        List<String> stringList = new ArrayList<>();
        stringList.add("Button ");
        stringList.add("Title   ");
        stringList.add("Company    ");
        stringList.add("isCurrentJob   ");
        for (String col : stringList) {
            TextView header_text = new TextView(this);
            header_text.setText(col);
            headerRow.addView(header_text);
        }
        // Add the header row to the table
        tableLayoutJobs_all.addView(headerRow);

        for (Jobs job : jobList) {
            TableRow row = new TableRow(this);
            Button jobButton = new Button(this);
            jobButton.setOnClickListener(view -> handleJobSelection(job, jobButton));

            TextView titleTextView = new TextView(this);
            TextView companyTextView = new TextView(this);
            TextView CurrentJobView = new TextView(this);

            titleTextView.setText(job.getTitle());
            companyTextView.setText(job.getCompany());
            Log.d(job.getTitle(),job.isTheCurrentJob().toString());
            CurrentJobView.setText(String.valueOf(job.isTheCurrentJob()));
            row.addView(jobButton);
            row.addView(titleTextView);
            row.addView(companyTextView);
            row.addView(CurrentJobView);
            tableLayoutJobs_all.addView(row);
        }
    }

    public void navigateToCompare2Jobs(View view) {
        if (selectedJobs.size() >= 2) {
            displayJobDetails(selectedJobs);
        } else {
            Toast.makeText(this, "Please select at least two jobs.", Toast.LENGTH_SHORT).show();
        }
    }
    private void handleJobSelection(Jobs selectedJob, Button jobButton) {
        if (selectedJobs.contains(selectedJob)) {
            selectedJobs.remove(selectedJob);

            jobButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            if (selectedJobs.size() < 2) {
                selectedJobs.add(selectedJob);

                jobButton.setBackgroundColor(Color.parseColor("#00FF00"));
            } else {
                Toast.makeText(this, "You can select at most two jobs.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void displayJobDetails(List<Jobs> jobList) {

        TableRow headerRow = new TableRow(this);
        List<String> stringList = new ArrayList<>();
        stringList.add("Title");
        stringList.add("Company");
        stringList.add("City");
        stringList.add("State");
        stringList.add("Yearly Salary");
        stringList.add("Yearly Bonus");
        stringList.add("Stock Options");
        stringList.add("Home Buying fund");
        stringList.add("Holidays");
        stringList.add("Stipend");
        for (String col : stringList) {
            TextView header_text = new TextView(this);
            header_text.setText(col);
            headerRow.addView(header_text);
        }
        // Add the header row to the table
        tableLayoutJobs_selected.removeAllViews();
        tableLayoutJobs_selected.addView(headerRow);

        for (Jobs job : jobList) {
            // Create a new row for each job
            TableRow row = new TableRow(this);

            TextView titleTextView = new TextView(this);
            TextView companyTextView = new TextView(this);
            TextView cityTextView = new TextView(this);
            TextView stateTextView = new TextView(this);
            TextView yearlysalaryTextView = new TextView(this);
            TextView yearlybonusTextView = new TextView(this);
            TextView stockTextView = new TextView(this);
            TextView homeTextView = new TextView(this);
            TextView holidayTextView = new TextView(this);
            TextView stipendTextView = new TextView(this);

            titleTextView.setText(job.getTitle());
            companyTextView.setText(job.getCompany());
            cityTextView.setText(job.getCity());
            stateTextView.setText(job.getState());
            yearlysalaryTextView.setText(Double.toString(job.getYearlySalary()));
            yearlybonusTextView.setText(Double.toString(job.getYearlyBonus()));
            stockTextView.setText(Double.toString(job.getNumberOfStockOptionsSharesOffered()));
            homeTextView.setText(Double.toString(job.getHomeBuyingProgramFund()));
            holidayTextView.setText(Double.toString(job.getPersonalChoiceHolidays()));
            stipendTextView.setText(Double.toString(job.getMonthlyInternetStipend()));
            // Add more TextViews for other attributes

            // Add TextViews to the row
            row.addView(titleTextView);
            row.addView(companyTextView);
            row.addView(cityTextView);
            row.addView(stateTextView);
            row.addView(yearlysalaryTextView);
            row.addView(yearlybonusTextView);
            row.addView(stockTextView);
            row.addView(homeTextView);
            row.addView(holidayTextView);
            row.addView(stipendTextView);
            // Add the row to the table
            tableLayoutJobs_selected.addView(row);
        }
    }

    private List<Jobs> rank_all_jobs(List<Jobs> jobList) {
        //https://www.geeksforgeeks.org/sorting-hashmap-according-key-value-java/
        //https://www.javatpoint.com/how-to-sort-hashmap-by-value

        Map<Jobs, Double> jobsWitJobScore = new HashMap<Jobs, Double>();

        // Each job object will be unique, so they have to be the key.
        for(Jobs job: jobList) {
            double jobscore = calculateJobScore(job);
            jobsWitJobScore.put(job, jobscore);
        }

        List<Map.Entry<Jobs, Double>> jobscores_list = new ArrayList<>(jobsWitJobScore.entrySet());
        // reverse order because it needs to be descending order (best job first)
        jobscores_list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        // Sorting the list based on values
        List<Jobs> rankedJobs = new ArrayList<>();
        for(Map.Entry<Jobs, Double> entry : jobscores_list) {
            rankedJobs.add(entry.getKey());
            //remove log messages
            Log.d(entry.getKey().getTitle(),"Job Title");
        }

        return rankedJobs;
    }

    //https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values

    private double calculateJobScore(Jobs job) {
        // AYS + AYB + (CSO/3) + HBP + (PCH * AYS / 260) + (MIS*12)
        // For example, if the weights are 2 for the yearly salary, 2 for the yearly bonus,
        // 2 for Internet Stipend, and 1 for all other factors, the score would be computed as:
        //2/9 * AYS + 2/9 * AYB + 1/9 * (CSO/3) + 1/9 * HBP + 1/9 * (PCH * AYS / 260) + 2/9 * (MIS*12)

        double total = this.weights[0]
                + this.weights[1]
                + this.weights[2]
                + this.weights[3]
                + this.weights[4]
                + this.weights[5];

        double yearly_salary_weight = this.weights[0] / total;
        double yearly_bonus_weight = this.weights[1] / total;
        double stock_weight = this.weights[2] / total;
        double home_weight = this.weights[3] / total;
        double holiday_weight = this.weights[4] / total;
        double stipend_weight = this.weights[5] / total;

        double salary = job.getYearlySalary();
        double bonus = job.getYearlyBonus();
        double stock = job.getNumberOfStockOptionsSharesOffered();
        double homefund = job.getHomeBuyingProgramFund();
        int holidays = job.getPersonalChoiceHolidays();
        double internetstipend = job.getMonthlyInternetStipend();

        double AYS = salary * yearly_salary_weight;
        double AYB = bonus * yearly_bonus_weight;
        double CSO = stock * stock_weight;
        double HBP = homefund * home_weight;
        double PCH = holidays * holiday_weight;
        double MIS = internetstipend * stipend_weight;

        return AYS + AYB + (CSO / 3.0) + HBP + ((PCH * AYS) / 260.0) + (MIS * 12.0);
    }

}
