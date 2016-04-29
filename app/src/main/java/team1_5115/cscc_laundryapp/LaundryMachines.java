package team1_5115.cscc_laundryapp;

import java.util.Timer;
import android.os.CountDownTimer;
/**
 * Created by chenxi on 4/28/2016.
 */
public class LaundryMachines {
    private static LaundryMachines machines = null;
    private String[] washer_status = new String[4];
    private CountDownTimer[] washer_timers = new CountDownTimer[4];
    LaundryMachines() {
        washer_status[0] = "10 min";
        washer_status[1] = "15 min";
        washer_status[2] = "FREE";
        washer_status[3] = "REPAIR";
        washer_timers[0] = new CountDownTimer(600000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                washer_status[0] = String.format("%d min", millisUntilFinished / 60000);
            }

            @Override
            public void onFinish() {
                washer_status[0] = "FREE";
            }
        }.start();
        washer_timers[1] = new CountDownTimer(900000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                washer_status[1] = String.format("%d min", millisUntilFinished / 60000);
            }

            @Override
            public void onFinish() {
                washer_status[1] = "FREE";
            }

        }.start();
        washer_timers[2] = null;
        washer_timers[3] = null;
    }

    public String getWasherStatus(int washerId) {
        if (washerId >0 && washerId <= 4)
            return washer_status[washerId-1];
        else {
            return "ERROR";
        }
    }

    public static LaundryMachines getInstance(){
        if (machines == null) {
            machines = new LaundryMachines();
        }
        return machines;
    }
}
