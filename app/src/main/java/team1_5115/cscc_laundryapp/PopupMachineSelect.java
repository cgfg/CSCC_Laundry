package team1_5115.cscc_laundryapp;

<<<<<<< HEAD
=======
import android.content.Intent;
>>>>>>> origin/master
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by Adam on 4/1/2016.
 */
public class PopupMachineSelect extends MainStatus{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_machine_options);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .75),(int) (height * .35));
    }

    /* called when user presses 'Use Machine' button from popup window
    update machine status so proper machine is updated to in use by user
    redirect to activity_main_status
    */
    public void clickUseMachine(View view) {

    }

    /* called when user presses 'Maintenance Request' button
    redirect user to maintenance screen
     */
    public void clickMaintenanceRequest(View view) {
<<<<<<< HEAD

=======
        Intent i = new Intent(this, Maintenance.class);
        //[TODO] pass machine id/name to the maintenance screen
        startActivity(i);
>>>>>>> origin/master
    }
}
