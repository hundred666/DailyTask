package txiner.top.dailytask.service;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import txiner.top.dailytask.database.DAOHelper;

/**
 * Created by wzhuo on 2016/3/19.
 */
public class AddTask extends Thread {

    Map<String, Object> task = new HashMap<>();
    Context context=null;

    public AddTask(Map<String, Object> task,Context context ){
        this.task=task;
        this.context=context;
    }


    @Override
    public void run() {
        Object[] obj=new Object[]{task.get("name").toString(),task.get("content").toString(),task.get("over")};
        new DAOHelper(context).addTask(obj);
    }
}
