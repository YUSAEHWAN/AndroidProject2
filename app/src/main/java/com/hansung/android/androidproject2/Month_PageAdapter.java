package com.hansung.android.androidproject2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Month_PageAdapter extends FragmentStateAdapter {
    private static int pages = 3000 * 12;

    public Month_PageAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @Override
    public Fragment createFragment(int position) {
        int year = position / 12 + 1; // 페이지의 포지션에 12를 나눠 1을 더해서 년도를 계산
        int month = position % 12; // 페이지의 포지션에 12를 나눠 나온 나머지로 달을 계산
        int date = 1; // 날짜는 1로 초기화
        return Month_Calendar_Fragment.newInstance(year,month,date);
    }

    @Override
    public int getItemCount() {  // 전체 페이지 개수 반환
        return pages;
    }
}
