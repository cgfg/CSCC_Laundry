package team1_5115.cscc_laundryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

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

    private void setPreferences(final View convertView){
        final RadioGroup radioGroup = (RadioGroup)convertView.findViewById(R.id.preference_group);
        final ToggleButton superCycle = (ToggleButton) convertView.findViewById(R.id.preference5);
        final ToggleButton washerOrDryer = (ToggleButton) convertView.findViewById(R.id.preferenceWaherOrDryer);
        final Context context = convertView.getContext();
        final SharedPreferences sharedPref = context.getSharedPreferences(convertView.getResources().getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        int washerPreID = sharedPref.getInt("washerPreID", -1);
        int dryerPreID = sharedPref.getInt("dryerPreID", -1);
        if(washerPreID != -1){
            radioGroup.check(washerPreID);
            superCycle.setChecked(sharedPref.getBoolean("washerPreSuperCycle", false));
            changeTextColorOfCheckedButton(radioGroup, radioGroup.getCheckedRadioButtonId());
        }else{
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("washerPreID", radioGroup.getCheckedRadioButtonId());
            editor.commit();
        }
        if(dryerPreID != -1){
            radioGroup.check(dryerPreID);
            superCycle.setChecked(sharedPref.getBoolean("dryerPreSuperCycle", false));
        }else{
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("dryerPreID", radioGroup.getCheckedRadioButtonId());
            editor.commit();
        }
        washerOrDryer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    int dryerPreID = sharedPref.getInt("dryerPreID", -1);
                    radioGroup.check(dryerPreID);
                    superCycle.setChecked(sharedPref.getBoolean("dryerPreSuperCycle", false));
                }else{
                    int washerPreID = sharedPref.getInt("washerPreID", -1);
                    radioGroup.check(washerPreID);
                    superCycle.setChecked(sharedPref.getBoolean("washerPreSuperCycle", false));
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                changeTextColorOfCheckedButton(group, checkedId);
                RadioButton checkedButton = (RadioButton) group.findViewById(checkedId);
                SharedPreferences.Editor editor = sharedPref.edit();
                if(washerOrDryer.isChecked()){
                    editor.putString("dryerPreClothes", checkedButton.getText().toString());
                    editor.putInt("dryerPreID", checkedId);
                }else{
                    editor.putString("washerPreClothes", checkedButton.getText().toString());
                    editor.putInt("washerPreID", checkedId);
                }
                editor.commit();
            }
        });
        superCycle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Context context = convertView.getContext();
                SharedPreferences sharedPref = context.getSharedPreferences(convertView.getResources().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                if(washerOrDryer.isChecked()){
                    editor.putBoolean("dryerPreSuperCycle", isChecked);
                }else{
                    editor.putBoolean("washerPreSuperCycle", isChecked);
                }
                editor.commit();
            }
        });
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

    private void changeTextColorOfCheckedButton(RadioGroup group, int checkedId){
        ((RadioButton)group.findViewById(R.id.preference1)).setTypeface(null, Typeface.NORMAL);
        ((RadioButton)group.findViewById(R.id.preference2)).setTypeface(null, Typeface.NORMAL);
        ((RadioButton)group.findViewById(R.id.preference3)).setTypeface(null, Typeface.NORMAL);
        ((RadioButton)group.findViewById(R.id.preference4)).setTypeface(null, Typeface.NORMAL);
        RadioButton checkedButton = (RadioButton) group.findViewById(checkedId);
        checkedButton.setTypeface(null, Typeface.BOLD);
    }
}