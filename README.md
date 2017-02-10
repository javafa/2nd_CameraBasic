# Camera Basic
카메라 기본사용법을 익힙니다

## 7.0 Nougat Camera Issues
누가버전 이상에서는 보안강화로 외부저장소 전체를 이미지 저장공간으로 사용할 수 없고, 임시저장 Uri 를 특정해야한다
```java
Uri fileUri = null;

Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
    // 저장할 미디어 속성을 정의하는 클래스
    ContentValues values = new ContentValues(1);
    // 속성중에 파일의 종류를 정의
    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
    // 전역변수로 정의한 fileUri에 외부저장소 컨텐츠가 있는 Uri 를 임시로 생성해서 넣어준다.
    fileUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    // 위에서 생성한 fileUri를 사진저장공간으로 사용하겠다고 설정
    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
    // Uri에 읽기와 쓰기 권한을 시스템에 요청
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
}

startActivityForResult(intent, REQ_CAMERA);
```