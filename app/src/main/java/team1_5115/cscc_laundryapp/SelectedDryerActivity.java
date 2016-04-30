package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SelectedDryerActivity extends AppCompatActivity {
    int selectdMachineId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_machine_dryer);
        // start threads to update machine status
        ScheduledThreadPoolExecutor updateThreadPool = new ScheduledThreadPoolExecutor(1);
        updateThreadPool.scheduleAtFixedRate(new updateMachineStatusCallable(), 0, 1, TimeUnit.SECONDS);

        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("id")) {
            selectdMachineId = extras.getInt("id");
            Button icon = (Button)findViewById(selectdMachineId);
            if (icon != null) icon.setAlpha(1);
        }
        int id = 0;
        switch(selectdMachineId) {
            case R.id.dryer_1:
                id = 1;
                break;
            case R.id.dryer_2:
                id = 2;
                break;
            case R.id.dryer_3:
                id = 3;
                break;
            case R.id.dryer_4:
                id = 4;
                break;
            default:
                break;
        }
        LaundryMachines laundryMachines = LaundryMachines.getInstance();
        String status = laundryMachines.getDryerStatus(id);
        Handler handler = new Handler();
        if (status.equals("REPAIR")) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Button quick_start_button = (Button) findViewById(R.id.quick_cycle_button);
                    if (quick_start_button != null) quick_start_button.setEnabled(false);
                    Button select_cycle_button = (Button) findViewById(R.id.selected_cycle_button);
                    if (select_cycle_button != null) select_cycle_button.setEnabled(false);
                    Button maintenance_button = (Button) findViewById(R.id.maintenance_button);
                    if (maintenance_button != null) maintenance_button.setEnabled(false);
                    Button notify_user_button = (Button) findViewById(R.id.notify_user_button);
                    if (notify_user_button != null) notify_user_button.setEnabled(false);
                }
            });
        } else if (status.equals("FREE")) {
            Button notify_user_button = (Button) findViewById(R.id.notify_user_button);
            if (notify_user_button != null) notify_user_button.setEnabled(false);
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Button quick_start_button = (Button) findViewById(R.id.quick_cycle_button);
                    if (quick_start_button != null) quick_start_button.setEnabled(false);
                    Button select_cycle_button = (Button) findViewById(R.id.selected_cycle_button);
                    if (select_cycle_button != null) select_cycle_button.setEnabled(false);
                }
            });
        }
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

    public void onMaintenanceSelected(View view) {
        Intent intent = new Intent(SelectedDryerActivity.this, MaintenanceDryer.class);
        intent.putExtra("id", selectdMachineId);
        startActivity(intent);
    }

    public void onCycleSelected(View view) {
        Intent intent = new Intent(SelectedDryerActivity.this, CycleSelectDryer.class);
        intent.putExtra("id", selectdMachineId);
        startActivity(intent);
    }

    public void onQuickStartSelected(View view){
        Intent intent = new Intent(SelectedDryerActivity.this, CycleSelectDryer.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", selectdMachineId);
        bundle.putBoolean("isQuickStart", true);
        intent.putExtras(bundle);
        startActivity(intent);
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
}
