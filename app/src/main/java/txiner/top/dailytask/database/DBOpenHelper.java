package txiner.top.dailytask.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wzhuo on 2016/3/18.
 */
public class DBOpenHelper extends SQLiteOpenHelper{


    private static String sqlName = "tasks.db";
    private static int version = 1;

    public DBOpenHelper(Context context) {
        super(context, sqlName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table tasks((id integer primary key autoincrement,name varchar(64),content text,over int)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
