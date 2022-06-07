package com.hansung.android.androidproject2;

public class CalendarItem {
    private int dayValue; // dayValue라는 인자를 만들고 날짜 값을 받으려 한다.

    public CalendarItem(int dayValue) {
        this.dayValue = dayValue;
    }

    public int getDate() {
        return dayValue;
    }
}
// 달력의 데이터를 입력받기 위한 클래스이기 때문에 오직 날짜 값만 클래스의 멤버로 가짐.