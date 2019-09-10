package com.example.cardwallet;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCardActivity extends AppCompatActivity implements View.OnClickListener {

    Button cancel_btn,next_btn;
    ImageView add_card_image_add;
    EditText add_card_company_name_add,add_card_category_add,add_card_owner_name_add,add_company_address_add,add_company_email_add,add_company_contact;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findallidsforaddcard();
        next_btn.setOnClickListener(this);
        add_card_image_add.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);


        ///data from display card for edit any card and add

        Intent intent=getIntent();
        add_card_company_name_add.setText(intent.getStringExtra("company_name_for_edit"));
        add_card_owner_name_add.setText(intent.getStringExtra("company_owner_name__for_edit"));
        add_company_contact.setText(intent.getStringExtra("company_contact_edit"));
        add_company_address_add.setText(intent.getStringExtra("comapny_address_edit"));
        add_company_email_add.setText(intent.getStringExtra("company_email_edit"));
        add_card_category_add.setText(intent.getStringExtra("company_category_edit"));
//
//        cancel_btn.setOnClickListener(this);
//        next_btn.setOnClickListener(this);
//
//



    }

    private void findallidsforaddcard() {

        cancel_btn=findViewById(R.id.cancel_btn);
        next_btn=findViewById(R.id.add_next_btn);
        add_card_image_add=findViewById(R.id.add_card_image_add);
        add_card_company_name_add=findViewById(R.id.add_card_company_name_add);
        add_card_category_add=findViewById(R.id.add_card_category_add);
        add_card_owner_name_add=findViewById(R.id.add_card_owner_name_add);
        add_company_contact=findViewById(R.id.add_card_contact_add);
        add_company_email_add=findViewById(R.id.add_card_email_add);
        add_company_address_add=findViewById(R.id.add_card_address_add);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cancel_btn:
                finish();
                break;


            case R.id.add_card_image_add:
               // showPictureDialog();

                break;

            case R.id.add_next_btn:

                boolean invalid = false;

                if (add_card_company_name_add.getText().toString().equals(""))
                {
                    invalid=true;
                    Toast.makeText(this, "Enter Company Name", Toast.LENGTH_SHORT).show();

                }
                else if (add_card_category_add.getText().toString().equals(""))
                {
                    invalid=true;
                    Toast.makeText(this, "Enter Card Category", Toast.LENGTH_SHORT).show();

                }
                else if(add_card_owner_name_add.getText().toString().equals(""))
                {
                    invalid=true;
                    Toast.makeText(this, "Enter Card Owner Name", Toast.LENGTH_SHORT).show();

                }
                else if (add_company_contact.getText().toString().equals(""))
                {
                    invalid=true;
                    Toast.makeText(this, "Enter Contact Number", Toast.LENGTH_SHORT).show();
                }

                final String email = add_company_email_add.getText().toString();
                if (!isValidEmail(email)) {
                    invalid = true;
                    Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                else if (add_company_address_add.getText().toString().equals(""))
                {
                    invalid=true;
                    Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
                }

                if (invalid != true) {

                    Intent display = new Intent(AddCardActivity.this, DisplayCardActivity.class);
                    display.putExtra("company_image",add_card_image_add.getImageAlpha());
                    display.putExtra("company_name",add_card_company_name_add.getText().toString());
                    display.putExtra("company_category",add_card_category_add.getText().toString());
                    display.putExtra("comapny_owner_name",add_card_owner_name_add.getText().toString());
                    display.putExtra("company_contact_number",add_company_contact.getText().toString());
                    display.putExtra("company_email",add_company_email_add.getText().toString());
                    display.putExtra("company_address",add_company_address_add.getText().toString());

//                    ByteArrayOutputStream _bs = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
//                    display.putExtra("byteArray", _bs.toByteArray());

                    startActivity(display);
                }
                break;

        }
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    // validating password with retype password
    private boolean isValidcompanyname(String name) {
        if (name == null) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {


    }










    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent,GALLERY);
    }
    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                     bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(AddCardActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    add_card_image_add.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddCardActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            add_card_image_add.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(AddCardActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }




}
