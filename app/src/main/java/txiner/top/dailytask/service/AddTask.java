package txiner.top.dailytask.service;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import txiner.top.dailytask.database.DAOHelper;
import txiner.top.dailytask.util.Task;

/**
 * Created by wzhuo on 2016/3/19.
 */
public class AddTask extends Thread {

    Task task = new Task();
    Context context=null;

    public AddTask(Task task,Context context ){
        this.task=task;
        this.context=context;
    }


    @Override
    public void run() {
        Object[] obj=new Object[]{task.getName(),task.getContent(),task.isOver()};
        new DAOHelper(context).addTask(obj);
    }
}
