package com.example.bhumika29.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.buddy.sdk.Buddy;
import com.buddy.sdk.BuddyCallback;
import com.buddy.sdk.BuddyClient;
import com.buddy.sdk.BuddyFile;
import com.buddy.sdk.BuddyResult;
import com.buddy.sdk.models.Picture;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class Main2Activity extends Activity {


    Button button;
    ImageView imageView;
    static final int CAM_REQUEST = 1;
    String pictureId = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context myContext = getApplicationContext(); // If there is no context, set myContext to null
        final BuddyClient init = Buddy.init(getApplicationContext(), "bbbbbc.plvnwCtmFqFhc", "f2f7c6c8-fbce-5ca5-4dc3-b98aa661755b");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button = (Button) (findViewById(R.id.camera));
        imageView = (ImageView) (findViewById(R.id.photphot));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_Intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();


                camera_Intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_Intent, CAM_REQUEST);


            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private File getFile() {
        File folder = new File("sdcard/camera_app");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File image_file = new File(folder, "cam_image.jpg");
        return image_file;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            System.out.println("Hello .. requestCode:" + requestCode + " resultCode:" + resultCode);
            if (resultCode == RESULT_OK) {
            if (requestCode == CAM_REQUEST) {
                String filePath = "sdcard/camera_app/cam_image.jpg";
                    BuddyFile buddyFile = new BuddyFile(new File(filePath), "image/jpg");
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("caption", "From Android");
                    parameters.put("data", buddyFile);

                    Buddy.<Picture>post("/pictures", parameters, new BuddyCallback<Picture>(Picture.class) {
                        @Override
                        public void completed(BuddyResult<Picture> result) {
                            if (result.getIsSuccess()) {
                                pictureId = result.getResult().id;
                                System.out.println("It worked.");
                            }else{
                                System.out.println("Picture post failed.");
                            }
                        }
                    });

                    imageView.setImageDrawable(Drawable.createFromPath(filePath));
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.bhumika29.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.bhumika29.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


}

