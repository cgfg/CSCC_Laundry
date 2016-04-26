package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectedMachineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_machine);
    }

    public void onMaintenanceSelected(View view) {
        Intent intent = new Intent(SelectedMachineActivity.this, Maintenance.class);
        startActivity(intent);
    }

    public void onCycleSelected(View view) {
        Intent intent = new Intent(SelectedMachineActivity.this, PopupMachineSelect.class);
        startActivity(intent);
    }
}
