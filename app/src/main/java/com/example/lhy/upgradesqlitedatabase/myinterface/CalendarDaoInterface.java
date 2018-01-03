package com.example.lhy.upgradesqlitedatabase.myinterface;


import com.example.lhy.upgradesqlitedatabase.bean.CalendarBean;

import java.util.ArrayList;

/**
 * Created by lihon on 2016/11/14.
 * 添加提醒接口
 */

public interface CalendarDaoInterface {
    /**
     * @param item
     * @return  添加eventid
     */
    public boolean addCache(CalendarBean item);

    /**
     * @param whereClause
     * @param whereArgs
     * @return  删除eventid
     */
    public boolean deleteCache(String whereClause, String[] whereArgs);


    /**
     * @param selection
     * @param selectionArgs
     * @return  查询事件是否存在
     */
    public CalendarBean findByEventId(String selection,
                                      String[] selectionArgs);

public ArrayList<CalendarBean> findAll(String selection,
                                       String[] selectionArgs);
}
