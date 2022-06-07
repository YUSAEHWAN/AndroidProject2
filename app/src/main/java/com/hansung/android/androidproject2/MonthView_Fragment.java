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

public class MonthView_Fragment extends Fragment {

    ViewPager2 vpPager_month;

    // 프래그먼트 초기화 매개 변수 지정
    private static final String ARG_PARAM1 = "YEAR";
    private int mYear;

    private static final String ARG_PARAM2 = "MONTH";
    private int mMonth;

    private static final String ARG_PARAM3 = "DATE";
    private int mDate;

    public MonthView_Fragment() { }

    // 프래그먼트로 인자들을 전달하기 위해 newInstance() 메소드 사용
    public static MonthView_Fragment newInstance(int param1, int param2, int param3) {
        MonthView_Fragment fragment = new MonthView_Fragment();
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
            mYear = getArguments().getInt(ARG_PARAM1); // 얻어온 인자를 mYear에 저장
            mMonth = getArguments().getInt(ARG_PARAM2); // 얻어온 인자를 mMonth에 저장
            mDate = getArguments().getInt(ARG_PARAM3); // 얻어온 인자를 mDate에 저장
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_month_view, container, false);
        vpPager_month = view.findViewById(R.id.vpPager_month);

        Month_PageAdapter monthPagerAdapter = new Month_PageAdapter(this);
        vpPager_month.setAdapter(monthPagerAdapter); // vpPager에 monthPagerAdapter를 연결

        Calendar cal = Calendar.getInstance(); // Calendar 인스턴스를 받아와서
        int curYear = cal.get(Calendar.YEAR); // 현재 년도를 구함
        int curMonth = cal.get(Calendar.MONTH); // 현재 월을 구함

        vpPager_month.setCurrentItem(24250); // 앱을 처음 시작했을 때 시작할 위치 지정

        ActionBar actionbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionbar.setTitle(curYear + "년 " + (curMonth + 1) + "월"); // 드래그하기 전의 앱바 타이틀 지정

        // vpPager 부분을 드래그 할 때 실행되는 메소드
        vpPager_month.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                mYear = position / 12 + 1; // 페이지의 포지션에 12를 나눠 1을 더해서 년도를 계산
                mMonth = position % 12 + 1; // 페이지의 포지션에 12를 나눠 나온 나머지에 1을 더해서 달을 계산
                actionbar.setTitle(mYear + "년 " + mMonth + "월"); // 드래그 하고 난 후의 앱바 타이틀 지정
            }
        });
        return view;
    }

}