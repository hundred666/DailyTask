package txiner.top.dailytask.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import txiner.top.dailytask.R;
import txiner.top.dailytask.database.DAOHelper;
import txiner.top.dailytask.service.SetTask;

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
//        tasks.set(0,task);
        new SetTask(task,context).start();
    }

    public void delete(Task task){
        tasks.remove(task);
        new DAOHelper(context).deleteTask(task);
    }

    /*public void onChange(ArrayList<Task> tasks){
        this.tasks=tasks;
    }*/

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
                task.setOver(!task.isOver());
                new SetTask(task,context).start();
            }
        });

        return convertView;
    }


    private class ViewHolder {
        TextView taskName, taskContent;
        CheckBox taskOver;
    }



}
