package com.hansung.android.androidproject2;

import android.location.Address;

public class EventItem {
    int id;
    String title;
    String startDay;
    String startMin;
    String endDay;
    String endMin;

    String textMemo;
    String audioMemo;
    String videoMemo;
    String imageMemo;
    Address location;

    public EventItem(int id, String title, String startDay, String startMin, String endDay, String endMin) {
        this.id = id;
        this.title = title;
        this.startDay = startDay;
        this.startMin = startMin;
        this.endDay = endDay;
        this.endMin = endMin;
    }

    public EventItem() {}

}
