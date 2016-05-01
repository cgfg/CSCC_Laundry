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

public class Maintenance extends AppCompatActivity implements MaintenanceIssueFragment.OnFragmentInteractionListener, MaintenanceConfirmFragment.OnFragmentInteractionListener {
    int selectdMachineId = 0;
    private int button_id_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
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
        transaction.add(R.id.maintenance_washer_container, issueFragment, "issue_fragment");
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
                    TextView washer_text_1 = (TextView) findViewById(R.id.washer_text_1);
                    TextView washer_text_2 = (TextView) findViewById(R.id.washer_text_2);
                    TextView washer_text_3 = (TextView) findViewById(R.id.washer_text_3);
                    TextView washer_text_4 = (TextView) findViewById(R.id.washer_text_4);
                    washer_text_1.setText(laundryMachines.getWasherStatus(1));
                    washer_text_2.setText(laundryMachines.getWasherStatus(2));
                    washer_text_3.setText(laundryMachines.getWasherStatus(3));
                    washer_text_4.setText(laundryMachines.getWasherStatus(4));

                    // washer icons
                    Button washer_button_1 = (Button) findViewById(R.id.washer_1);
                    Button washer_button_2 = (Button) findViewById(R.id.washer_2);
                    Button washer_button_3 = (Button) findViewById(R.id.washer_3);
                    Button washer_button_4 = (Button) findViewById(R.id.washer_4);
                    switch (laundryMachines.getWasherStatusIcon(1)) {
                        case "w_free":
                            washer_button_1.setBackgroundResource(R.drawable.w_free);
                            break;
                        case "w_broken":
                            washer_button_1.setBackgroundResource(R.drawable.w_broken);
                            break;
                        default:
                            washer_button_1.setBackgroundResource(R.drawable.w_busy);
                            break;
                    }
                    switch (laundryMachines.getWasherStatusIcon(2)) {
                        case "w_free":
                            washer_button_2.setBackgroundResource(R.drawable.w_free);
                            break;
                        case "w_broken":
                            washer_button_2.setBackgroundResource(R.drawable.w_broken);
                            break;
                        default:
                            washer_button_2.setBackgroundResource(R.drawable.w_busy);
                            break;
                    }
                    switch (laundryMachines.getWasherStatusIcon(3)) {
                        case "w_free":
                            washer_button_3.setBackgroundResource(R.drawable.w_free);
                            break;
                        case "w_broken":
                            washer_button_3.setBackgroundResource(R.drawable.w_broken);
                            break;
                        default:
                            washer_button_3.setBackgroundResource(R.drawable.w_busy);
                            break;
                    }
                    switch (laundryMachines.getWasherStatusIcon(4)) {
                        case "w_free":
                            washer_button_4.setBackgroundResource(R.drawable.w_free);
                            break;
                        case "w_broken":
                            washer_button_4.setBackgroundResource(R.drawable.w_broken);
                            break;
                        default:
                            washer_button_4.setBackgroundResource(R.drawable.w_busy);
                            break;
                    }
                }
            });
        }
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

    public void onIssueSubmitButtonClicked(View view) {
        MaintenanceIssueFragment oldFragment = (MaintenanceIssueFragment) getSupportFragmentManager().findFragmentById(R.id.maintenance_issue_fragment);
        MaintenanceConfirmFragment newFragment = new MaintenanceConfirmFragment();
        Bundle args = new Bundle();
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.maintenance_washer_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onConfirmSubmitButtonClicked(View view) {
        LaundryMachines laundryMachines = LaundryMachines.getInstance();
        laundryMachines.issueWasherMaintenanceRequest(button_id_num);
        Toast.makeText(this.getBaseContext(), "A maintenance request has issued", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Maintenance.this, MainStatus.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
