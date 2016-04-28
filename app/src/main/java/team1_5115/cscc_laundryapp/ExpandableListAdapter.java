package team1_5115.cscc_laundryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
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
        Button bt = (Button)convertView.findViewById(R.id.saveButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(convertView.getContext());
                builder.setMessage("Your profile will be changed !")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Window view = ((AlertDialog)dialog).getWindow();
                        view.setBackgroundDrawableResource(R.drawable.dialog_bk);
                    }
                });
                dialog.show();
            }
        });
    }

    private void setNotifications(View convertView){
        //To Do
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
        if(isExpanded){
            convertView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.settings_item_selected));
        }else{
            convertView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.settings_item_unselected));
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