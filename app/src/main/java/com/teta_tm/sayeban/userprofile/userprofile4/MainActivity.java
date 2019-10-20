package com.teta_tm.sayeban.userprofile.userprofile4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private ImageView img_forward;
    private TextView tv_name,tv_email;
    private ListView lv_list_items;
    private String [] items;
    private ArrayList<String> arrayList_items;
    private CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inIt();
        fillList();

        adapter= new CustomAdapter(MainActivity.this,R.layout.listview_raw,arrayList_items);
        lv_list_items.setAdapter(adapter);

        lv_list_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listItems(position);
            }
        });


//        fill these items:
//        tv_name.setText(name);
//        tv_email.setText(email);

        img_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                going to previous activity
            }
        });

}
    public void listItems(int position){
        switch (position){
            case 0:
                Intent i0=new Intent(MainActivity.this,EditInfoActivity.class);
                i0.putExtra("editInfo",position);
                MainActivity.this.startActivity(i0);
                break;
            case 1:
                Intent i1=new Intent(MainActivity.this,EditInfoActivity.class);
                i1.putExtra("editInfo",position);
                MainActivity.this.startActivity(i1);
                break;
            case 2:
                Intent i2=new Intent(MainActivity.this,EditInfoActivity.class);
                i2.putExtra("editInfo",position);
                MainActivity.this.startActivity(i2);
                break;
            case 3:
                Intent i3=new Intent(MainActivity.this,EditInfoActivity.class);
                i3.putExtra("editInfo",position);
                MainActivity.this.startActivity(i3);
                break;
            case 4:
                Intent i4=new Intent(MainActivity.this,MyAddressList.class);
                i4.putExtra("addAddress",position);
                MainActivity.this.startActivity(i4);
                break;
            case 5:
                Intent i5=new Intent(MainActivity.this,EditInfoActivity.class);
                i5.putExtra("editInfo",position);
                MainActivity.this.startActivity(i5);
                break;
            case 6:
                Intent i6=new Intent(MainActivity.this,PassActivity.class);
                i6.putExtra("editInfo",position);
                MainActivity.this.startActivity(i6);
                break;
            case 7:
                Intent i7=new Intent(MainActivity.this,EditInfoActivity.class);
                i7.putExtra("editInfo",position);
                MainActivity.this.startActivity(i7);

        }
    }

    public void inIt(){
        img_forward=findViewById(R.id.img_forward_main_activity);
        tv_name=findViewById(R.id.tv_name_profile);
        tv_email=findViewById(R.id.tv_email_profile);
        lv_list_items= findViewById(R.id.lv_list_items);
        items= getResources().getStringArray(R.array.items);
    }

    private void fillList() {
        arrayList_items = new ArrayList<>();
        for (int i = 0; i < items.length; i++){
//            String itm = new String(items[i]);
            arrayList_items.add(items[i]);
        }
    }






}