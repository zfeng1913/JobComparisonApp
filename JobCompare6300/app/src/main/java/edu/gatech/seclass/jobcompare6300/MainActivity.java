package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void navigateToCurrentJobDetails(View view) {
        Intent intent = new Intent(this, EnterCurrentJobDetails.class);
        startActivity(intent);
    }
    public void navigateToNewJobOffers(View view) {
        Intent intent = new Intent(this, EnterNewJobOffers.class);
        startActivity(intent);
    }
    public void navigateToWeightsAdjustment(View view) {
        Intent intent = new Intent(this, EditWeightsSettings.class);
        startActivity(intent);
    }

    public void navigateToJobComparison(View view) {
        Intent intent = new Intent(this, JobComparison.class);
        startActivity(intent);
    }
}