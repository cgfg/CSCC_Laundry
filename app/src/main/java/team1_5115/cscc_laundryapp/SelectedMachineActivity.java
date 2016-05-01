package team1_5115.cscc_laundryapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SelectedMachineActivity extends AppCompatActivity {
    int selectdMachineId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_machine);
        // start threads to update machine status
        ScheduledThreadPoolExecutor updateThreadPool = new ScheduledThreadPoolExecutor(1);
        updateThreadPool.scheduleAtFixedRate(new updateMachineStatusCallable(), 0, 1, TimeUnit.SECONDS);
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("id")) {
            selectdMachineId = extras.getInt("id");
            Button icon = (Button)findViewById(selectdMachineId);
            if (icon != null) icon.setAlpha(1);
        }
        int tmp = 0;
        switch(selectdMachineId) {
            case R.id.washer_1:
                tmp = 1;
                break;
            case R.id.washer_2:
                tmp = 2;
                break;
            case R.id.washer_3:
                tmp = 3;
                break;
            case R.id.washer_4:
                tmp = 4;
                break;
            default:
                    break;
        }
        final LaundryMachines laundryMachines = LaundryMachines.getInstance();
        final int id = tmp;
        String status = laundryMachines.getWasherStatus(tmp);
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
                    if (laundryMachines.userTrackedWashers[id-1]) {
                        Button notify_user_button = (Button) findViewById(R.id.notify_user_button);
                        if (notify_user_button != null) notify_user_button.setEnabled(false);
                    }
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

    public void onQuickStartSelected(View view){
        Intent intent = new Intent(SelectedMachineActivity.this, PopupMachineSelect.class);
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
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onNotifyUserClicked(View view) {
        String message = "A notification has sent to the user";
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
