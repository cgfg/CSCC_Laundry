package team1_5115.cscc_laundryapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings extends AppCompatActivity {

    List<String> groupList;
    ExpandableListView expListView;
    Map<String, List<Integer>> childList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_main);

        createGroupList();
        createChildList();

        expListView = (ExpandableListView) findViewById(R.id.expandableListView);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, childList);
        expListView.setAdapter(expListAdapter);


        //setGroupIndicatorToRight();

//        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//                final String selected = (String) expListAdapter.getChild(
//                        groupPosition, childPosition);
//                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
//                        .show();
//
//                return true;
//            }
//        });
    }

    private void createGroupList(){
        groupList = new ArrayList<>();
        groupList.add("Account Setting");
        groupList.add("Notifications");
        groupList.add("Preferences");
    }

    private void createChildList(){
        childList = new HashMap<>();
        for(String groupName : groupList){
            childList.put(groupName, new ArrayList<Integer>());
        }
        childList.get("Notifications").add(R.layout.child_item_notification);
        childList.get("Preferences").add(R.layout.child_preferences);
        childList.get("Account Setting").add(R.layout.child_item_act_setting);
    }

    public void onRadioButtonClicked(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.maintenance_radio_group);
        RadioButton radio_1 = (RadioButton) findViewById(R.id.issues_1_radio);
        RadioButton radio_2 = (RadioButton) findViewById(R.id.issues_2_radio);
        RadioButton radio_3 = (RadioButton) findViewById(R.id.issues_3_radio);
        radio_1.setTypeface(null, Typeface.NORMAL);
        radio_2.setTypeface(null, Typeface.NORMAL);
        radio_3.setTypeface(null, Typeface.NORMAL);
        RadioButton newRadio = (RadioButton) radioGroup.findViewById(view.getId());
        newRadio.setTypeface(null, Typeface.BOLD);
    }

}
