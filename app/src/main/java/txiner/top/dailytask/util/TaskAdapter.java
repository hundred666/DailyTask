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
import txiner.top.dailytask.service.AddTask;

/**
 * Created by wzhuo on 2016/3/18.
 */
public class TaskAdapter extends BaseAdapter {

    Context context;
    ArrayList<Task> tasks;

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
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

    public void add(Task task){
        tasks.add(task);
        new AddTask(task,context).start();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        final Task task=tasks.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.task_list, null);
            viewHolder = new ViewHolder();
            viewHolder.taskName = (TextView) convertView.findViewById(R.id.task_name);
            viewHolder.taskContent = (TextView) convertView.findViewById(R.id.task_content);
            viewHolder.taskOver = (CheckBox) convertView.findViewById(R.id.task_over);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.taskName.setText(task.getName());
        viewHolder.taskContent.setText(task.getContent());
        viewHolder.taskOver.setChecked(task.isOver());

        viewHolder.taskOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }


    private class ViewHolder {
        TextView taskName, taskContent;
        CheckBox taskOver;
    }

}
