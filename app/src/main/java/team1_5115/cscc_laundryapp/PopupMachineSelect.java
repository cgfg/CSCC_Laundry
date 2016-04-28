package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PopupMachineSelect extends AppCompatActivity implements CycleSelectFragment.OnFragmentInteractionListener, CycleConfirmFragment.OnFragmentInteractionListener{
    int selectdMachineId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cycle_select_washers);
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("id")) {
            selectdMachineId = extras.getInt("id");
            Button icon = (Button)findViewById(selectdMachineId);
            icon.setAlpha(1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CycleSelectFragment issueFragment = new CycleSelectFragment();
        transaction.add(R.id.cycle_select_washer_container, issueFragment, "issue_fragment");
        transaction.commit();

    }

    public void onRadioButtonClicked(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.cycle_radio_group);
        RadioButton radio_1 = (RadioButton) findViewById(R.id.cycle_1_radio);
        RadioButton radio_2 = (RadioButton) findViewById(R.id.cycle_2_radio);
        RadioButton radio_3 = (RadioButton) findViewById(R.id.cycle_3_radio);
        RadioButton radio_4 = (RadioButton) findViewById(R.id.cycle_4_radio);
        radio_1.setTypeface(null, Typeface.NORMAL);
        radio_2.setTypeface(null, Typeface.NORMAL);
        radio_3.setTypeface(null, Typeface.NORMAL);
        radio_4.setTypeface(null, Typeface.NORMAL);
        RadioButton newRadio = (RadioButton) radioGroup.findViewById(view.getId());
        newRadio.setTypeface(null, Typeface.BOLD);
    }

    public void onIssueSubmitButtonClicked(View view) {
        CycleSelectFragment oldFragment = (CycleSelectFragment) getSupportFragmentManager().findFragmentById(R.id.cycle_options_fragment);
        CycleConfirmFragment newFragment = new CycleConfirmFragment();
        Bundle args = new Bundle();
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.cycle_select_washer_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onConfirmSubmitButtonClicked(View view) {
        Toast.makeText(this.getBaseContext(), "A maintenance request has sent", Toast.LENGTH_LONG).show();
        finish();
    }

    public void onCancelButtonClicked(View view) {
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

//    public static int totalTime = 0;
//    public static int deductMoney = 0;
//    private String machineId = "";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cycle_select);
//
////        DisplayMetrics dm = new DisplayMetrics();
////        getWindowManager().getDefaultDisplay().getMetrics(dm);
////        int width = dm.widthPixels;
////        int height = dm.heightPixels;
////        getWindow().setLayout((int) (width * .75),(int) (height * .35));
////        Bundle extras = getIntent().getExtras();
////        if (extras.containsKey("dryerId")) {
////            machineId = "Dryer " + extras.getInt("dryerId");
////        } else if (extras.containsKey("washerId")) {
////            machineId = "Washer " + extras.getInt("washerId");
////        } else {
////            machineId = "Unknown";
////        }
////        TextView machineOptionsText = (TextView) findViewById(R.id.machineOptionsText);
////        if (machineOptionsText != null)
////            machineOptionsText.setText(machineId);
////
////        getWindow().setLayout((int) (width * .75), (int) (height * .35));
//    }
//
//    /* called when user presses 'Use Machine' button from popup window
//    update machine status so proper machine is updated to in use by user
//    redirect to activity_main_status
//    */
//    public void clickUseMachine(View view) {
//        popUpCycleOptions();
//    }
//
//    /* called when user presses 'Maintenance Request' button
//    redirect user to maintenance screen
//     */
//    public void clickMaintenanceRequest(View view) {
//        Intent i = new Intent(this, Maintenance.class);
//        i.putExtra("MachineId", machineId);
//        startActivity(i);
//        finish();
//    }
//
//    public void popUpCycleOptions() {
//        View popUpView = getLayoutInflater().inflate(R.layout.activity_cycle_select, null);
//        PopupWindow popUp = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
//        popUp.showAtLocation(popUpView, Gravity.CENTER, 0, 0);
//        Button button = (Button) popUpView.findViewById(R.id.cycle_button_submit);
//        final RadioGroup radioGroup = (RadioGroup) popUpView.findViewById(R.id.cycle_radioGroup);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final int isChecked = radioGroup.getCheckedRadioButtonId();
//                if (isChecked < 0) {
//                    totalTime = 0;
//                    deductMoney = 0;
//                }
//                finish();
//            }
//        });
//    }
//
//    public void onCycleRadioButtonClicked(View view) {
//        boolean checked = ((RadioButton) view).isChecked();
//        switch (view.getId()) {
//            case R.id.cycle_radioButton1:
//                if (checked) {
//                    totalTime = 30;
//                    deductMoney = 2;
//                    break;
//                }
//            case R.id.cycle_radioButton2:
//                if (checked) {
//                    totalTime = 45;
//                    deductMoney = 4;
//                    break;
//                }
//            case R.id.cycle_radioButton3:
//                if (checked) {
//                    totalTime = 60;
//                    deductMoney = 5;
//                    break;
//                }
//        }
//    }
