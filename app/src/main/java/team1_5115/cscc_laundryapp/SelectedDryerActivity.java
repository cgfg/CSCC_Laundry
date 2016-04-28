package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SelectedDryerActivity extends AppCompatActivity {
    int selectdMachineId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_machine_dryer);
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("id")) {
            selectdMachineId = extras.getInt("id");
            Button icon = (Button)findViewById(selectdMachineId);
            icon.setAlpha(1);
        }
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
