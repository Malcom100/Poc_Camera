package adneom.poc_surfaceview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import adneom.poc_surfaceview.camera.CameraView;
import adneom.poc_surfaceview.camera.CameraViewTwo;

public class MainActivity extends AppCompatActivity {

    private Button picture;
    private Button picture_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picture = (Button)findViewById(R.id.btn_camera);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MainActivity.this, CameraView.class);
                startActivity(intentCamera);
            }
        });

        picture_2 = (Button)findViewById(R.id.camera_2);
        picture_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MainActivity.this, CameraViewTwo.class);
                startActivity(intentCamera);
            }
        });
    }
}
