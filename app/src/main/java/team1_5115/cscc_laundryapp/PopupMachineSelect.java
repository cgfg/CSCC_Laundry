package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PopupMachineSelect extends MainStatus {
    public static int totalTime = 0;
    public static int deductMoney = 0;
    private int selectedMachineId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_select);
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("id")) {
            selectedMachineId = extras.getInt("id");
            Button icon = (Button)findViewById(selectedMachineId);
            icon.setAlpha(1);
        }

    }


    public void onCycleRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.cycle_radioButton1:
                if (checked) {
                    totalTime = 30;
                    deductMoney = 2;
                    break;
                }
            case R.id.cycle_radioButton2:
                if (checked) {
                    totalTime = 45;
                    deductMoney = 4;
                    break;
                }
            case R.id.cycle_radioButton3:
                if (checked) {
                    totalTime = 60;
                    deductMoney = 5;
                    break;
                }
        }
    }

}
