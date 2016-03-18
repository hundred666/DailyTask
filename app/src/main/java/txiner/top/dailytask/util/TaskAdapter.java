package txiner.top.dailytask.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import txiner.top.dailytask.R;

/**
 * Created by wzhuo on 2016/3/18.
 */
public class TaskAdapter extends BaseAdapter {

    Context context;
    ArrayList<Map<String, Object>> tasks;

    public TaskAdapter(Context context, ArrayList<Map<String, Object>> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.task_composent, null);
            viewHolder = new ViewHolder();
            viewHolder.taskName = (TextView) convertView.findViewById(R.id.task_name);
            viewHolder.taskContent = (TextView) convertView.findViewById(R.id.task_content);
            viewHolder.taskOver = (CheckBox) convertView.findViewById(R.id.task_over);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.taskName.setText((CharSequence) tasks.get(position).get("name"));
        viewHolder.taskContent.setText((CharSequence) tasks.get(position).get("content"));
        viewHolder.taskOver.setChecked((Boolean) tasks.get(position).get("over"));


        return convertView;
    }


    private class ViewHolder {
        TextView taskName, taskContent;
        CheckBox taskOver;
    }

}
