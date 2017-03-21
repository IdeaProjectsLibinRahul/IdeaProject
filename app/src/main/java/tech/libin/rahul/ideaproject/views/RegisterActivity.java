package tech.libin.rahul.ideaproject.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.models.Login;
import tech.libin.rahul.ideaproject.views.models.RegisterModel;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.dialogs.FOSDialog;
import tech.libin.rahul.ideaproject.views.widgets.edittext.FOSIconEditText;

public class RegisterActivity extends AppCompatActivity {

    //region declarations
    private static final int CAMERA_PIC_REQUEST = 1003;
    private FloatingActionButton floatingActionButton;
    private FOSIconEditText editTextRegName;
    private FOSIconEditText editTextRegDOB;
    private FOSIconEditText editTextRegJoinDate;
    private FOSIconEditText editTextRegAddress1;
    private FOSIconEditText editTextRegAddress2;
    private FOSIconEditText editTextRegAddress3;
    private FOSIconEditText editTextRegZip;
    private FOSIconEditText editTextRegFatherName;
    private FOSIconEditText editTextRegMICode;
    private FOSIconEditText editTextRegMobileNo;
    private FOSIconEditText editTextRegPassword;
    private FOSIconEditText editTextRegConfirmPassword;
    private RadioButton rdbMico;
    private RadioButton rdbZsm;
    private RadioButton rdbExecutive;
    private ImageView imageViewRegProfilePic;
    private FOSButton buttonSubmit;
    private RadioGroup rdgRole;
    Uri mPhotoURI;
    private FOSDialog fosDialog;
    //endregion

    //region onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();
        setListeners();
    }
    //endregion

    //region initComponents
    private void initComponents() {
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        editTextRegName = (FOSIconEditText) findViewById(R.id.editTextRegName);
        editTextRegDOB = (FOSIconEditText) findViewById(R.id.editTextRegDOB);
        editTextRegJoinDate = (FOSIconEditText) findViewById(R.id.editTextRegJoinDate);
        editTextRegAddress1 = (FOSIconEditText) findViewById(R.id.editTextRegAddress1);
        editTextRegAddress2 = (FOSIconEditText) findViewById(R.id.editTextRegAddress2);
        editTextRegAddress3 = (FOSIconEditText) findViewById(R.id.editTextRegAddress3);
        editTextRegZip = (FOSIconEditText) findViewById(R.id.editTextRegZip);
        editTextRegFatherName = (FOSIconEditText) findViewById(R.id.editTextRegFatherName);
        editTextRegMICode = (FOSIconEditText) findViewById(R.id.editTextRegMICode);
        editTextRegMobileNo = (FOSIconEditText) findViewById(R.id.editTextRegMobileNo);
        editTextRegPassword = (FOSIconEditText) findViewById(R.id.editTextRegPassword);
        editTextRegConfirmPassword = (FOSIconEditText) findViewById(R.id.editTextRegConfirmPassword);
        imageViewRegProfilePic = (ImageView) findViewById(R.id.imageViewRegProfilePic);
        rdbMico = (RadioButton) findViewById(R.id.radio_mico);
        rdbZsm = (RadioButton) findViewById(R.id.radio_zsm);
        rdbExecutive = (RadioButton) findViewById(R.id.radio_executive);
        rdgRole = (RadioGroup) findViewById(R.id.radioGroupRegRole);
        buttonSubmit = (FOSButton) findViewById(R.id.buttonRegSignUp);
    }
    //endregion

    //region setListeners
    private void setListeners() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, CAMERA_PIC_REQUEST);

               // dispatchTakePictureIntent();
            }
        });

        rdgRole.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                // Check which radio button was clicked
                switch(checkedId) {
                    case R.id.radio_executive:
                        editTextRegMICode.setVisibility(View.VISIBLE);
                        break;
                    default:
                        editTextRegMICode.setVisibility(View.GONE);
                        break;
                }
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValid()) {
                    FOSFacade facade = new FOSFacadeImpl();
                    RegisterModel registerModel = new RegisterModel();
                    registerModel.setName(editTextRegName.getText().toString());
                    registerModel.setAddress1(editTextRegAddress1.getText().toString());
                    registerModel.setAddress2(editTextRegAddress2.getText().toString());
                    registerModel.setAddress3(editTextRegAddress3.getText().toString());
                    registerModel.setZip(editTextRegZip.getText().toString());
                    registerModel.setFatherName(editTextRegFatherName.getText().toString());
                    registerModel.setMiCode(editTextRegMICode.getText().toString());
                    registerModel.setMobileNum(editTextRegMobileNo.getText().toString());
                    registerModel.setPassword(editTextRegPassword.getText().toString());
                    if (rdbMico.isChecked()) {
                        registerModel.setRole(Constants.Role.MICO);
                    } else if (rdbZsm.isChecked()) {
                        registerModel.setRole(Constants.Role.ZSM);

                    } else if (rdbExecutive.isChecked()) {
                        registerModel.setRole(Constants.Role.EXECUTIVE);
                    }

                    registerModel.setName(editTextRegName.getText().toString());
                    registerModel.setName(editTextRegName.getText().toString());
                    registerModel.setDob(editTextRegDOB.getText().toString());
                    registerModel.setDateOfJoining(editTextRegJoinDate.getText().toString());

                    facade.doRegistrationDummy(registerModel, new ServiceCallback<String>() {
                        @Override
                        public void onResponse(String response) {
                            String title = "Success";
                            fosDialog = FOSDialog.newInstance(title, response);
                            fosDialog.show(getSupportFragmentManager(), "tag");

                        }

                        @Override
                        public void onRequestTimout() {
                            String title = "Info";
                            fosDialog = FOSDialog.newInstance(title, getResources().getString(R.string.warn_request_timed_out));
                            fosDialog.show(getSupportFragmentManager(), "tag");

                        }

                        @Override
                        public void onRequestFail(FOSError error) {
                            String message = error.getErrorMessage();
                            String title = "Info";
                            fosDialog = FOSDialog.newInstance(title, message);

                            fosDialog.show(getSupportFragmentManager(), "tag");
                        }
                    });
                }

//                Map<String, String> params = new HashMap<>();
//
//                params.put("name", editTextRegName.getText().toString());
//                params.put("miCode", editTextRegName.getText().toString());
//                params.put("mobileNum", editTextRegName.getText().toString());
//                params.put("dob", editTextRegName.getText().toString());
//                params.put("dateOfJoining", editTextRegName.getText().toString());
//                params.put("address1", editTextRegName.getText().toString());
//                params.put("address2", editTextRegName.getText().toString());
//                params.put("address3", editTextRegName.getText().toString());
//                params.put("zip", editTextRegName.getText().toString());
//                params.put("fatherName", editTextRegName.getText().toString());
//                params.put("password", editTextRegName.getText().toString());
//                Map<String, Uri> files= new HashMap<>();
//
//                if(mPhotoURI!=null) {
//                    files.put("profilePic",mPhotoURI);
//                }
//
//                FOSFacade facade = new FOSFacadeImpl();
//
//                facade.doRegistration(params, files, new ServiceCallback<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                    }
//
//                    @Override
//                    public void onRequestTimout() {
//
//                    }
//
//                    @Override
//                    public void onRequestFail(FOSError error) {
//
//                    }
//                });

            }
        });
    }
    //endregion

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
// Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir);
// Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                mPhotoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI);
                startActivityForResult(takePictureIntent, CAMERA_PIC_REQUEST);
            }
        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageViewRegProfilePic.getWidth();
        int targetH = imageViewRegProfilePic.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        imageViewRegProfilePic.setImageBitmap(bitmap);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
           if(mPhotoURI!=null)
           {
               setPic();
           }
        }
    }

    private boolean isValid()
    {
        editTextRegName.setError(null);
        editTextRegDOB.setError(null);
        editTextRegAddress1.setError(null);
        editTextRegZip.setError(null);
        editTextRegMobileNo.setError(null);
        editTextRegPassword.setError(null);
        editTextRegConfirmPassword.setError(null);
        editTextRegMICode.setError(null);

        boolean status=true;
        if(editTextRegName.getText().toString().trim().isEmpty()) {
            status=false;
            editTextRegName.setError(getResources().getString(R.string.warn_name));
        }
        if(editTextRegDOB.getText().toString().trim().isEmpty()) {
            status=false;
            editTextRegDOB.setError(getResources().getString(R.string.warn_dob));
        }
        if(editTextRegAddress1.getText().toString().trim().isEmpty()) {
            status=false;
            editTextRegAddress1.setError(getResources().getString(R.string.warn_address));
        }
        if(editTextRegZip.getText().toString().trim().isEmpty()) {
            status=false;
            editTextRegZip.setError(getResources().getString(R.string.warn_zip));
        }
        if(editTextRegMobileNo.getText().toString().trim().isEmpty()) {
            status=false;
            editTextRegMobileNo.setError(getResources().getString(R.string.warn_mobile));
        }
        if(editTextRegPassword.getText().toString().trim().isEmpty()) {
            status=false;
            editTextRegPassword.setError(getResources().getString(R.string.warn_password));
        }
        if((! editTextRegPassword.getText().toString().trim().isEmpty()) && ! editTextRegPassword.getText().toString().trim().equals(editTextRegConfirmPassword.getText().toString().trim())) {
            status = false;
            editTextRegConfirmPassword.setError(getResources().getString(R.string.warn_password_miss_match));
        }
        if(editTextRegMICode.getVisibility()==View.VISIBLE && editTextRegMICode.getText().toString().trim().isEmpty()) {
            status=false;
            editTextRegMICode.setError(getResources().getString(R.string.warn_mi_code));
        }

       return  status;
    }
    
}
