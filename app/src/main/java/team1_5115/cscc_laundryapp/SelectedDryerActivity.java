package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SelectedDryerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_machine_dryer);
    }

    public void onMaintenanceSelected(View view) {
        Intent intent = new Intent(SelectedDryerActivity.this, MaintenanceDryer.class);
        startActivity(intent);
    }

    public void onCycleSelected(View view) {
        Intent intent = new Intent(SelectedDryerActivity.this, CycleSelectDryer.class);
        startActivity(intent);
    }
}
