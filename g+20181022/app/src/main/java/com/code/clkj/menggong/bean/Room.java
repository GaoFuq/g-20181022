package com.code.clkj.menggong.bean;

/**
 *
 * Created by Administrator on 2017/9/5.
 * 房间的一个对象
 *
 */

public class Room {
    private String icon;   //直播间缩略图
    private String title;  //直播间标题
    private String count;  //观看人数
    private String distance; //距离

    public Room() {
    }

    public Room(String icon, String title, String count, String distance) {
        this.icon = icon;
        this.title = title;
        this.count = count;
        this.distance = distance;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
