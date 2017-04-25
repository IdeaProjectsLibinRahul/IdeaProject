package tech.libin.rahul.ideaproject.views.detailsview.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import tech.libin.rahul.ideaproject.R;


/**
 * Created by libin on 05/03/17.
 */

public class FOSMapExploreDialog extends DialogFragment implements OnMapReadyCallback {
    private static View view;
    private ImageView imageViewBackButton;
    private TextView textViewName;
    private GoogleMap mMap;
    private double latitude = 0L;
    private double longitude = 0L;
    private SupportMapFragment mapFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }

        view = inflater.inflate(R.layout.dialog_view_map, container, false);

        initComponents();
        setListeners();
        parseBundle();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
    }

    private void initComponents() {
        imageViewBackButton = (ImageView) view.findViewById(R.id.imageViewBackButton);
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private void setListeners() {
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void parseBundle() {

        textViewName.setText(getArguments().getString("name"));
        latitude = Double.parseDouble(getArguments().getString("latitude"));
        longitude = Double.parseDouble(getArguments().getString("longitude"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment fragment = (fm.findFragmentById(R.id.map));
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        drawLocation();
    }

    private void drawLocation() {
        LatLng latLng;
        latLng = new LatLng(latitude, longitude);

        mMap.addMarker(new MarkerOptions().position(latLng)
                .title("here"));
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(latLng, 12);
        mMap.animateCamera(location);
    }
}
