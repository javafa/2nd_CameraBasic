package com.veryworks.android.camerabasic;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int REQ_PERMISSION = 100; // 권한요청코드
    private final int REQ_CAMERA = 101; // 카메라 요청코드

    Button btnCamera;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidget();
        setListener();
        checkPermission();
    }
    private void init(){
        // 프로그램 실행
    }

    // 권한관리
    private void checkPermission() {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            if( PermissionControl.checkPermission(this, REQ_PERMISSION) ){
                init();
            }
        }else{
            init();
        }
    }
    // 위젯 세팅
    private void setWidget(){
        imageView = (ImageView) findViewById(R.id.imageView);
        btnCamera = (Button) findViewById(R.id.btnCamera);
    }
    // 리스너 세팅
    private void setListener(){
        btnCamera.setOnClickListener(clickListener);
    }
    // 리스너 정의
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnCamera: //카메라 버튼 동작

                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQ_PERMISSION){
            if( PermissionControl.onCheckResult(grantResults)){
                init();
            }else{
                Toast.makeText(this, "권한을 허용하지 않으시면 프로그램을 실행할 수 없습니다.", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
