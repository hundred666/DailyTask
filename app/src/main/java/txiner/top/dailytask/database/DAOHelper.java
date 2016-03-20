package txiner.top.dailytask.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import txiner.top.dailytask.util.DateUtil;
import txiner.top.dailytask.util.Task;

/**
 * Created by wzhuo on 2016/3/18.
 */
public class DAOHelper {
    private DBOpenHelper helper = null;

    public DAOHelper(Context context) {
        helper = new DBOpenHelper(context);
    }

    public void setTask(Object[] params) {
        SQLiteDatabase database = null;
        try {
            String sql = "replace into tasks(name,content,over,time) values(?,?,?,?)";
            database = helper.getWritableDatabase();
            database.execSQL(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }

    public ArrayList<Task> getTasks(String[] selectionArgs) {
        SQLiteDatabase database = null;
        ArrayList<Task> tasks = new ArrayList<>();
        String sql = "select * from tasks";
        try {
            database = helper.getReadableDatabase();
            Cursor cursor = database.rawQuery(sql, selectionArgs);
            if (cursor.getCount() == 0) {
                return tasks;
            }

            int columnCount = cursor.getColumnCount();
            while (cursor.moveToNext()) {
                Task task = new Task();
                for (int i = 0; i < columnCount; i++) {
                    String colName = cursor.getColumnName(i);
                    if (colName.equals("time")) {
                        long time = cursor.getLong(cursor.getColumnIndex(colName));
                        boolean flag = time < new DateUtil().startOfTodDay();

                    } else if (colName.equals("over")) {
                        Boolean over = cursor.getInt(cursor.getColumnIndex(colName)) == 1 ? true
                                : false;
                        task.setOver(over);
                    } else if (colName.equals("name")) {
                        String name = cursor.getString(cursor.getColumnIndex(colName));
                        if (name == null)
                            name = "";
                        task.setName(name);
                    } else if (colName.equals("content")) {
                        String content = cursor.getString(cursor.getColumnIndex(colName));
                        if (content == null) {
                            content = "";
                        }
                        task.setContent(content);
                    }

                }
                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return tasks;
    }


}
