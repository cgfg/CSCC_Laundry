package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
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
        expListView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

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


}
