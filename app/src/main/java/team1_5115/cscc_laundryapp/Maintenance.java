package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Maintenance extends AppCompatActivity implements MaintenanceIssueFragment.OnFragmentInteractionListener, MaintenanceConfirmFragment.OnFragmentInteractionListener {
    int selectdMachineId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("id")) {
            selectdMachineId = extras.getInt("id");
            Button icon = (Button)findViewById(selectdMachineId);
            icon.setAlpha(1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MaintenanceIssueFragment issueFragment = new MaintenanceIssueFragment();
        transaction.add(R.id.maintenance_washer_container, issueFragment, "issue_fragment");
        transaction.commit();
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
        Toast.makeText(this.getBaseContext(), "A maintenance request has sent", Toast.LENGTH_LONG).show();
        finish();
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

}
