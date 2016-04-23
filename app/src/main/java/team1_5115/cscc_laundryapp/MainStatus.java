package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_status);
    }

    public void machineClickHandler(View view) {
        Intent intent = new Intent(MainStatus.this,PopupMachineSelect.class);
//        switch (view.getId()) {
//            case R.id.dryer1Button:
//                intent.putExtra("dryerId", 1);
//                break;
//            case R.id.dryer2Button:
//                intent.putExtra("dryerId", 2);
//                break;
//            case R.id.dryer3Button:
//                intent.putExtra("dryerId", 3);
//                break;
//            case R.id.dryer4Button:
//                intent.putExtra("dryerId", 4);
//                break;
//            case R.id.washer1Button:
//                intent.putExtra("washerId", 1);
//                break;
//            case R.id.washer2Button:
//                intent.putExtra("washerId", 2);
//                break;
//            case R.id.washer3Button:
//                intent.putExtra("washerId", 3);
//                break;
//            case R.id.washer4Button:
//                intent.putExtra("washerId", 4);
//                break;
//        }
//        startActivity(intent);
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
}
