package com.hansung.android.androidproject2;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Schedule_Adapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<EventItem> eventItems;
    private String mDaySelected;
    private int mTimeSelected;

    public Schedule_Adapter(Context context, ArrayList<EventItem> schedItems) {
        mContext = context;
        this.eventItems = schedItems;
    }

    @Override
    public int getCount() {
        Log.d("ScheduleAdapter","getCount="+ eventItems.size());
        return eventItems.size();
    }

    @Override
    public Object getItem(int position) {
        return eventItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("ScheduleAdapter","getView("+position+")");
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.time_item, parent, false);
        }
        // 라인 표시하기 위해 백그라운드 색 지정
        convertView.setBackgroundColor(Color.GRAY);

        TextView txt = convertView.findViewById(R.id.title);
        //txt.setText("1");

        if (position == mTimeSelected) {
            txt.setBackgroundColor(Color.CYAN);
        } else {
            txt.setBackgroundColor(Color.WHITE);
        }
        int screen_height = mContext.getResources().getDisplayMetrics().heightPixels;
        int screen_width = mContext.getResources().getDisplayMetrics().widthPixels;
        int width = screen_width/7;

        // int height = screen_height/12;
        int height = 100;

        convertView.setLayoutParams(new ViewGroup.LayoutParams(width, height));

        return convertView;
    }

    void setTimeSelection(int position) {
        mTimeSelected = position;
    }
}