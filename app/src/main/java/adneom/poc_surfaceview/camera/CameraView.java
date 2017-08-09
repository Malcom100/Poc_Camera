package adneom.poc_surfaceview.camera;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import adneom.poc_surfaceview.R;

/**
 * Created by gtshilombowanticale on 08-08-17.
 */

public class CameraView extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Camera camera;
    //indicates if camera is active and not closed
    private boolean previewRunning = false;

    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] imageData, Camera c) {
            Log.i("Test","my image here : "+imageData.length+" -- ");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_custom_surface_view);

        surfaceView = (SurfaceView)findViewById(R.id.surface_camera);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    //first instance, the surface is created
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        camera = Camera.open();
    }

    //the size or the format of surface changes
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int w, int h) {
        if(previewRunning){
            camera.stopPreview();
        }
        Camera.Parameters p = camera.getParameters();
        p.setPreviewSize(w,h);
        camera.setParameters(p);
        try{
            camera.setPreviewDisplay(surfaceHolder);
        }catch (IOException ioe){
            ioe.printStackTrace();
            Log.i("Text",ioe.getMessage());
        }
        camera.startPreview();
        previewRunning = true;
    }

    //the surface is destroyed
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        previewRunning = false;
        camera.release();
    }
}
