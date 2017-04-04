package tech.libin.rahul.ideaproject.views;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.requests.RegisterRequest;
import tech.libin.rahul.ideaproject.service.responses.RegisterResponse;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseActivity;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.dialogs.FOSDialog;
import tech.libin.rahul.ideaproject.views.widgets.edittext.FOSIconEditText;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

public class RegisterActivity extends FOSBaseActivity {

    public static final int FLIP_INTERVAL = 2000;
    public static final String COLOR_TRANSPARENT = "#00000000";
    public static final String COLOR_BACKGROUND = "#55000000";
    public static final String DIR_NAME = "MyVisit";
    public static final String USER_PIC_PREFIX = "user_pic_";
    private static final int PERMISSION_REQUEST_CODE = 1001;
    //region declarations
    private static final int CAMERA_PIC_REQUEST = 1003;
    private static final int SELECT_PICTURE = 1004;
    private static final String TAG = RegisterActivity.class.getName();
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

    private Uri outputFileUri;
    private FOSFacade facade;

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

    @Override
    protected void setToolbarElevation() {
        setToolbarElevation(0);
    }

    @Override
    protected void setHasToolBar() {
        setHasToolBar(false);
    }
    //endregion

    //region initComponents
    private void initComponents() {
        facade = new FOSFacadeImpl();
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
                boolean storagePermission = ActivityCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

                if (!storagePermission) {
                    ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                } else {
                    openImageIntent();
                }

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

                    sendRegisterRequest();
//                    RegisterModel registerModel = new RegisterModel();
//                    registerModel.setName(editTextRegName.getText());
//                    registerModel.setAddress1(editTextRegAddress1.getText());
//                    registerModel.setAddress2(editTextRegAddress2.getText());
//                    registerModel.setAddress3(editTextRegAddress3.getText());
//                    registerModel.setZip(editTextRegZip.getText());
//                    registerModel.setFatherName(editTextRegFatherName.getText());
//                    registerModel.setMiCode(editTextRegMICode.getText());
//                    registerModel.setMobileNum(editTextRegMobileNo.getText());
//                    registerModel.setPassword(editTextRegPassword.getText());
//                    if (rdbMico.isChecked()) {
//                        registerModel.setRole(Constants.Role.MICO);
//                    } else if (rdbZsm.isChecked()) {
//                        registerModel.setRole(Constants.Role.ZSM);
//
//                    } else if (rdbExecutive.isChecked()) {
//                        registerModel.setRole(Constants.Role.EXECUTIVE);
//                    }
//
//                    registerModel.setName(editTextRegName.getText());
//                    registerModel.setName(editTextRegName.getText());
//                    registerModel.setDob(editTextRegDOB.getText());
//                    registerModel.setDateOfJoining(editTextRegJoinDate.getText());
//
//                    facade.doRegistrationDummy(registerModel, new ServiceCallback<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            String title = "Success";
//                            fosDialog = FOSDialog.newInstance(RegisterActivity.this, title, response, true);
//                            fosDialog.show(getSupportFragmentManager(), "tag");
//
//                        }
//
//                        @Override
//                        public void onRequestTimout() {
//                            String title = "Info";
//                            fosDialog = FOSDialog.newInstance(RegisterActivity.this, title, getResources().getString(R.string.warn_request_timed_out), false);
//                            fosDialog.show(getSupportFragmentManager(), "tag");
//
//                        }
//
//                        @Override
//                        public void onRequestFail(FOSError error) {
//                            String message = error.getErrorMessage();
//                            String title = "Info";
//                            fosDialog = FOSDialog.newInstance(RegisterActivity.this, title, message, false);
//
//                            fosDialog.show(getSupportFragmentManager(), "tag");
//                        }
//                    });
                }


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

    private void sendRegisterRequest() {
        String name = editTextRegName.getText();
        String miCode = editTextRegMICode.getText();
        Constants.Role role = null;
        if (rdbMico.isChecked()) {
            role = Constants.Role.MICO;
        } else if (rdbZsm.isChecked()) {
            role = Constants.Role.ZSM;

        } else if (rdbExecutive.isChecked()) {
            role = Constants.Role.EXECUTIVE;
        }

        String mobileNo = editTextRegMobileNo.getText();
        String dob = editTextRegDOB.getText();
        String doj = editTextRegJoinDate.getText();
        String address1 = editTextRegAddress1.getText();
        String address2 = editTextRegAddress2.getText();
        String address3 = editTextRegAddress3.getText();
        String zip = editTextRegZip.getText();
        String fatherName = editTextRegFatherName.getText();
        String password = editTextRegPassword.getText();

        Map<String, String> data = new HashMap<>();
        data.put(RegisterRequest.NAME, name);
        data.put(RegisterRequest.MI_CODE, miCode);
        data.put(RegisterRequest.ROLE, role.toString());
        data.put(RegisterRequest.MOBILE_NUM, mobileNo);
        data.put(RegisterRequest.DOB, dob);
        data.put(RegisterRequest.DOJ, doj);
        data.put(RegisterRequest.ADDRESS1, address1);
        data.put(RegisterRequest.ADDRESS2, address2);
        data.put(RegisterRequest.ADDRESS3, address3);
        data.put(RegisterRequest.ZIP, zip);
        data.put(RegisterRequest.FATHER_NAME, fatherName);
        data.put(RegisterRequest.PASSWORD, password);

        Map<String, Uri> files = new HashMap<>();
        files.put(RegisterRequest.IMAGE, mPhotoURI);

        facade.doRegistration(data, files, new ServiceCallback<RegisterResponse>() {
            @Override
            public void onResponse(RegisterResponse response) {
                String title = "Success";
                fosDialog = FOSDialog.newInstance(RegisterActivity.this, title, response.getMessage(), true);
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
                } else if (view == tabItemOfficial) {
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

    private void openImageIntent() {

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment
                            .getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, CAMERA_PIC_REQUEST);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_PICTURE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "onRequestPermissionsResult: Permission Granted");
                openImageIntent();
            } else {

                String message = getString(R.string.storage_permission_message);
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_PIC_REQUEST) {
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bm;
                    BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                    btmapOptions.inSampleSize = 2;
                    bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            btmapOptions);

                    // bm = Bitmap.createScaledBitmap(bm, 70, 70, true);
                    imageViewRegProfilePic.setImageBitmap(bm);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "test";
                    f.delete();
                    OutputStream fOut = null;
                    File file = new File(path, String.valueOf(System
                            .currentTimeMillis()) + ".jpg");
                    fOut = new FileOutputStream(file);
                    bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                    fOut.flush();
                    fOut.close();
                    mPhotoURI = Uri.fromFile(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                mPhotoURI = selectedImageUri;
                String tempPath = getPath(selectedImageUri, RegisterActivity.this);
                Bitmap bm;
                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                btmapOptions.inSampleSize = 2;
                bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                imageViewRegProfilePic.setImageBitmap(bm);
            }
        }

    }

    public String getPath(Uri uri, Activity activity) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = activity
                .managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
        int slideIndex = 0;
        if (editTextRegName.getText().trim().isEmpty()) {
            status = false;
            editTextRegName.setError(getResources().getString(R.string.warn_name));
            slideIndex = 0;
        }
        if (editTextRegDOB.getText().trim().isEmpty()) {
            status = false;
            editTextRegDOB.setError(getResources().getString(R.string.warn_dob));
            slideIndex = 0;
        }
        if (editTextRegAddress1.getText().trim().isEmpty()) {
            status = false;
            editTextRegAddress1.setError(getResources().getString(R.string.warn_address));
            slideIndex = 0;
        }
        if (editTextRegZip.getText().trim().isEmpty()) {
            status = false;
            editTextRegZip.setError(getResources().getString(R.string.warn_zip));
            slideIndex = 0;
        }
        if (editTextRegMobileNo.getText().trim().isEmpty()) {
            status = false;
            editTextRegMobileNo.setError(getResources().getString(R.string.warn_mobile));
            slideIndex = 1;
        }
        if (editTextRegPassword.getText().trim().isEmpty()) {
            status = false;
            editTextRegPassword.setError(getResources().getString(R.string.warn_password));
            slideIndex = 1;
        }
        if ((!editTextRegPassword.getText().trim().isEmpty()) && !editTextRegPassword.getText().trim().equals(editTextRegConfirmPassword.getText().trim())) {
            status = false;
            editTextRegConfirmPassword.setError(getResources().getString(R.string.warn_password_miss_match));
            slideIndex = 1;
        }
        if (editTextRegMICode.getVisibility() == View.VISIBLE && editTextRegMICode.getText().trim().isEmpty()) {
            status = false;
            editTextRegMICode.setError(getResources().getString(R.string.warn_mi_code));
            slideIndex = 2;
        }

        if (!status) {
            slideTabView(slideIndex);
        }

        return status;
    }

}
