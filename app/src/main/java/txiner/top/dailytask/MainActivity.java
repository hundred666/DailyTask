package txiner.top.dailytask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import txiner.top.dailytask.database.DAOHelper;
import txiner.top.dailytask.util.Task;
import txiner.top.dailytask.util.TaskAdapter;

public class MainActivity extends AppCompatActivity {
    //// TODO: 2016/3/18 add tasks to database and sync tasks


    private ListView listView = null;
    private TextView textView = null;
    ArrayList<Map<String, Object>> tasks = new ArrayList<>();
    TaskAdapter taskAdapter = null;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = new Bundle();
            bundle = msg.getData();
            tasks = (ArrayList<Map<String, Object>>) bundle.getSerializable("tasks");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new Thread(new GetTask()).start();

        textView = (TextView) findViewById(R.id.no_task);
        listView = (ListView) findViewById(R.id.task_list);
        taskAdapter = new TaskAdapter(this, tasks);
        listView.setAdapter(taskAdapter);

        if (tasks.size() == 0) {
            listView.setVisibility(View.INVISIBLE);
        } else {
            textView.setVisibility(View.INVISIBLE);

        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDialog();
                /*if () {
                    textView.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.VISIBLE);
                }*/
            }
        });


    }


    private class GetTask implements Runnable {

        @Override
        public void run() {
            Message message = handler.obtainMessage();
            Bundle bundle = new Bundle();
            ArrayList<Map<String, Object>> tasks = new DAOHelper(MainActivity.this).getTasks(null);
            bundle.putSerializable("tasks", tasks);
            message.setData(bundle);
        }
    }

    public void setDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("添加计划");
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.task_composent, null);
        builder.setView(view);
        final EditText addTaskName = (EditText) view.findViewById(R.id.add_task_name);
        final EditText addTaskContent = (EditText) view.findViewById(R.id.add_task_content);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Map<String, Object> task = new HashMap<>();
                String taskName = addTaskName.getText().toString();
                String taskContent = addTaskContent.getText().toString();
                if (!taskName.trim().equals("")) {
                    task.put("name", taskName);
                    task.put("content", taskContent);
                    task.put("over", false);
                    taskAdapter.add(task);
                    taskAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, taskName + "已添加", Toast.LENGTH_SHORT).show();


                    textView.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(MainActivity.this, "计划名未填写", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }


}
