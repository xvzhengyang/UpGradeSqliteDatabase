package com.example.lhy.upgradesqlitedatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.lhy.upgradesqlitedatabase.bean.CalendarBean;
import com.example.lhy.upgradesqlitedatabase.db.SQLHelper;
import com.example.lhy.upgradesqlitedatabase.utils.CalendarManage;

/**
 * 关于数据库升级的想法
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SQLHelper sqlHelper = new SQLHelper(this, "数据库", null, 1);
        sqlHelper.getWritableDatabase();
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarBean calendarBean = new CalendarBean(1, 2, 3);  // 未升级的时候点击添加数据，升级之后测试是否添加成功
                CalendarManage calendarManage = CalendarManage.getManage(sqlHelper);
                calendarManage.saveUserCalendar(calendarBean);
                Log.e("ceshi", calendarManage.queryUserCalendar(calendarBean) + "");

               // 检测新的表中数据是否有
             /*   CalendarBean calendarBean = new CalendarBean(1, 2, 3);  // 未升级的时候点击添加数据，升级之后测试是否添加成功
                CalendarManage calendarManage = CalendarManage.getManage(sqlHelper);
                Log.e("ceshi", calendarManage.queryUserCalendar(calendarBean) + "");*/
            }
        });
    }
}
