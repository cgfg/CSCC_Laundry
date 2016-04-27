package team1_5115.cscc_laundryapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<Integer>> childItems;
    private List<String> groupItems;

    public ExpandableListAdapter(Activity context, List<String> groupItems,
                                 Map<String, List<Integer>> childItems) {
        this.context = context;
        this.childItems = childItems;
        this.groupItems = groupItems;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return childItems.get(groupItems.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final int layoutID = (Integer) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);

        switch(layoutID){
            case R.layout.child_item_act_setting: setAccountSetting(convertView); break;
            case R.layout.child_item_notification: setNotifications(convertView); break;
            case R.layout.child_preferences: setPreferences(convertView); break;
        }
        return convertView;
    }

    private void setAccountSetting(final View convertView){
        Button edit = (Button)convertView.findViewById(R.id.editButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView et1 = ((TextView)convertView.findViewById(R.id.usernameEdit));
                et1.setEnabled(true);
                EditText et2 = ((EditText)convertView.findViewById(R.id.emailEdit));
                et2.setEnabled(true);
                EditText et3 = ((EditText)convertView.findViewById(R.id.phoneNumEdit));
                et3.setEnabled(true);
                EditText et4 = ((EditText)convertView.findViewById(R.id.passwordEdit));
                et4.setEnabled(true);
                EditText et5 = ((EditText)convertView.findViewById(R.id.confirmPasswordEdit));
                et5.setEnabled(true);
            }
        });

        Button submit = (Button)convertView.findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView et1 = ((TextView) convertView.findViewById(R.id.usernameEdit));
                et1.setEnabled(false);
                EditText et2 = ((EditText)convertView.findViewById(R.id.emailEdit));
                et2.setEnabled(false);
                EditText et3 = ((EditText)convertView.findViewById(R.id.phoneNumEdit));
                et3.setEnabled(false);
                EditText et4 = ((EditText)convertView.findViewById(R.id.passwordEdit));
                et4.setEnabled(false);
                EditText et5 = ((EditText)convertView.findViewById(R.id.confirmPasswordEdit));
                et5.setEnabled(false);
            }
        });
    }

    private void setNotifications(View convertView){
//        TextView textView = (TextView) convertView.findViewById(R.id.textView11);
//        textView.setText("notification");
    }

    private void setPreferences(View convertView){
        //To Do
    }

    public int getChildrenCount(int groupPosition) {
        return childItems.get(groupItems.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return groupItems.get(groupPosition);
    }

    public int getGroupCount() {
        return groupItems.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String groupName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.settings_group_item, null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.groupItem);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(groupName);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}