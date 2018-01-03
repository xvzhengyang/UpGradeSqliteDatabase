package com.example.lhy.upgradesqlitedatabase.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "database.db";// 数据库名称
    public static final int VERSION = 1;

    // 存放提醒事件id的表格
    public static final String TABLE_EVENT = "Event";
    private static final String promotion_news_id = "promotionnewsid";
    private static final String channel_id = "channelid";
    public static final String eventId = "eventid";

    // 存放频道的id
    public static final String TABLE_CHANNEL = "channel";//数据表
    public static final String ID = "id";//
    public static final String NAME = "name";
    public static final String ORDERID = "orderId";
    public static final String SELECTED = "selected";
    private Context context;

    public static final String TEST = "test";
    /**
     * 重新创建一张表--相比之前的表添加了1个字段test
     */
    public static final String createNewTable = "create table if not exists " + TABLE_CHANNEL +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ID + " INTEGER , " +
            NAME + " TEXT , " +
            ORDERID + " INTEGER , " +
            SELECTED + " SELECTED , " +
            TEST + " INTEGER)";
    /**
     * B表复制A表中的数据
     */
    public static final String insertFromTable = "insert into channel(_id, id, name, orderId,selected,test) "
            + "select _id, id, name, orderId,selected, \"CHINA\" from channel_temp";
    /**
     * 重命名的语句
     */
    public static final String reName = "ALTER TABLE channel RENAME TO channel_temp";
    /**
     * 删除表
     */
    public static final String delete = "DROP TABLE channel_temp";


    public SQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists " + TABLE_CHANNEL +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ID + " INTEGER , " +
                NAME + " TEXT , " +
                ORDERID + " INTEGER , " +
                SELECTED + " SELECTED)";
        db.execSQL(sql);
        Log.e("channel表", "onCreate:channel ");


        // 创建保存eventid的表
        String sql_Event = "create table if not exists " + TABLE_EVENT +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                promotion_news_id + " INTEGER , " +
                channel_id + " INTEGER , " +
                eventId + " INTEGER)";
        db.execSQL(sql_Event);
        Log.e("event表", "onCreate: event表");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 解决从之前的所有版本升级到当前最新的版本--满足跨版本升级数据库需求
        Log.e("ceshi", "onUpgrade: " + "当前版本" + oldVersion);
        Log.e("ceshi", "onUpgrade: " + "最新版本" + newVersion);
        db.beginTransaction();
        for (int i = oldVersion; i < newVersion; i++) {
            switch (i) {
                case 1://
                    db.beginTransaction();
                    db.execSQL(reName);
                    db.execSQL(createNewTable);
                    db.execSQL(insertFromTable);
                    db.execSQL(delete);
                    db.endTransaction();
                    Log.e("ceshi", "onUpgrade: " + "执行了case2");

                    break;
                case 2:
                    db.execSQL("create table if not exists " + "newTab" +
                            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            ID + " INTEGER , " +
                            NAME + " TEXT , " +
                            ORDERID + " INTEGER , " +
                            SELECTED + " SELECTED)");
                    Log.e("ceshi", "onUpgrade: " + "执行了case2");
                    break;
                default:
                    break;
            }
        }
        db.endTransaction();
    }

    @Override// 因为我们无法预知未来版本的表结构，向下兼容时最稳妥的方法就是将该版本自己需要的表重构一次
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
        //  super.onDowngrade(db, oldVersion, newVersion);
        if (newVersion < oldVersion) {
            db.beginTransaction();
            try {
                //Update DataBase SQL...
                db.execSQL("DROP TABLE IF EXISTS brs");
                onCreate(db);
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }
    }
}
