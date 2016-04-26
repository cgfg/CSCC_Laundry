package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Adam on 4/1/2016.
 */
public class PopupMachineSelect extends MainStatus {
    public static int totalTime = 0;
    public static int deductMoney = 0;
    private String machineId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_select);

//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//        getWindow().setLayout((int) (width * .75),(int) (height * .35));
//        Bundle extras = getIntent().getExtras();
//        if (extras.containsKey("dryerId")) {
//            machineId = "Dryer " + extras.getInt("dryerId");
//        } else if (extras.containsKey("washerId")) {
//            machineId = "Washer " + extras.getInt("washerId");
//        } else {
//            machineId = "Unknown";
//        }
//        TextView machineOptionsText = (TextView) findViewById(R.id.machineOptionsText);
//        if (machineOptionsText != null)
//            machineOptionsText.setText(machineId);
//
//        getWindow().setLayout((int) (width * .75), (int) (height * .35));
    }

    /* called when user presses 'Use Machine' button from popup window
    update machine status so proper machine is updated to in use by user
    redirect to activity_main_status
    */
    public void clickUseMachine(View view) {
        popUpCycleOptions();
    }

    /* called when user presses 'Maintenance Request' button
    redirect user to maintenance screen
     */
    public void clickMaintenanceRequest(View view) {
        Intent i = new Intent(this, Maintenance.class);
        i.putExtra("MachineId", machineId);
        startActivity(i);
        finish();
    }

    public void popUpCycleOptions() {
        View popUpView = getLayoutInflater().inflate(R.layout.activity_cycle_select, null);
        PopupWindow popUp = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popUp.showAtLocation(popUpView, Gravity.CENTER, 0, 0);
        Button button = (Button) popUpView.findViewById(R.id.cycle_button_submit);
        final RadioGroup radioGroup = (RadioGroup) popUpView.findViewById(R.id.cycle_radioGroup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int isChecked = radioGroup.getCheckedRadioButtonId();
                if (isChecked < 0) {
                    totalTime = 0;
                    deductMoney = 0;
                }
                finish();
            }
        });
    }

    public void onCycleRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.cycle_radioButton1:
                if (checked) {
                    totalTime = 30;
                    deductMoney = 2;
                    break;
                }
            case R.id.cycle_radioButton2:
                if (checked) {
                    totalTime = 45;
                    deductMoney = 4;
                    break;
                }
            case R.id.cycle_radioButton3:
                if (checked) {
                    totalTime = 60;
                    deductMoney = 5;
                    break;
                }
        }
    }

}
