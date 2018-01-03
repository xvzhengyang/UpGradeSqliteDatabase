package com.example.lhy.upgradesqlitedatabase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lhy.upgradesqlitedatabase.bean.CalendarBean;
import com.example.lhy.upgradesqlitedatabase.myinterface.CalendarDaoInterface;

import java.util.ArrayList;

/**
 * Created by lihon on 2016/11/14.
 * 实现提醒接口的类
 */

public class CalendarDao implements CalendarDaoInterface {
    private SQLHelper helper = null;

    public CalendarDao(Context context) {
        helper = new SQLHelper(context,"测试",null,2);
    }

    @Override
    public boolean addCache(CalendarBean item) {
        boolean flag = false;
        SQLiteDatabase database = null;
        long id = -1;
        try {
            database = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("promotionnewsid", item.getPromotion_news_id());
            values.put("channelid", item.getChannel_id());
            values.put("eventid", item.getEventId());
            id = database.insert(SQLHelper.TABLE_EVENT, null, values);
            flag = (id != -1 ? true : false);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return flag;
    }

    @Override
    public boolean deleteCache(String whereClause, String[] whereArgs) {
        boolean flag = false;
        SQLiteDatabase database = null;
        int count = 0;
        try {
            database = helper.getWritableDatabase();
            // String whereClause =  "promotion_news_id=?";
            // String[] whereArgs = new String[] {String.valueOf(promotion_news_id)};
            count = database.delete(SQLHelper.TABLE_EVENT, whereClause, whereArgs);
            Log.e("查看删除了几条数据", count + "");
            flag = (count > 0 ? true : false);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return flag;
    }

    @Override
    public CalendarBean findByEventId(String selection,
                                      String[] selectionArgs) {
        SQLiteDatabase database = null;
        Cursor cursor;
        try {
            database = helper.getReadableDatabase();
            String[] columns = new String[]{"_id", "promotionnewsid", "channelid", "eventid"};
            //String selection = "promotion_news_id=?";
            //String[] selectionArgs = { String.valueOf(id) };
            //String groupBy = null;
            //String having = null;
            //String orderBy = null;
            // mDb.query(table, columns, selection,selectionArgs, groupBy, having, orderBy);
            Log.e("查询条件是否出错", selection + "查询数组" + selectionArgs[0]);

            cursor = database.query(SQLHelper.TABLE_EVENT, columns, selection,
                    selectionArgs, null, null, null, null);
            Log.e("是否执行到此步骤", "findByEventId: " + "查询结束");
            if (cursor.moveToFirst()) {
                // 获取接收到的数据
                int promotion_news_id = cursor.getInt(cursor.getColumnIndex("promotionnewsid"));
                int channel_id = cursor.getInt(cursor.getColumnIndex("channelid"));
                long event_id = cursor.getInt(cursor.getColumnIndex("eventid"));
                Log.e("是否执行到此步骤", "findByEventId: " + "查询结束" + new CalendarBean(promotion_news_id, channel_id, event_id).toString());
                return new CalendarBean(promotion_news_id, channel_id, event_id);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (database != null) {
                database.close();
            }
        }
        Log.e("没有查询到", "没有查询到");
        return null;
    }

    @Override
    public ArrayList<CalendarBean> findAll(String selection, String[] selectionArgs) {
        SQLiteDatabase database = null;
        Cursor cursor;
        ArrayList<CalendarBean> list = new ArrayList<>();
        try {
            database = helper.getReadableDatabase();
            String[] columns = new String[]{"promotionnewsid", "channelid", "eventid"};
            //String selection = "id=?";
            //String[] selectionArgs = { String.valueOf(id) };
            //String groupBy = null;
            //String having = null;
            //String orderBy = null;
            // mDb.query(table, columns, selection,selectionArgs, groupBy, having, orderBy);
            cursor = database.query(SQLHelper.TABLE_EVENT, columns, selection,
                    selectionArgs, null, null, null, null);
//参数1：表名
//参数2：要想显示的列
//参数3：where子句
//参数4：where子句对应的条件值
//参数5：分组方式
//参数6：having条件
//参数7：排序方式
            // Cursor cursor = db.query("stu_table", new String[]{"id","sname","sage","ssex"}, "id=?", new String[]{"1"}, null, null, null);
            while (cursor.moveToNext()) {
                CalendarBean calendarBean = new CalendarBean();
                int promotion_news_id = cursor.getInt(cursor.getColumnIndex("promotionnewsid"));
                int channel_id = cursor.getInt(cursor.getColumnIndex("channelid"));
                long eventId = cursor.getInt(cursor.getColumnIndex("eventid"));
                calendarBean.setEventId(eventId);
                calendarBean.setPromotion_news_id(promotion_news_id);
                calendarBean.setChannel_id(channel_id);
                list.add(calendarBean);
                Log.e("查询所有", "findAll: " + promotion_news_id + "事件id" + eventId + "频道id" + channel_id);
            }
            return list;
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return null;
    }


}
