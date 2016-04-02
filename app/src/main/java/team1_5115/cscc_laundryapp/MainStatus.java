package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_status);
    }

    public void machineClickHandler(View view) {
        Intent intent = new Intent(MainStatus.this,PopupMachineSelect.class);
        startActivity(intent);

    }
}
