package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectedMachineActivity extends AppCompatActivity {
    int selectdMachineId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_machine);
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("id")) {
            selectdMachineId = extras.getInt("id");
            Button icon = (Button)findViewById(selectdMachineId);
            if (icon != null) icon.setAlpha(1);
        }
    }

    public void onMaintenanceSelected(View view) {
        Intent intent = new Intent(SelectedMachineActivity.this, Maintenance.class);
        intent.putExtra("id", selectdMachineId);
        startActivity(intent);
    }

    public void onCycleSelected(View view) {
        Intent intent = new Intent(SelectedMachineActivity.this, PopupMachineSelect.class);
        intent.putExtra("id", selectdMachineId);
        startActivity(intent);
    }
}
