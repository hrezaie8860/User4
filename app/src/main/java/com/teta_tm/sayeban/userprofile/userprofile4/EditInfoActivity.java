package com.teta_tm.sayeban.userprofile.userprofile4;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

import static android.graphics.Color.GRAY;

public class EditInfoActivity extends AppCompatActivity {

    private ImageView img_forward,img_cart ,img_selectBirthDate;
    private Button btn_setInfo;
    private EditText ed_firstName, ed_lastName,ed_nationalCode,ed_telNum,ed_phoneNum;
    private RadioButton rb_man,rb_woman;
    private Spinner sp_province,sp_city;
    private TextView tv_birthDate,tv_warning;
    private PersianDatePickerDialog picker;
    DbHelper dbHelper;
    private ArrayList<String> provinceArray;
    private ArrayList<String> cityArray;
    ArrayAdapter<String> arrayAdapterCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        inIt();

        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//going to cart
            }
        });

        img_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        img_selectBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                select birthday date and set it.
                dialog();
            }
        });
        btn_setInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//record information:
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
    }

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
    public void dialog(){
        PersianCalendar initDate = new PersianCalendar();
        initDate.setPersianDate(1360, 3, 13);
        PersianCalendar date=initDate;
//        picker.setInitDate(initDate);
        picker = new PersianDatePickerDialog(this)
                .setPositiveButtonString("Ø§Ù†ØªØ®Ø§Ø¨")
                .setNegativeButton("Ø¨ÛŒØ®ÛŒØ§Ù„")
//                .setTodayButton("Ø§Ù…Ø±ÙˆØ²")
                .setTodayButtonVisible(false)
                .setInitDate(initDate)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setMinYear(1300)
                .setActionTextColor(GRAY)
//                .setTypeFace(typeface)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
//                        Toast.makeText(EditInfoActivity.this, persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay(), Toast.LENGTH_SHORT).show();
                        tv_birthDate.setText(persianCalendar.getPersianYear() + "/"
                                + persianCalendar.getPersianMonth() + "/"
                                + persianCalendar.getPersianDay());
                    }
                    @Override
                    public void onDismissed() {
                    }
                });

        picker.show();
    }
    public void inIt(){
        tv_birthDate=findViewById(R.id.tv_birth_date);
        tv_warning=findViewById(R.id.tv_warning);
        sp_province=findViewById(R.id.spn_province);
        sp_city=findViewById(R.id.spn_city);
        rb_man=findViewById(R.id.rb_man);
        rb_woman=findViewById(R.id.rb_woman);
        ed_phoneNum=findViewById(R.id.ed_mobile_num);
        ed_telNum=findViewById(R.id.ed_tel_num);
        ed_nationalCode=findViewById(R.id.ed_national_code);
        ed_firstName=findViewById(R.id.ed_first_name);
        ed_lastName=findViewById(R.id.ed_last_name);
        img_forward=findViewById(R.id.img_forward);
        img_cart=findViewById(R.id.img_cart);
        img_selectBirthDate=findViewById(R.id.img_set_birth_date);
        btn_setInfo=findViewById(R.id.btn_set_edited_info);
    }


}

