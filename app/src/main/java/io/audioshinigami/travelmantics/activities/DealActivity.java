package io.audioshinigami.travelmantics.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;

import io.audioshinigami.travelmantics.R;
import io.audioshinigami.travelmantics.models.Deal;
import io.audioshinigami.travelmantics.repository.DealRepository;
import io.audioshinigami.travelmantics.services.UploadImageIntentService;
import io.audioshinigami.travelmantics.utility.Utility;

public class DealActivity extends AppCompatActivity {

    private Button uploadBtn;
    private ImageView imageView;

    private boolean imagePresent;
    private Deal deal;

    private EditText titleEdit;
    private EditText descriptionEdit;
    private EditText priceEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Utility.requestReadPermission(this);

        uploadBtn = findViewById(R.id.id_btn_select_image);
        imageView = findViewById(R.id.id_imagevw_select);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/jpeg");
                intent.putExtra( Intent.EXTRA_LOCAL_ONLY, true );
                startActivityForResult(intent
                        , Utility.PICTURE_RESULT);
            }
        });

        imagePresent = false;
        deal = new Deal();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_deal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == android.R.id.home){
            finish();
        }else if( item.getItemId() == R.id.action_save_menu){
            saveDeal();
        }
        return true;
    }

    private void saveDeal() {

        titleEdit = findViewById(R.id.id_edittxt_title);
        descriptionEdit = findViewById(R.id.id_edittxt_description);
        priceEdit = findViewById(R.id.id_edittxt_price);

        if( !isFormValid(titleEdit, descriptionEdit, priceEdit)){
            return;
        } /*end IF*/

        deal.setTitle(titleEdit.getText().toString());
        deal.setDescription(descriptionEdit.getText().toString());
        deal.setPrice(priceEdit.getText().toString());

        if(imagePresent){
            UploadImageIntentService.startActionUpload(this, deal.getAbsPath());
        }
        DealRepository.getInstance().addDeal(deal, this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == Utility.PICTURE_RESULT && resultCode == RESULT_OK && data.getData() != null){
            Uri imageUri = data.getData();
            String absPath = getFilepath(imageUri);
            String filename = absPath.substring( absPath.lastIndexOf('/')+1);

            deal.setAbsPath(absPath);
            deal.setImageName(filename);
            deal.setImageUrl(Utility.image_location+ "/" + filename);

            try{
//                InputStream stream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                imagePresent = true;
            }catch (IOException e){
                e.printStackTrace();
            }
        } /*end IF*/
    } /*end onActivityResult*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == Utility.SD_REQCODE && grantResults.length != 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED ){
            Log.d("cata", "Write Permission granted");
        } //end if
        else{
            Log.d("cata", "No Permission ");
        }
    }


    private String getFilepath(Uri selectedImage){

        String imgDecodableString = null;

        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        // Get the cursor
        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        // Move to first row
        cursor.moveToFirst();
        //Get the column index of MediaStore.Images.Media.DATA
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        //Gets the String value in the column
        imgDecodableString = cursor.getString(columnIndex);

        cursor.close();

        return imgDecodableString;
    }

    private boolean isFormValid(EditText titleEdit, EditText descriptionEdit, EditText priceEdit){
        /*checks if email and password is valid */

        boolean valid = true;
        String email = titleEdit.getText().toString();

        if( TextUtils.isEmpty(email) ){
            titleEdit.requestFocus();
            titleEdit.setError("Required.");
            valid = false;
        }else {
            titleEdit.setError(null);
        }



        String password = descriptionEdit.getText().toString();
        if( TextUtils.isEmpty(password)){
            descriptionEdit.requestFocus();
            descriptionEdit.setError("Required.");
            valid = false;
        }else {
            descriptionEdit.setError(null);
        }

        String price =  priceEdit.getText().toString();
        if( TextUtils.isEmpty(price)){
            priceEdit.requestFocus();
            priceEdit.setError("Required.");
            valid = false;
        }else {
            priceEdit.setError(null);
        }

        return valid;

    } /*end isFormvalid*/


}
