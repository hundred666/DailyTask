package txiner.top.dailytask.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wzhuo on 2016/3/18.
 */
public class DAOHelper {
    private DBOpenHelper helper = null;

    public DAOHelper(Context context) {
        helper = new DBOpenHelper(context);
    }


    public List<Map<String, Object>> getTasks(String[] selectionArgs) {
        List<Map<String, Object>> tasks = null;
        String sql = "select * from tasks";
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        if (cursor.getCount() == 0) {
            return tasks;
        }

        int columnCount = cursor.getColumnCount();

        tasks = new ArrayList<>();
        while (cursor.moveToNext()) {
            Map<String, Object> task = new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                String colName = cursor.getColumnName(i);
                if (colName.equals("over")) {
                    Boolean over = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex
                            (colName)));
                    task.put(colName, over);
                } else {
                    String cols_value = cursor.getString(cursor.getColumnIndex(colName));
                    if (cols_value == null) {
                        cols_value = "";
                    }
                    task.put(colName, cols_value);
                }

            }
            tasks.add(task);

        }
        return tasks;
    }


}
