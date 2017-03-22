package tech.libin.rahul.ideaproject.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ViewFlipper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.models.RegisterModel;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.dialogs.FOSDialog;
import tech.libin.rahul.ideaproject.views.widgets.edittext.FOSIconEditText;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

public class RegisterActivity extends AppCompatActivity {

    public static final int FLIP_INTERVAL = 2000;
    public static final String COLOR_TRANSPARENT = "#00000000";
    public static final String COLOR_BACKGROUND = "#55000000";
    //region declarations
    private static final int CAMERA_PIC_REQUEST = 1003;
    String mCurrentPhotoPath;
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
    private Uri mPhotoURI;
    private FOSDialog fosDialog;

    private ViewFlipper viewFlipper;
    private FOSTextView tabItemPersonal;
    private FOSTextView tabItemLogin;
    private FOSTextView tabItemOfficial;

    private EditText selectedEditText;

    private float lastX;
    //endregion

    //region onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();
        setListeners();
        initViewFlipper();
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
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        tabItemPersonal = (FOSTextView) findViewById(R.id.tabItemPersonal);
        tabItemLogin = (FOSTextView) findViewById(R.id.tabItemLogin);
        tabItemOfficial = (FOSTextView) findViewById(R.id.tabItemOfficial);
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
                switch (checkedId) {
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

                if (isValid()) {
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
                            fosDialog = FOSDialog.newInstance(RegisterActivity.this, title, response, true);
                            fosDialog.show(getSupportFragmentManager(), "tag");

                        }

                        @Override
                        public void onRequestTimout() {
                            String title = "Info";
                            fosDialog = FOSDialog.newInstance(RegisterActivity.this, title, getResources().getString(R.string.warn_request_timed_out), false);
                            fosDialog.show(getSupportFragmentManager(), "tag");

                        }

                        @Override
                        public void onRequestFail(FOSError error) {
                            String message = error.getErrorMessage();
                            String title = "Info";
                            fosDialog = FOSDialog.newInstance(RegisterActivity.this, title, message, false);

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

        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                try {
                    FOSIconEditText editText1 = (FOSIconEditText) view;
                    selectedEditText = editText1.getEditText();
                } catch (Exception e) {
                    EditText editText1 = (EditText) view;
                    selectedEditText = editText1;
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DAY_OF_YEAR, -1);
                    int yy = calendar.get(Calendar.YEAR);
                    int mm = calendar.get(Calendar.MONTH);
                    int dd = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                            selectedEditText.setText(String.format("%02d/%02d/%d", month + 1, date, year));
                        }
                    };
                    DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, dateSetListener, yy, mm, dd);
                    datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                    datePickerDialog.show();
                    return true;
                }
                return false;
            }
        };
        editTextRegDOB.setOnTouchListener(touchListener);
        editTextRegDOB.getEditText().setOnTouchListener(touchListener);
        editTextRegJoinDate.setOnTouchListener(touchListener);
        editTextRegJoinDate.getEditText().setOnTouchListener(touchListener);
    }

    private void initViewFlipper() {
        viewFlipper.setFlipInterval(FLIP_INTERVAL);

        View.OnClickListener tabClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tabItemPersonal.setTypeface(tabItemPersonal.getTypeface(), Typeface.NORMAL);
                tabItemPersonal.setTextColor(Color.BLACK);
                tabItemPersonal.setBackgroundColor(Color.parseColor(COLOR_TRANSPARENT));

                tabItemLogin.setTypeface(tabItemLogin.getTypeface(), Typeface.NORMAL);
                tabItemLogin.setBackgroundColor(Color.parseColor(COLOR_TRANSPARENT));
                tabItemLogin.setTextColor(Color.BLACK);

                tabItemOfficial.setTypeface(tabItemOfficial.getTypeface(), Typeface.NORMAL);
                tabItemOfficial.setBackgroundColor(Color.parseColor(COLOR_TRANSPARENT));
                tabItemOfficial.setTextColor(Color.BLACK);

                FOSTextView textView = (FOSTextView) view;
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView.setBackgroundColor(Color.parseColor(COLOR_BACKGROUND));
                textView.setTextColor(Color.WHITE);

                int nextView;

                if (view == tabItemPersonal) {
                    nextView = 0;
                } else if (view == tabItemLogin) {
                    nextView = 1;
                } else {
                    nextView = 2;
                }

                slideTabView(nextView);
            }
        };

        tabItemPersonal.setOnClickListener(tabClickListener);
        tabItemLogin.setOnClickListener(tabClickListener);
        tabItemOfficial.setOnClickListener(tabClickListener);
    }

    private void slideTabView(int nextView) {
        int currentView = viewFlipper.getDisplayedChild();

        Animation inRight = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.slide_in_from_left);
        Animation outRight = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.slide_out_to_right);

        Animation inLeft = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.slide_in_from_right);
        Animation outLeft = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.slide_out_to_left);

        if (nextView > currentView) {
            viewFlipper.setInAnimation(inLeft);
            viewFlipper.setOutAnimation(outLeft);

            if (nextView - currentView > 1) {
                viewFlipper.showNext();
                viewFlipper.showNext();
            } else {
                viewFlipper.showNext();
            }
        } else if (nextView < currentView) {
            viewFlipper.setInAnimation(inRight);
            viewFlipper.setOutAnimation(outRight);

            if (nextView - currentView < -1) {
                viewFlipper.showPrevious();
                viewFlipper.showPrevious();
            } else {
                viewFlipper.showPrevious();
            }
        }
    }

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
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

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
            if (mPhotoURI != null) {
                setPic();
            }
        }
    }

    private boolean isValid() {
        editTextRegName.setError(null);
        editTextRegDOB.setError(null);
        editTextRegAddress1.setError(null);
        editTextRegZip.setError(null);
        editTextRegMobileNo.setError(null);
        editTextRegPassword.setError(null);
        editTextRegConfirmPassword.setError(null);
        editTextRegMICode.setError(null);

        boolean status = true;
        if (editTextRegName.getText().toString().trim().isEmpty()) {
            status = false;
            editTextRegName.setError(getResources().getString(R.string.warn_name));
            slideTabView(0);
        }
        if (editTextRegDOB.getText().toString().trim().isEmpty()) {
            status = false;
            editTextRegDOB.setError(getResources().getString(R.string.warn_dob));
            slideTabView(0);
        }
        if (editTextRegAddress1.getText().toString().trim().isEmpty()) {
            status = false;
            editTextRegAddress1.setError(getResources().getString(R.string.warn_address));
            slideTabView(0);
        }
        if (editTextRegZip.getText().toString().trim().isEmpty()) {
            status = false;
            editTextRegZip.setError(getResources().getString(R.string.warn_zip));
            slideTabView(0);
        }
        if (editTextRegMobileNo.getText().toString().trim().isEmpty()) {
            status = false;
            editTextRegMobileNo.setError(getResources().getString(R.string.warn_mobile));
            slideTabView(1);
        }
        if (editTextRegPassword.getText().toString().trim().isEmpty()) {
            status = false;
            editTextRegPassword.setError(getResources().getString(R.string.warn_password));
            slideTabView(1);
        }
        if ((!editTextRegPassword.getText().toString().trim().isEmpty()) && !editTextRegPassword.getText().toString().trim().equals(editTextRegConfirmPassword.getText().toString().trim())) {
            status = false;
            editTextRegConfirmPassword.setError(getResources().getString(R.string.warn_password_miss_match));
            slideTabView(1);
        }
        if (editTextRegMICode.getVisibility() == View.VISIBLE && editTextRegMICode.getText().toString().trim().isEmpty()) {
            status = false;
            editTextRegMICode.setError(getResources().getString(R.string.warn_mi_code));
            slideTabView(2);
        }

        return status;
    }

}
