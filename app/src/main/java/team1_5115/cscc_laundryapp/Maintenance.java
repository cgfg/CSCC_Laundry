package team1_5115.cscc_laundryapp;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Maintenance extends AppCompatActivity implements MaintenanceIssueFragment.OnFragmentInteractionListener, MaintenanceConfirmFragment.OnFragmentInteractionListener {
    private String machineId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MaintenanceIssueFragment issueFragment = new MaintenanceIssueFragment();
        transaction.add(R.id.maintenance_container, issueFragment, "issue_fragment");
        transaction.commit();

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.maintenance_radioButton1:
//                if (checked) {
////                    Toast.makeText(this, "You selected Problem 1", Toast.LENGTH_SHORT).show();
//                    break;
//                }
//            case R.id.maintenance_radioButton2:
//                if (checked) {
////                    Toast.makeText(this.getBaseContext(), "You selected Problem 2", Toast.LENGTH_LONG).show();
//                    break;
//                }
//            case R.id.maintenance_radioButton3:
//                if (checked) {
////                    Toast.makeText(this.getBaseContext(),"You selected Other", Toast.LENGTH_LONG).show();
//                    break;
//                }
//        }
    }

    public void onIssueSubmitButtonClicked(View view) {
        MaintenanceIssueFragment oldFragment = (MaintenanceIssueFragment) getSupportFragmentManager().findFragmentById(R.id.maintenance_issue_fragment);
        MaintenanceConfirmFragment newFragment = new MaintenanceConfirmFragment();
        Bundle args = new Bundle();
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.maintenance_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onConfirmSubmitButtonClicked(View view) {

    }

    public void onCancelButtonClicked(View view) {
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
