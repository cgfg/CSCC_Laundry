package team1_5115.cscc_laundryapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

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
        childList.get("Account Setting").add(R.layout.child_item_act_setting);
        childList.get("Notifications").add(R.layout.child_item_notification);
        childList.get("Preferences").add(R.layout.child_preferences);
    }

}
