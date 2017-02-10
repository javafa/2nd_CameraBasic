package com.veryworks.android.camerabasic;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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

        // 1. 위젯을 세팅
        setWidget();
        // 2. 버튼관련 컨트롤러 활성화처리
        buttonDisable();
        // 3. 리스너 계열을 등록
        setListener();
        // 4. 권한처리
        checkPermission();
    }
    // 버튼 비활성화하기
    private void buttonDisable(){
        btnCamera.setEnabled(false);
    }
    // 버튼 활성화하기
    private void buttonEnable(){
        btnCamera.setEnabled(true);
    }

    private void init(){
        // 권한처리가 통과 되었을때만 버튼을 활성화 시켜준다
        buttonEnable();
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
            Intent intent = null;
            switch(v.getId()){
                case R.id.btnCamera: //카메라 버튼 동작
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQ_CAMERA);
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
            }
        }
    }
}
