package team1_5115.cscc_laundryapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainStatus extends AppCompatActivity {

    @SuppressLint("InlinedApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        setContentView(R.layout.activity_main_status);
        // start threads to update machine status
        ScheduledThreadPoolExecutor updateThreadPool = new ScheduledThreadPoolExecutor(1);
        updateThreadPool.scheduleAtFixedRate(new updateMachineStatusCallable(), 0, 1, TimeUnit.SECONDS);

    }

    private class updateMachineStatusCallable implements Runnable {
        Handler handler = new Handler();
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    LaundryMachines laundryMachines = LaundryMachines.getInstance();
                    // washer timers
                    TextView washer_text_1 = (TextView) findViewById(R.id.washer_text_1);
                    TextView washer_text_2 = (TextView) findViewById(R.id.washer_text_2);
                    TextView washer_text_3 = (TextView) findViewById(R.id.washer_text_3);
                    TextView washer_text_4 = (TextView) findViewById(R.id.washer_text_4);
                    washer_text_1.setText(laundryMachines.getWasherStatus(1));
                    washer_text_2.setText(laundryMachines.getWasherStatus(2));
                    washer_text_3.setText(laundryMachines.getWasherStatus(3));
                    washer_text_4.setText(laundryMachines.getWasherStatus(4));
                    // dryer timers
                    TextView dryer_text_1 = (TextView) findViewById(R.id.dryer_text_1);
                    TextView dryer_text_2 = (TextView) findViewById(R.id.dryer_text_2);
                    TextView dryer_text_3 = (TextView) findViewById(R.id.dryer_text_3);
                    TextView dryer_text_4 = (TextView) findViewById(R.id.dryer_text_4);
                    dryer_text_1.setText(laundryMachines.getDryerStatus(1));
                    dryer_text_2.setText(laundryMachines.getDryerStatus(2));
                    dryer_text_3.setText(laundryMachines.getDryerStatus(3));
                    dryer_text_4.setText(laundryMachines.getDryerStatus(4));

//                    // washer icons
//                    Button washer_button_1 = (Button) findViewById(R.id.washer_1);
//                    Button washer_button_2 = (Button) findViewById(R.id.washer_2);
//                    Button washer_button_3 = (Button) findViewById(R.id.washer_3);
//                    Button washer_button_4 = (Button) findViewById(R.id.washer_4);
//                    washer_button_1.setBackground(Drawable.createFromPath(laundryMachines.getWasherStatusIcon(1)));
//                    washer_button_2.setBackground(Drawable.createFromPath(laundryMachines.getWasherStatusIcon(2)));
//                    washer_button_3.setBackground(Drawable.createFromPath(laundryMachines.getWasherStatusIcon(3)));
//                    washer_button_4.setBackground(Drawable.createFromPath(laundryMachines.getWasherStatusIcon(4)));
//
//                    // dryer icons
//                    Button dryer_button_1 = (Button) findViewById(R.id.dryer_1);
//                    Button dryer_button_2 = (Button) findViewById(R.id.dryer_2);
//                    Button dryer_button_3 = (Button) findViewById(R.id.dryer_3);
//                    Button dryer_button_4 = (Button) findViewById(R.id.dryer_4);
//                    dryer_button_1.setBackground(Drawable.createFromPath(laundryMachines.getDryerStatusIcon(1)));
//                    dryer_button_2.setBackground(Drawable.createFromPath(laundryMachines.getDryerStatusIcon(2)));
//                    dryer_button_3.setBackground(Drawable.createFromPath(laundryMachines.getDryerStatusIcon(3)));
//                    dryer_button_4.setBackground(Drawable.createFromPath(laundryMachines.getDryerStatusIcon(4)));
                }
            });
        }
    }

    public void onMachineClicked(View view) {
        Intent intent = new Intent(MainStatus.this,SelectedMachineActivity.class);
        intent.putExtra("id", view.getId());
        startActivity(intent);
    }

    public void onDryerClicked(View view) {
        Intent intent = new Intent(MainStatus.this,SelectedDryerActivity.class);
        intent.putExtra("id", view.getId());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                gotoSettingsScreen();
                return true;
            case R.id.payment:
                gotoPaymentScreen();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void gotoSettingsScreen(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void gotoPaymentScreen(){
        Intent intent = new Intent(this, Payment.class);
        startActivity(intent);
    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        ActionBar.LayoutParams lp1 = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        View customNav = LayoutInflater.from(this).inflate(R.layout.main_screen_action_bar, null); // layout which contains your button.
        actionBar.setCustomView(customNav, lp1);
    }
}
