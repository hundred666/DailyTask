package txiner.top.dailytask.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import txiner.top.dailytask.util.Task;

/**
 * Created by wzhuo on 2016/3/18.
 */
public class DAOHelper {
    private DBOpenHelper helper = null;

    public DAOHelper(Context context) {
        helper = new DBOpenHelper(context);
    }

    public void addTask(Object[] params) {
        SQLiteDatabase database = null;
        try {
            String sql = "insert into tasks(name,content,over) values(?,?,?)";
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
        ArrayList<Task> tasks = null;
        SQLiteDatabase database = null;
        String sql = "select * from tasks";
        try {
            database = helper.getReadableDatabase();
            Cursor cursor = database.rawQuery(sql, selectionArgs);
            if (cursor.getCount() == 0) {
                return tasks;
            }

            int columnCount = cursor.getColumnCount();

            tasks = new ArrayList<>();
            while (cursor.moveToNext()) {
                Task task = new Task();
                for (int i = 0; i < columnCount; i++) {
                    String colName = cursor.getColumnName(i);
                    if (colName.equals("over")) {
                        Boolean over = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex
                                (colName)));
                        task.setOver(over);
                    } else if (colName.equals("name")) {
                        String name = cursor.getString(cursor.getColumnIndex(colName));
                        if (name==null)
                            name="";
                        task.setName(name);
                    } else {
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
