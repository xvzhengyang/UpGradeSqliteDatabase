package com.example.lhy.upgradesqlitedatabase.utils;

import android.database.SQLException;

import com.example.lhy.upgradesqlitedatabase.bean.CalendarBean;
import com.example.lhy.upgradesqlitedatabase.db.CalendarDao;
import com.example.lhy.upgradesqlitedatabase.db.SQLHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihon on 2016/11/14.
 * 提醒事件的管理器
 */

public class CalendarManage {
    public static CalendarManage calendarManage;
    /**
     *
     * */
    public static List<CalendarBean> defaultUserChannels;
    /**
     *
     * */
    public static List<CalendarBean> defaultOtherChannels;
    private CalendarDao calendarDao;
    /** 判断数据库中是否存在用户数据 */
    private boolean userExist = false;

    private CalendarManage(SQLHelper paramDBHelper) throws SQLException {
        if (calendarDao == null){

            calendarDao = new CalendarDao(paramDBHelper.getContext());
        }
        // NavigateItemDao(paramDBHelper.getDao(NavigateItem.class));
        return;
    }

    /**
     * 初始化提醒管理类
     * @throws SQLException
     */
    public static CalendarManage getManage(SQLHelper dbHelper)throws SQLException {
        if (calendarManage == null)
            calendarManage = new CalendarManage(dbHelper);
        return calendarManage;
    }
    /**
     * 保存用户提醒到数据库
     * @param
     */
    public boolean saveUserCalendar(CalendarBean calendarBean) {

           return calendarDao.addCache(calendarBean);
        }
    /**
     * 取消提醒
     * @return 数据库存在用户配置 ? 数据库内的用户选择频道 : 默认用户选择频道 ;
     */
    public boolean deleteUserCalendar(CalendarBean calendarBean) {
        // 多条件删除
        String whereClause =  "promotionnewsid=? and channelid=?";
        String[] whereArgs = new String[] {String.valueOf(calendarBean.getPromotion_news_id()),String.valueOf(calendarBean.getChannel_id())};
        boolean flag  = calendarDao.deleteCache(whereClause,whereArgs);
        return flag;
    }

    /**
     * @param calendarBean
     * @return  查询对应的营销信息是否存在
     */
    public CalendarBean queryUserCalendar(CalendarBean calendarBean){
        String whereClause =  "promotionnewsid=? and channelid=?";
        String[] whereArgs = new String[] {String.valueOf(calendarBean.getPromotion_news_id()),String.valueOf(calendarBean.getChannel_id())};

       return calendarDao.findByEventId(whereClause,whereArgs);

    }

    /**
     * @param calendarBean
     * @return
     * 获取所有的提醒
     */
    public ArrayList<CalendarBean> queryAll(CalendarBean calendarBean){
        String whereClause =  "promotionnewsid=? and channelid=?";
        String[] whereArgs = new String[] {String.valueOf(calendarBean.getPromotion_news_id()),String.valueOf(calendarBean.getChannel_id())};
        return calendarDao.findAll(whereClause,whereArgs);
    }
}
