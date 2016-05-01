package team1_5115.cscc_laundryapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Payment extends AppCompatActivity {

    float moneyValue;
    int selectedAddValue = 0;
    boolean isRest = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        loadBalanceVal(this);
        final Spinner spinner1 = (Spinner) findViewById(R.id.moneyChoices1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.moneyChoices1, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        final TextView blanceValue = (TextView)findViewById(R.id.balanceValue);
        String[] str = blanceValue.getText().toString().split(" ");
        moneyValue = Float.parseFloat(str[1]);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = spinner1.getSelectedItem().toString();
                switch(value){
                    case "10 $": selectedAddValue = 10; break;
                    case "20 $": selectedAddValue = 20; break;
                    case "30 $": selectedAddValue = 30; break;
                    default: selectedAddValue = 0; break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button button = (Button)findViewById(R.id.submitMoney);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.resetBalanceVal);
                StringBuilder message = new StringBuilder();
                if(et.getText().toString().length() > 0){
                    message.append("Reset the balance to ").append(et.getText().toString()).append(" $ ?");
                    isRest = true;
                } else if(selectedAddValue > 0){
                    message.append("Add ").append(selectedAddValue).append(" $ to your account ?");
                    isRest = false;
                } else {
                    message.append("Please add value or reset the balance.");
                    isRest = false;
                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage(message)
                        .setCancelable(true)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(isRest){
                                    moneyValue = Float.parseFloat(((EditText)findViewById(R.id.resetBalanceVal)).getText().toString());
                                } else{
                                    moneyValue += selectedAddValue;
                                }
                                dialog.dismiss();
                                writeToPreferenceFile(builder.getContext());
                                blanceValue.setText("Balance: " + Float.toString(moneyValue) + " $");
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Window view = ((AlertDialog)dialog).getWindow();
                        view.setBackgroundDrawableResource(R.drawable.dialog_bk);
                    }
                });
                alert.show();
            }
        });
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

    private void writeToPreferenceFile(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("balanceVal", Float.toString(moneyValue));
        editor.commit();
    }

    private void loadBalanceVal(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        String balanceVal = sharedPref.getString("balanceVal", "");
        TextView textView = (TextView)findViewById(R.id.balanceValue);
        textView.setText(String.format("Balance: %s $", balanceVal));
    }
}