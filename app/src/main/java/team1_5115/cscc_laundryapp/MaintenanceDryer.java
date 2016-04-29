package team1_5115.cscc_laundryapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MaintenanceDryer extends AppCompatActivity implements MaintenanceIssueFragment.OnFragmentInteractionListener, MaintenanceConfirmFragment.OnFragmentInteractionListener {
    int selectdMachineId = 0;
    private int button_id_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_dryer);
        // start threads to update machine status
        ScheduledThreadPoolExecutor updateThreadPool = new ScheduledThreadPoolExecutor(1);
        updateThreadPool.scheduleAtFixedRate(new updateMachineStatusCallable(), 0, 1, TimeUnit.SECONDS);

        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("id")) {
            selectdMachineId = extras.getInt("id");
            Button icon = (Button)findViewById(selectdMachineId);
            icon.setAlpha(1);

            button_id_num = Integer.parseInt(icon.getTag().toString());
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MaintenanceIssueFragment issueFragment = new MaintenanceIssueFragment();
        transaction.add(R.id.maintenance_dryer_container, issueFragment, "dryer_fragment");
        transaction.commit();
    }

    private class updateMachineStatusCallable implements Runnable {
        Handler handler = new Handler();
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    LaundryMachines laundryMachines = LaundryMachines.getInstance();
                    // dryers
                    TextView dryer_text_1 = (TextView) findViewById(R.id.dryer_text_1);
                    TextView dryer_text_2 = (TextView) findViewById(R.id.dryer_text_2);
                    TextView dryer_text_3 = (TextView) findViewById(R.id.dryer_text_3);
                    TextView dryer_text_4 = (TextView) findViewById(R.id.dryer_text_4);
                    dryer_text_1.setText(laundryMachines.getDryerStatus(1));
                    dryer_text_2.setText(laundryMachines.getDryerStatus(2));
                    dryer_text_3.setText(laundryMachines.getDryerStatus(3));
                    dryer_text_4.setText(laundryMachines.getDryerStatus(4));

                    // dryer icons
                    Button dryer_button_1 = (Button) findViewById(R.id.dryer_1);
                    Button dryer_button_2 = (Button) findViewById(R.id.dryer_2);
                    Button dryer_button_3 = (Button) findViewById(R.id.dryer_3);
                    Button dryer_button_4 = (Button) findViewById(R.id.dryer_4);
                    switch (laundryMachines.getDryerStatusIcon(1)) {
                        case "d_free":
                            dryer_button_1.setBackgroundResource(R.drawable.d_free);
                            break;
                        case "d_broken":
                            dryer_button_1.setBackgroundResource(R.drawable.d_broken);
                            break;
                        default:
                            dryer_button_1.setBackgroundResource(R.drawable.d_busy);
                            break;
                    }
                    switch (laundryMachines.getDryerStatusIcon(2)) {
                        case "d_free":
                            dryer_button_2.setBackgroundResource(R.drawable.d_free);
                            break;
                        case "d_broken":
                            dryer_button_2.setBackgroundResource(R.drawable.d_broken);
                            break;
                        default:
                            dryer_button_2.setBackgroundResource(R.drawable.d_busy);
                            break;
                    }
                    switch (laundryMachines.getDryerStatusIcon(3)) {
                        case "d_free":
                            dryer_button_3.setBackgroundResource(R.drawable.d_free);
                            break;
                        case "d_broken":
                            dryer_button_3.setBackgroundResource(R.drawable.d_broken);
                            break;
                        default:
                            dryer_button_3.setBackgroundResource(R.drawable.d_busy);
                            break;
                    }
                    switch (laundryMachines.getDryerStatusIcon(4)) {
                        case "d_free":
                            dryer_button_4.setBackgroundResource(R.drawable.d_free);
                            break;
                        case "d_broken":
                            dryer_button_4.setBackgroundResource(R.drawable.d_broken);
                            break;
                        default:
                            dryer_button_4.setBackgroundResource(R.drawable.d_busy);
                            break;
                    }
                }
            });
        }
    }

    public void onIssueSubmitButtonClicked(View view) {
        MaintenanceIssueFragment oldFragment = (MaintenanceIssueFragment) getSupportFragmentManager().findFragmentById(R.id.maintenance_issue_fragment);
        MaintenanceConfirmFragment newFragment = new MaintenanceConfirmFragment();
        Bundle args = new Bundle();
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.maintenance_dryer_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onConfirmSubmitButtonClicked(View view) {
        LaundryMachines laundryMachines = LaundryMachines.getInstance();
        laundryMachines.issueDryerMaintenanceRequest(button_id_num);
        Toast.makeText(this.getBaseContext(), "A maintenance request has issued", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MaintenanceDryer.this, MainStatus.class);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.maintenance_radio_group);
        RadioButton radio_1 = (RadioButton) findViewById(R.id.issues_1_radio);
        RadioButton radio_2 = (RadioButton) findViewById(R.id.issues_2_radio);
        RadioButton radio_3 = (RadioButton) findViewById(R.id.issues_3_radio);
        radio_1.setTypeface(null, Typeface.NORMAL);
        radio_2.setTypeface(null, Typeface.NORMAL);
        radio_3.setTypeface(null, Typeface.NORMAL);
        RadioButton newRadio = (RadioButton) radioGroup.findViewById(view.getId());
        newRadio.setTypeface(null, Typeface.BOLD);
    }

    public void onCancelButtonClicked(View view) {
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.child_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(this, MainStatus.class);
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void resetPassword(View view) {
        String message = "A new password has sent to your email address";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
