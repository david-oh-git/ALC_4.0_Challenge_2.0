package io.audioshinigami.travelmantics.utility;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;

import io.audioshinigami.travelmantics.R;

public class Utility {

    public static String TAG = "cata";
    public static String user_location = "users";
    public static String deal_location = "deals";
    public static int PICTURE_RESULT = 77;
    public static int SD_REQCODE = 88;
    public static String image_location = "photos";
    public static String title_key = "title";
    public static String description_key = "description";
    public static String price_key = "price";
    public static String image_url_key = "imageUrl";
    public static String image_name_key = "imageName";
    public static String id_key = "id";
    public static String absolute_path_key = "abs_path";


    private static boolean isStorageReadable(Context context){
        return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED;
    } /*end isStroe*/

    public static void requestReadPermission(AppCompatActivity context){

        if(!isStorageReadable(context)){
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SD_REQCODE);
        } /*end IF*/
    } /*end requestRead*/

    public static <T extends AppCompatActivity> void launchActivity(Class<T> inputClass, AppCompatActivity activity,
                                                               Bundle bundle ){
        Intent intent = new Intent(activity, inputClass);
        if( bundle != null )
            intent.putExtras(bundle);

        activity.startActivity(intent);
    } /*end launchActivity*/

    public static String getFilepath(Uri selectedImage, AppCompatActivity activity){

        String imgDecodableString = null;

        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        // Get the cursor
        Cursor cursor = activity.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        // Move to first row
        if( cursor != null ) {
            cursor.moveToFirst();

            //Get the column index of MediaStore.Images.Media.DATA
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //Gets the String value in the column
            imgDecodableString = cursor.getString(columnIndex);

            cursor.close();
        }


        return imgDecodableString;
    }

    public static boolean isFormValid(TextInputEditText titleEdit, TextInputEditText descriptionEdit,
                                TextInputEditText priceEdit , AppCompatActivity activity){
        /*checks if email and password is valid */

        boolean valid = true;

        TextInputEditText titleLayout = activity.findViewById(R.id.id_edittxt_title);
        TextInputEditText descriptionLayout = activity.findViewById(R.id.id_edittxt_description);
        TextInputEditText priceLayout = activity.findViewById(R.id.id_edittxt_price);

        if( titleEdit.getText()!= null && TextUtils.isEmpty(titleEdit.getText().toString()) ){
            titleEdit.requestFocus();
//            titleEdit.setError("Required.");
            titleLayout.setError("Required.");
            valid = false;
        }

        if( descriptionEdit.getText() != null && TextUtils.isEmpty(descriptionEdit.getText().toString())){
            descriptionEdit.requestFocus();
//            descriptionEdit.setError("Required.");
            descriptionLayout.setError("Required.");
            valid = false;
        }

        if( priceEdit.getText() != null && TextUtils.isEmpty(priceEdit.getText().toString())){
            priceEdit.requestFocus();
//            priceEdit.setError("Required.");
            priceLayout.setError("Required.");
            valid = false;
        }

        return valid;

    } /*end isFormvalid*/
}
