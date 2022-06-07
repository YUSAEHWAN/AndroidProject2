package com.hansung.android.androidproject2;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

public class WeekView_Fragment extends Fragment {

    // 프래그먼트 초기화 매개 변수 지정
    private static final String ARG_PARAM1 = "year";
    private int curYear;

    private static final String ARG_PARAM2 = "month";
    private int curMonth;

    private static final String ARG_PARAM3 = "DATE";
    private int curDate;

    public WeekView_Fragment() { }

    // 프래그먼트로 인자들을 전달하기 위해 newInstance() 메소드 사용
    public static WeekView_Fragment newInstance(int param1, int param2, int param3) {
        WeekView_Fragment fragment = new WeekView_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM3, param3);
        fragment.setArguments(args); // setArguments() 메소드를 통해 프래그먼트 객체로 인자 전달
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            curYear = getArguments().getInt(ARG_PARAM1);  // 얻어온 인자를 mYear에 저장
            curMonth = getArguments().getInt(ARG_PARAM2); // 얻어온 인자를 mMonth에 저장
            curDate = getArguments().getInt(ARG_PARAM3);  // 얻어온 인자를 mDate에 저장
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week_view, container, false);
        ViewPager2 vpPager_week = view.findViewById(R.id.vpPager_week);

        Week_PageAdapter weekPagerAdapter = new Week_PageAdapter(this);
        vpPager_week.setAdapter(weekPagerAdapter);  // vpPager에 weekPagerAdapter를 연결

        Calendar cal = Calendar.getInstance(); // Calendar 인스턴스를 받아와서
        curYear = cal.get(Calendar.YEAR);      // 현재 년도를 구함
        curMonth = cal.get(Calendar.MONTH);    // 현재 달을 구함
        curDate = cal.get(Calendar.DAY_OF_MONTH);  // 현재 일을 구함

        int totaldays = calculate(curYear, curMonth + 1, curDate);

        int initPosition = totaldays / 7; // 앱을 처음 시작했을 때 시작할 위치 계산
        vpPager_week.setCurrentItem(initPosition,false); // 앱을 처음 시작했을 때 시작할 위치 지정

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle(curYear + "년" + (curMonth + 1) + "월"); // 드래그하기 전의 앱바 타이틀 지정

        // vpPager 부분을 드래그 할 때 실행되는 메소드
        vpPager_week.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                int totalDate = position * 7;
                int year = totalDate / 365 + 1;

                // 윤년 횟수 구하기
                int leap_year = year / 400 + year / 4 - year / 100;

                curYear = (totalDate - leap_year) / 365 + 1;
                int year_date = (totalDate - leap_year) % 365; // 월과 일을 구하기 위한 계산

                if (year_date <= 31) {
                    curMonth = 1;
                    curDate = year_date;
                } else if (year_date <= 59) {
                    curMonth = 2;
                    curDate = year_date - 31;
                } else if (year_date <= 90) {
                    curMonth = 3;
                    curDate = year_date - 59;
                } else if (year_date <= 120) {
                    curMonth = 4;
                    curDate = year_date - 90;
                } else if (year_date <= 151) {
                    curMonth = 5;
                    curDate = year_date - 120;
                } else if (year_date <= 181) {
                    curMonth = 6;
                    curDate = year_date - 151;
                } else if (year_date <= 212) {
                    curMonth = 7;
                    curDate = year_date - 181;
                } else if (year_date <= 243) {
                    curMonth = 8;
                    curDate = year_date - 212;
                } else if (year_date <= 273) {
                    curMonth = 9;
                    curDate = year_date - 243;
                } else if (year_date <= 304) {
                    curMonth = 10;
                    curDate = year_date - 273;
                } else if (year_date <= 334) {
                    curMonth = 11;
                    curDate = year_date - 304;
                } else {
                    curMonth = 12;
                    curDate = year_date - 334;
                }
                actionBar.setTitle(curYear + "년" + curMonth + "월");
            }
        });
        return view;
    }

    public int calculate(int year, int month, int date) {
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int total_date = 0;
        total_date += (year - 1) * 365;                             // 이전 년도까지의 일수
        total_date += year / 4 - year / 100 + year / 400;             // 윤년의 횟수를 더함
        if ((( month % 4 == 0 && month % 100 != 0)|| (month % 400 == 0)) && (month <= 2))   // 윤년이고 1,2월이면
            total_date--;                                       // 윤년이므로 하루를 빼줌
        for(int i = 0; i < month - 1; i++)
            total_date += months[i];
        total_date += date;                                      // 일수를 추가

        return total_date;
    }
}
