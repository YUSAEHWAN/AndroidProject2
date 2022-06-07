package com.hansung.android.androidproject2;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;

public class Month_Calendar_Fragment extends Fragment {
    GridView monthView;
    MonthAdapter adt;

    // 프래그먼트 초기화 매개 변수 지정
    private static final String ARG_PARAM1 = "YEAR";
    private int toast_year;

    private static final String ARG_PARAM2 = "MONTH";
    private int toast_month;

    private static final String ARG_PARAM3 = "DATE";
    private int mDate;

    public Month_Calendar_Fragment() {}

    // 프래그먼트로 인자들을 전달하기 위해 newInstance() 메소드 사용
    public static Month_Calendar_Fragment newInstance(int year, int month, int date) {

        Month_Calendar_Fragment fragment = new Month_Calendar_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        args.putInt(ARG_PARAM3, date);
        fragment.setArguments(args); // setArguments() 메소드를 통해 프래그먼트 객체로 인자 전달
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            toast_year = getArguments().getInt(ARG_PARAM1); // 얻어온 인자를 toast_year에 저장
            toast_month = getArguments().getInt(ARG_PARAM2); // 얻어온 인자를 toast_month에 저장
            mDate = getArguments().getInt(ARG_PARAM3); // 얻어온 인자를 mDate에 저장
        }
    }

    // 월간 달력의 날짜를 채워넣기 위한 계산 메소드
    private ArrayList<CalendarItem> calculate(int year, int month) {
        ArrayList<CalendarItem> items = new ArrayList<>();

        Calendar cal = Calendar.getInstance(); // 날자를 계산하기 위해 Calendar 인스턴스를 불러옴
        cal.set(year, month, 1); // 날짜를 1일로

        int startDay = cal.get(Calendar.DAY_OF_WEEK); // 현재 달 1일의 요일 (1: 일요일, . . . 7: 토요일)
        int lastDay= cal.getActualMaximum(Calendar.DATE); // 달의 마지막 날짜를 구함

        int cnt = 1;

        for (int i = 1; i <= 6 * 7; i++) { // 크기는 6 X 7
            if (i < startDay || (i >= startDay + lastDay)) // 범위의 값과 맞지 않는다면
                items.add(new CalendarItem(0)); // 리스트 초기화
            else { // 각 시작위치와 마지막 날을 계산하여 지정
                CalendarItem item = new CalendarItem(cnt);
                cnt++;
                items.add(item); // 아이템을 각 시작위치와 마지막 날을 계산한 부분으로 6 X 7로 추가
            }
        }
        return items;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_month_calendar, container, false);

        ArrayList<CalendarItem> items = calculate(toast_year, toast_month); // 계산하여 나온 년도와 달을 리스트에 저장

        monthView = view.findViewById(R.id.monthview); // id를 바탕으로 화면 레이아웃에 정의된 GridView 객체 로딩
        adt = new MonthAdapter(getActivity(),items); // adt 객체 생성
        monthView.setAdapter(adt);  // adt 객체를 GridView 객체에 연결
        adt.set_Date_Selected(mDate); // 어댑터의 set_Date_Selected 메소드 호출

        // 달력 클릭 이벤트를 처리하는 메소드 정의
        monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int toast_date = ((CalendarItem) adt.getItem(position)).getDate();
                adt.set_Date_Selected(toast_date); // 어댑터의 set_Date_Selected 메소드 호출
                adt.notifyDataSetChanged();
                Toast.makeText(getActivity(), toast_year + "." + (toast_month + 1) + "."
                        + toast_date, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}