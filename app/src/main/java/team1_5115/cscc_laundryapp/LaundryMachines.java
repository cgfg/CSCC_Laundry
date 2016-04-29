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
    private String[] dryer_status = new String[4];
    private CountDownTimer[] dryer_timers = new CountDownTimer[4];
    LaundryMachines() {
        washer_status[0] = "10 min";
        washer_status[1] = "15 min";
        washer_status[2] = "FREE";
        washer_status[3] = "REPAIR";
        dryer_status[0] = "FREE";
        dryer_status[1] = "FREE";
        dryer_status[2] = "FREE";
        dryer_status[3] = "FREE";
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
                washer_timers[1] = null;
            }

        }.start();
        washer_timers[2] = null;
        washer_timers[3] = null;
    }

    // return the correct washer drawable for a given washer
    public String getWasherStatusIcon(int washerId) {
        switch (getWasherStatus(washerId)) {
            case "FREE":
                return "w_free";
            case "REPAIR":
                return "w_broken";
            default:
                return "w_busy";
        }
    }

    // return the correct washer drawable for a given washer
    public String getDryerStatusIcon(int dryerId) {
        switch (getDryerStatus(dryerId)) {
            case "FREE":
                return "d_free";
            case "REPAIR":
                return "d_broken";
            default:
                return "d_busy";
        }
    }

    public String getWasherStatus(int washerId) {
        if (washerId >0 && washerId <= 4) {
            return washer_status[washerId - 1];
        }
        else {
            return "ERROR";
        }
    }

    public String getDryerStatus(int dryerId) {
        if (dryerId > 0 && dryerId <= 4) {
            return dryer_status[dryerId-1];
        }
        else {
            return "ERROR";
        }
    }

    public Boolean setWasherTimer(int washerId, Long time){
        final int id = washerId-1;
        if (id >= 0 && id < 4 && washer_timers[id] == null) {
            washer_timers[washerId] = new CountDownTimer(time, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    washer_status[id] = String.format("%d min", millisUntilFinished / 60000);
                }

                @Override
                public void onFinish() {
                    washer_status[id] = "FREE";
                    washer_timers[id] = null;
                }
            }.start();
            return true;
        } else {
            return false;
        }
    }


    public Boolean setDryerTimer(int dryerId, Long time){
        final int id = dryerId-1;
        if (id >= 0 && id < 4 && washer_timers[id] == null) {
            washer_timers[id] = new CountDownTimer(time, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    dryer_status[id] = String.format("%d min", millisUntilFinished / 60000);
                }

                @Override
                public void onFinish() {
                    dryer_status[id] = "FREE";
                    dryer_timers[id] = null;
                }
            }.start();
            return true;
        } else {
            return false;
        }
    }

    public static LaundryMachines getInstance(){
        if (machines == null) {
            machines = new LaundryMachines();
        }
        return machines;
    }
}
