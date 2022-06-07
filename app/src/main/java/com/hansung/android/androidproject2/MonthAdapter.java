package com.hansung.android.androidproject2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MonthAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<CalendarItem> calendarItems;
    private int Selected_Date;

    public MonthAdapter(Context context, ArrayList<CalendarItem> calendarItems) {
        mContext = context;
        this.calendarItems = calendarItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent, false);
        }
        // 기본 배경은 WHITE로 지정
        convertView.setBackgroundColor(Color.WHITE);
        
        TextView textview = convertView.findViewById(R.id.date);
        int date = calendarItems.get(position).getDate(); // 날짜를 받아옴
        if (date != 0) {                                  // 받아온 날짜가 0이 아니라면
            textview.setText(date + "");                 // 받아온 날짜를 텍스트로 설정
            if (date == Selected_Date) {   // 선택된 날짜라면
                convertView.setBackgroundColor(Color.CYAN); // view 배경을 CYAN로 변경
            } else {                                        // 날짜가 선택되지 않았다면
                convertView.setBackgroundColor(Color.WHITE); // view 배경을 WHITE로 변경
            }
        } else {                   // 받아온 날짜가 0이면
            textview.setText("");  // 텍스트를 빈칸으로 설정
            convertView.setEnabled(false);
        }

        // 격자 구분선을 표시하기 위해 가로 세로에 -1을 함
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(parent.getWidth()/7-1, parent.getHeight()/6-1);
        convertView.setLayoutParams(params);

        return convertView;
    }
    // 선택된 날짜를 반환하는 메소드
    public void set_Date_Selected(int date) {
        Selected_Date = date;
    }

    @Override
    public int getCount() {
        return calendarItems.size();
    }

    @Override
    public Object getItem(int position) {
        return calendarItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
