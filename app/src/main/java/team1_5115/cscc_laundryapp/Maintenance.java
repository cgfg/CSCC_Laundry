package team1_5115.cscc_laundryapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Maintenance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.maintenance_radioButton1:
                if (checked) {
                    Toast.makeText(this, "You selected Problem 1", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.maintenance_radioButton2:
                if (checked) {
                    Toast.makeText(this.getBaseContext(), "You selected Problem 2", Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.maintenance_radioButton3:
                if (checked) {
                    Toast.makeText(this.getBaseContext(),"You selected Problem 3", Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.maintenance_radioButton4:
                if (checked) {
                    Toast.makeText(this.getBaseContext(),"You selected Other", Toast.LENGTH_LONG).show();
                    break;
                }
        }
    }

    public void onSubmitButtonClicked(View view) {
        //check if there is radio button selected
        RadioGroup rg = (RadioGroup) findViewById(R.id.maintenance_radioGroup);
        String message;
        final int isChecked = rg.getCheckedRadioButtonId();
        if ( isChecked < 0) {
            message = "Please select one problem";
        } else {
            message = "A maintenance request has been sent.";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        if (isChecked >= 0)
                            finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onCancelButtonClicked(View view) {
        finish();
    }
}
