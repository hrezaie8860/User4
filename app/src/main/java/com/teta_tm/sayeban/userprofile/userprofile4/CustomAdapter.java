package com.teta_tm.sayeban.userprofile.userprofile4;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    private Context mContext;
    private ArrayList<String> arrayListItems;
//    EditAddressInfoActivity editAddressInfoActivity;
//    EditAddressInfoActivity arrayListModel;
//    EditAdrsModel editAdrsModel;
    private String TAG="customAdapter";



    public CustomAdapter(@NonNull Context mContext, int layout, ArrayList<String> arrayListItems) {
        super(mContext,layout,arrayListItems);
        this.mContext=mContext;
        this.arrayListItems=arrayListItems;
//        this.arrayListModel=arrayListModel;

    }

//    public CustomAdapter( int layout, ArrayList arrayListModel,@NonNull Context mContext) {
//        super(layout,arrayListModel,mContext);
//        this.mContext=mContext;
//        this.arrayListModel=arrayListModel;
//    }

    private static class ViewHolder {
        TextView txtItem,fnameLname,proCity,adrs,telNum,moblNum;
        ImageView imgItem;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
//        String item = (String) getItem(position);
//        EditAdrsModel editAdrsModel= (EditAdrsModel) getItem(position);
        ViewHolder viewHolder;
        final View result;
//        editAdrsModel= new EditAdrsModel();
        viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        Log.i(TAG, "getView: before conditions");
        if (arrayListItems!=null){
            if (convertView == null) {
                Log.i(TAG, "getView: Item list- convertView== null");
                convertView = inflater.inflate(R.layout.listview_raw, parent, false);
                viewHolder.txtItem = convertView.findViewById(R.id.tv_item);
                viewHolder.imgItem = convertView.findViewById(R.id.img_item);

                result = convertView;

                convertView.setTag(viewHolder);

            } else {
                Log.i(TAG, "getView: item list- convertView is not null");
                viewHolder = (ViewHolder) convertView.getTag();
                result = convertView;
            }
            viewHolder.txtItem.setText(arrayListItems.get(position));
            switch (position) {
                case 0:
                    viewHolder.imgItem.setImageResource(R.drawable.round_edit_24);
                    break;
                case 1:
                    viewHolder.imgItem.setImageResource(R.drawable.round_list_alt_24);
                    break;
                case 2:
                    viewHolder.imgItem.setImageResource(R.drawable.round_favorite_24);
                    break;
                case 3:
                    viewHolder.imgItem.setImageResource(R.drawable.round_email_24);
                    break;
                case 4:
                    viewHolder.imgItem.setImageResource(R.drawable.round_location_on_24);
                    break;
                case 5:
                    viewHolder.imgItem.setImageResource(R.drawable.round_credit_card_24);
                    break;
                case 6:
                    viewHolder.imgItem.setImageResource(R.drawable.round_security_24);
                    break;
                case 7:
                    viewHolder.imgItem.setImageResource(R.drawable.round_exit_to_app_24);
            }
            return convertView;
        }
        else {
            Log.i(TAG, "getView: row address _ filling row");
//            arrayListModel=new EditAddressInfoActivity();
//            if (convertView==null){
            Log.i(TAG, "getView: row address _ convertView is null");
            //            editAdrsModel=new EditAdrsModel();
            convertView=inflater.inflate(R.layout.lv_adress_row,parent,false);
            viewHolder.fnameLname=convertView.findViewById(R.id.tv_nameLastname_adrs_row);
            viewHolder.proCity=convertView.findViewById(R.id.tv_pro_city_adrs_row);
            viewHolder.adrs=convertView.findViewById(R.id.tv_adres_asrs_row);
            viewHolder.telNum=convertView.findViewById(R.id.tv_tel_adrs_row);
            viewHolder.moblNum=convertView.findViewById(R.id.tv_mobile_adrs_row);
            convertView.setTag(viewHolder);
//            }
//            else {
            Log.i(TAG, "getView: row address _ convertView is not null");
//                viewHolder= (ViewHolder) convertView.getTag();
//            }


//            viewHolder.fnameLname.setText(arrayListModel.get(position).getFnameLname());
//            viewHolder.proCity.setText(arrayListModel.get(position).getProvinceCity());
//            viewHolder.adrs.setText(arrayListModel.get(position).getAdrs());
//            viewHolder.telNum.setText(arrayListModel.get(position).getTelNum());
//            viewHolder.moblNum.setText(arrayListModel.get(position).getMobNum());

//            viewHolder.fnameLname.setText(editAdrsModel.getFnameLname());
//            viewHolder.proCity.setText(editAdrsModel.getProvinceCity());
//            viewHolder.adrs.setText(editAdrsModel.getAdrs());
//            viewHolder.telNum.setText(editAdrsModel.getTelNum());
//            viewHolder.moblNum.setText(editAdrsModel.getMobNum());
            return convertView;


        }


    }
}

