package com.b.om.androidjsonparsingdemo.seconddemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.b.om.androidjsonparsingdemo.R;

import java.util.List;

/**
 * Created by bhavin on 1/21/2019.
 */

public class ContactAdapter extends ArrayAdapter {

    private Context context;
    private List<Contacts> contactsList;
    private LayoutInflater inflater;
    private int resource;

    public ContactAdapter(Context context, int resource, List<Contacts> contactses) {
        super(context, resource,contactses);
        this.context = context;
        this.resource = resource;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contactsList = contactses;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ContactViewHolder holder = null;

        if (convertView == null){
            convertView = inflater.inflate(resource,null);
            holder = new ContactViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ContactViewHolder) convertView.getTag();
        }

        holder.name.setText(contactsList.get(position).getName());
        holder.email.setText(contactsList.get(position).getEmail());
        holder.address.setText(contactsList.get(position).getAddress());
        holder.mobile.setText(contactsList.get(position).getMobile());
        holder.home.setText(contactsList.get(position).getHome());
        holder.office.setText(contactsList.get(position).getOffice());

        return convertView;
    }

    static class ContactViewHolder{

        private TextView name,email,address,mobile,home,office;

        public ContactViewHolder(View view){
            name = (TextView)view.findViewById(R.id.tv_name_list_contact);
            email = (TextView)view.findViewById(R.id.tv_email_list_contact);
            address = (TextView)view.findViewById(R.id.tv_address_list_contact);
            mobile = (TextView)view.findViewById(R.id.tv_mobile_list_contact);
            home = (TextView)view.findViewById(R.id.tv_home_list_contact);
            office = (TextView)view.findViewById(R.id.tv_office_list_contact);
        }
    }
}
