package team1_5115.cscc_laundryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainStatus extends AppCompatActivity {

    @SuppressLint("InlinedApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        setContentView(R.layout.activity_main_status);
        loadBalanceValue(this);
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

    private void loadBalanceValue(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        String balanceVal = sharedPref.getString("balanceVal", "");
        if (balanceVal.equals("")){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("balanceVal", "0.0");
            editor.commit();
            balanceVal = "0.0";
        }
        TextView textView = (TextView) findViewById(R.id.main_screen_balanceVal);
        textView.setText(balanceVal);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        String balanceVal = sharedPref.getString("balanceVal", "");
        TextView textView = (TextView) findViewById(R.id.main_screen_balanceVal);
        textView.setText(balanceVal);
    }
}
