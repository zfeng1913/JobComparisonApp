package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PageTransfer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_transfer);
    }

    public void navigateToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void navigateToEnterNewOffer(View view) {
        Intent intent = new Intent(this, EnterNewJobOffers.class);
        startActivity(intent);
    }

    public void navigateToSelectJobForComparison(View view) {
        Intent intent = new Intent(this, JobComparison.class);
        startActivity(intent);
    }
}
