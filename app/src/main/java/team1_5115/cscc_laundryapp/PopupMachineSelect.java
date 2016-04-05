package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Adam on 4/1/2016.
 */
public class PopupMachineSelect extends MainStatus{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_machine_options);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .75),(int) (height * .35));
        Bundle extras = getIntent().getExtras();
        String machineId;
        if (extras.containsKey("dryerId")) {
            machineId = "Dryer " + extras.getInt("dryerId");
        } else if (extras.containsKey("washerId")) {
            machineId = "Washer " + extras.getInt("washerId");
        } else {
            machineId = "X";
        }
        TextView machineOptionsText = (TextView) findViewById(R.id.machineOptionsText);
        if (machineOptionsText != null)
            machineOptionsText.setText(machineId);
    }

    /* called when user presses 'Use Machine' button from popup window
    update machine status so proper machine is updated to in use by user
    redirect to activity_main_status
    */
    public void clickUseMachine(View view) {

    }

    /* called when user presses 'Maintenance Request' button
    redirect user to maintenance screen
     */
    public void clickMaintenanceRequest(View view) {
        Intent i = new Intent(this, Maintenance.class);
        //[TODO] pass machine id/name to the maintenance screen
        startActivity(i);
    }
}
