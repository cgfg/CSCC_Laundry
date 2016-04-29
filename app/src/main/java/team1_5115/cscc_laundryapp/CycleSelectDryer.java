package team1_5115.cscc_laundryapp;

import android.os.Bundle;
import android.widget.Button;

public class CycleSelectDryer extends MainStatus {
    private int selectedMachineId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_select_dryer);
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("id")) {
            selectedMachineId = extras.getInt("id");
            Button icon = (Button)findViewById(selectedMachineId);
            icon.setAlpha(1);
        }
    }
}
