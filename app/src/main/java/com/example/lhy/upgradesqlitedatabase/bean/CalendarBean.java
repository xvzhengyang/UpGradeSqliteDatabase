package com.example.lhy.upgradesqlitedatabase.bean;

/**
 * Created by lihon on 2016/11/14.
 * 提醒bean,暂时包含活动id和事件id
 */

public class CalendarBean {
    /**
     * 营销信息id
     */
    private int promotion_news_id ;
    /**
     * 提醒事件id
     */
    private long eventId;



    /**
     * 频道id
     */
    private int channel_id;

    public CalendarBean() {
       super();
    }
    public CalendarBean(int promotion_news_id, int channel_id , long eventId) {
        this.promotion_news_id = promotion_news_id;
        this.channel_id = channel_id;
        this.eventId = eventId;
    }
    public CalendarBean(int promotion_news_id, int channel_id ) {
        this.promotion_news_id = promotion_news_id;
        this.channel_id = channel_id;

    }

    public void setPromotion_news_id(int promotion_news_id) {
        this.promotion_news_id = promotion_news_id;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public int getPromotion_news_id() {
        return promotion_news_id;
    }

    public long getEventId() {
        return eventId;
    }
    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }

    public int getChannel_id() {
        return channel_id;
    }

    @Override
    public String toString() {
        return "CalendarBean{" +
                "promotion_news_id=" + promotion_news_id +
                ", eventId=" + eventId +
                ", channel_id=" + channel_id +
                '}';
    }
}
