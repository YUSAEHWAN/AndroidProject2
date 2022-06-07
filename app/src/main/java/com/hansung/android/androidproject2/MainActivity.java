package com.hansung.android.androidproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 액티비티를 생성하는 OnCreate 함수
        setContentView(R.layout.activity_main); // activity_main.xml 파일을 가상 디바이스에 표시

        // 동적으로 프래그먼트를 추가
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new MonthView_Fragment());
        fragmentTransaction.commit();

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
//        mapFragment.getMapAsync(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), test.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 앱바 추가
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 앱바에서 아이템 선택 시 이벤트 처리 메소드
        switch (item.getItemId()) {
            case R.id.actionbar_month: // 월을 누르면
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.fragment_container, new MonthView_Fragment());
                fragmentTransaction.commit();
                return true;
            case R.id.actionbar_weekend: // 주를 누르면
                fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.fragment_container, new WeekView_Fragment());
                fragmentTransaction.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        // 구글에서 등록한 api와 엮어주기
//        // 시작위치를 한성대입구역으로 변경
//        LatLng location = new LatLng(37.5882827, 127.006390); // 한성대입구역 위도와 경도
//
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
//        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
//
//        // 시작 시 마커 생성하기
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(cityHall);
//        markerOptions.title("한성대입구역");
//        markerOptions.snippet("4호선");
//        // 생성된 마커 옵션을 지도에 표시
//        googleMap.addMarker(markerOptions);
//    }
}