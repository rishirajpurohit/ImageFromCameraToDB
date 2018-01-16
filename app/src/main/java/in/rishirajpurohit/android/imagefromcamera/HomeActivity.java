package in.rishirajpurohit.android.imagefromcamera;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class HomeActivity extends AppCompatActivity {

    String filePath,filename;
    Uri image_temp_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClick(View v) {

        //Create folder !exist
        String folderPath = Environment.getExternalStorageDirectory() + "/MyAppDirectory/images";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            File wallpaperDirectory = new File(folderPath);
            wallpaperDirectory.mkdirs();
        }
        //create a new file
        File newFile = new File(folderPath, "test_image.jpg");
        filePath= newFile.getAbsolutePath();
        Uri relativePath = Uri.fromFile(newFile);
        image_temp_uri = relativePath;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, relativePath);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        ImageView yourimgView = (ImageView) findViewById(R.id.myImage);

        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    yourimgView.setImageURI(image_temp_uri);
                    Log.i("mytag2 path",filePath);
                    Log.i("mytag2 uri",image_temp_uri.toString());
                }
        }
    }


}
