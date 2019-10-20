package com.teta_tm.sayeban.userprofile.userprofile4;

import android.app.Dialog;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;
//import com.google.android.gms.maps.MapView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.MapView;

import java.util.ArrayList;

public class SetAddress extends AppCompatActivity {
    private ImageView img_forward;
    private Button btn_setInfo, btn_setAddressOnMap;
    private EditText ed_first_lastName, ed_phoneNumReciver,ed_telNumRecivere,ed_TelCode,ed_postcode,
            ed_addressPoste;
    private Spinner sp_province,sp_city;
    private TextView tv_warning;
    private MapView mapView;
    DbHelper dbHelper;
    private ArrayList<String> provinceArray;
    private ArrayList<String> cityArray;
    ArrayAdapter<String> arrayAdapterCity;
    private static  final String TAG= "EditAddressInfoActivity";
    private static final int ERROR_DIALOG_REQUSET=9001;
//    static EditAdrsModel editAdrsModel;
//    public static ArrayList<EditAdrsModel> arrayListModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_address);

        inIt();

        btn_setAddressOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(EditAddressInfoActivity.this,MapsActivity.class);
//                startActivity(intent);
            }
        });

        refresherProReader();

        sp_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                refresherCityReader();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       /* btn_setInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListModel=new ArrayList<>();
                editAdrsModel=new EditAdrsModel();
                editAdrsModel.setFnameLname(ed_first_lastName.getText().toString());
                editAdrsModel.setProvinceCity( sp_city.getSelectedItem().toString()+"Ø´Ù‡Ø±: "+ "    " +sp_province.getSelectedItem().toString()+"Ø§Ø³ØªØ§Ù†: ");
                editAdrsModel.setAdrs(ed_addressPoste.getText().toString());
                editAdrsModel.setTelNum(ed_TelCode.getText().toString()+"-"+ed_telNumRecivere.getText().toString()+"Ø´Ù…Ø§Ø±Ù‡ ØªÙ…Ø§Ø³ Ø«Ø§Ø¨Øª: ");
                editAdrsModel.setMobNum(ed_phoneNumReciver.getText().toString()+"Ø´Ù…Ø§Ø±Ù‡ ØªÙ…Ø§Ø³ Ù‡Ù…Ø±Ø§Ù‡: ");
                editAdrsModel.setPstCode(ed_postcode.getText().toString());
//                arrayListModel.add(editAdrsModel);

                Intent intent= new Intent(EditAddressInfoActivity.this,MyAddressActivity.class);
                startActivity(intent);
            }
        });*/

    }


//    public boolean isServiceOk(){
//        Log.i(TAG, "isServiceOk: chacking google services version");
//        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(EditAddressInfoActivity.this);
//        if (available== ConnectionResult.SUCCESS){
//            // everything is fine and user can make map requests
//            Log.i(TAG, "isServiceOk: Google Play Servise is working");
//            return true;
//        }
//        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
//            //an error occured but we can resolve it.
//            Log.i(TAG, "isServiceOk: an error occured but we can resolve it");
//            Dialog dialog= GoogleApiAvailability.getInstance().getErrorDialog(EditAddressInfoActivity.this,available,ERROR_DIALOG_REQUSET);
//            dialog.show();
//        }
//        else {
//            Toast.makeText(this, "you can not make map requests", Toast.LENGTH_SHORT).show();
//        }
//        return false;
//    }

    public void refresherPro(){
        provinceArray= new ArrayList<>();
        dbHelper= new DbHelper(this);
        dbHelper.opendatabase();
        int num= dbHelper.num_pro("province");
        for (int i=0; i<num; i++){
            provinceArray.add(dbHelper.province("province", i));
        }

    }
    public void refresher_citis(String province){
        cityArray= new ArrayList<>();
        dbHelper= new DbHelper(this);
        dbHelper.opendatabase();
        int num= dbHelper.num_city(province);
        for (int i=0; i<num; i++){
            cityArray.add(dbHelper.citis(province, i));
        }

    }

    public void refresherProReader(){
        refresherPro();
        // set province data to spinner
        ArrayAdapter<String> arrayAdapterPro= new ArrayAdapter<>(this,R.layout.customized_spinner , provinceArray);
        arrayAdapterPro.setDropDownViewResource(R.layout.customized_spinner);
        sp_province.setAdapter(arrayAdapterPro);
    }

    public void refresherCityReader(){
        String province= sp_province.getSelectedItem().toString();
        refresher_citis(province);
        arrayAdapterCity= new ArrayAdapter<>(this,R.layout.customized_spinner , cityArray);
        arrayAdapterCity.setDropDownViewResource(R.layout.customized_spinner);
        sp_city.setAdapter(arrayAdapterCity);
    }


    public void inIt(){
        img_forward=findViewById(R.id.img_forward_back_address);
        img_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_setInfo=findViewById(R.id.btn_set_address_info);
        btn_setAddressOnMap=findViewById(R.id.btn_set_on_map);
        ed_first_lastName=findViewById(R.id.ed_first_last_name);
        ed_phoneNumReciver=findViewById(R.id.ed_phone_num_receiver);
        ed_telNumRecivere=findViewById(R.id.ed_tel_num);
        ed_TelCode=findViewById(R.id.ed_tel_code);
        ed_postcode=findViewById(R.id.ed_post_code);
        ed_addressPoste=findViewById(R.id.ed_post_address);
        sp_province=findViewById(R.id.spn_province_adrs);
        sp_city=findViewById(R.id.spn_city_adrs);
        mapView=findViewById(R.id.mapView);

    }
}
