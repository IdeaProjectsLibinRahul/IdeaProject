package tech.libin.rahul.ideaproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import tech.libin.rahul.ideaproject.R;

public class RegisterActivity extends AppCompatActivity {

    private static final int CAMERA_PIC_REQUEST = 1003;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();
        setListeners();
    }

    private void initComponents() {
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
    }

    private void setListeners() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, CAMERA_PIC_REQUEST);
            }
        });
    }
}
