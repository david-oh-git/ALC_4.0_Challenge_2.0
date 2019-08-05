package io.audioshinigami.travelmantics.utility;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Utility {
    public static String user_location = "users";
    public static String deal_location = "deals";
    public static int PICTURE_RESULT = 77;
    public static int SD_REQCODE = 88;
    public static String image_location = "photos";

    private static boolean isStorageReadable(Context context){
        return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED;
    } /*end isStroe*/

    public static void requestReadPermission(AppCompatActivity context){

        if(!isStorageReadable(context)){
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SD_REQCODE);
        } /*end IF*/
    } /*end requestRead*/
}
