package team1_5115.cscc_laundryapp;

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
        Bundle extras = new Bundle();
        extras.putInt("id", selectdMachineId);

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
