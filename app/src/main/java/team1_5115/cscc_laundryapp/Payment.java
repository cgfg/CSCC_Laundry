package team1_5115.cscc_laundryapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Payment extends AppCompatActivity {

    int moneyValue;
    int selectedAddValue = 0;
    int selectedDeductValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        final Spinner spinner1 = (Spinner) findViewById(R.id.moneyChoices1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.moneyChoices1, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

//        final Spinner spinner2 = (Spinner) findViewById(R.id.moneyChoices2);
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
//                R.array.moneyChoices2, android.R.layout.simple_spinner_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner2.setAdapter(adapter2);

        final TextView blanceValue = (TextView)findViewById(R.id.balanceValue);
        String[] str = blanceValue.getText().toString().split(" ");
        moneyValue = Integer.parseInt(str[1]);

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

//        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String value = spinner2.getSelectedItem().toString();
//                switch(value){
//                    case "2$": selectedDeductValue = 2; break;
//                    case "3$": selectedDeductValue = 3; break;
//                    case "5$": selectedDeductValue = 5; break;
//                    default: selectedDeductValue = 0; break;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        Button button = (Button)findViewById(R.id.submitMoney);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StringBuilder message = new StringBuilder();
                if(selectedAddValue > 0){
                    message.append("Add ").append(selectedAddValue).append(" $ to your account");
                }
                if(selectedDeductValue > 0){
                    if(selectedAddValue > 0) message.append(" and ");
                    message.append("Deduct ").append(selectedDeductValue).append(" $ from your account");
                }
                if(selectedAddValue == 0 && selectedDeductValue == 0){
                    message.append("Please add or deduct some money");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage(message)
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                moneyValue += (selectedAddValue - selectedDeductValue);
                                blanceValue.setText("Balance: " + Integer.toString(moneyValue) + " $");
                                selectedAddValue = selectedDeductValue = 0;
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}