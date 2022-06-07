package com.hansung.android.androidproject2;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Week_PageAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS = 4000 * 12 * 52;

    public Week_PageAdapter(Fragment fragment) {
        super(fragment);
    }

    @Override
    public Fragment createFragment(int position) {
        int year, month, date;
        int totalDays = position * 7;

        int temp_year = totalDays / 365 + 1;

        // 윤년 횟수 구하기
        int leap_year = temp_year / 400 + temp_year / 4 - temp_year / 100;

        // 연도
        year = (totalDays - leap_year) / 365 + 1;
        int year_date = (totalDays - leap_year) % 365; // 월과 일을 구하기 위한 계산

        if (year_date <= 31) {
            month = 0;
            date = year_date;
        } else if (year_date <= 59) {
            month = 1;
            date = year_date - 31;
        } else if (year_date <= 90) {
            month = 2;
            date = year_date - 59;
        } else if (year_date <= 120) {
            month = 3;
            date = year_date - 90;
        } else if (year_date <= 151) {
            month = 4;
            date = year_date - 120;
        } else if (year_date <= 181) {
            month = 5;
            date = year_date - 151;
        } else if (year_date <= 212) {
            month = 6;
            date = year_date - 181;
        } else if (year_date <= 243) {
            month = 7;
            date = year_date - 212;
        } else if (year_date <= 273) {
            month = 8;
            date = year_date - 243;
        } else if (year_date <= 304) {
            month = 9;
            date = year_date - 273;
        } else if (year_date <= 334) {
            month = 10;
            date = year_date - 304;
        } else {
            month = 11;
            date = year_date - 334;
        }
        Week_Calendar_Fragment week = Week_Calendar_Fragment.newInstance(year,month,date);
        return week;
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }
}
