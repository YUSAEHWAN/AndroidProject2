package com.hansung.android.androidproject2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Week_Calendar_Fragment extends Fragment {
    GridView weekView;

    // 프래그먼트 초기화 매개 변수 지정
    private static final String ARG_PARAM1 = "param1";
    private int mYear;

    private static final String ARG_PARAM2 = "param2";
    private int mMonth;

    private static final String ARG_PARAM3 = "param3";
    private int mDate;

    public Week_Calendar_Fragment() { }

    // 프래그먼트로 인자들을 전달하기 위해 newInstance() 메소드 사용
    public static Week_Calendar_Fragment newInstance(int year, int month, int day) {

        Week_Calendar_Fragment fragment = new Week_Calendar_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        args.putInt(ARG_PARAM3, day);
        fragment.setArguments(args); // setArguments() 메소드를 통해 프래그먼트 객체로 인자 전달
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mYear = getArguments().getInt(ARG_PARAM1);  // 얻어온 인자를 mYear에 저장
            mMonth = getArguments().getInt(ARG_PARAM2); // 얻어온 인자를 mMonth에 저장
            mDate = getArguments().getInt(ARG_PARAM3);   // 얻어온 인자를 mDate에 저장
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week_calendar, container, false);

        weekView = view.findViewById(R.id.weekview);

        String[] dayLabels = makeDayLabels();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, dayLabels);

        weekView.setAdapter(arrayAdapter);
        weekView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        weekView.setSelection(0);
        weekView.setItemChecked(0, true);

        LinearLayout times = view.findViewById(R.id.times);

        for (int i = 0; i < 24; i++) {
            TextView text = new TextView(getContext());
            text.setText("" + i);
            text.setLayoutParams(new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 100)); // grid cell 높이 고정
            times.addView(text);
        }

        GridView schedules = view.findViewById(R.id.schedules);
        ArrayList<EventItem> items = makeWeekCalendar();
        Schedule_Adapter adapter = new Schedule_Adapter(getActivity(), items);
        schedules.setAdapter(adapter);
        weekView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // 달력 클릭 이벤트를 처리하는 메소드 정의
        schedules.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"position=" + position, Toast.LENGTH_SHORT).show();
                ((Schedule_Adapter)parent.getAdapter()).setTimeSelection(position);
                ((Schedule_Adapter)parent.getAdapter()).notifyDataSetChanged();

                weekView.setSelection(position % 7);
                weekView.setItemChecked(position % 7, true);
            }
        });
        return view;
    }

    private String[] makeDayLabels() {
        String[] labels = new String[7];
        int date = mDate;

        for (int i = 0; i < 7; i++) {
            switch (mMonth) {
                case 0:
                case 2:
                case 4:
                case 6:
                case 7:
                case 9:
                case 11:
                    if (date > 31) {
                        date = 1;
                    }
                    break;
                case 3:
                case 5:
                case 8:
                case 10:
                    if (date > 30) {
                        date = 1;
                    }
                    break;
                case 1:
                    if (date > 28) {
                        date = 1;
                    }
                    break;
            }
            labels[i] = "" + date++;
        }
        return labels;
    }

    // 주간 달력의 날짜를 채워넣기 위한 계산 메소드
    private ArrayList<EventItem> makeWeekCalendar() {
        ArrayList<EventItem> items = new ArrayList<EventItem>();
        for (int j = 0; j < 24; j++) {
            for (int i = 0; i < 7; i++) {
                items.add(new EventItem());
            }
        }
        return items;
    }
}
