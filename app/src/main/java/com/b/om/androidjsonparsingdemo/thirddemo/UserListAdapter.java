package com.b.om.androidjsonparsingdemo.thirddemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.b.om.androidjsonparsingdemo.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private Context context;
    private List<UserModel> userModels;

    public UserListAdapter(Context context,List<UserModel> userModels){

        this.context = context;
        this.userModels = userModels;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        UserModel model = userModels.get(position);

        holder.tvName.setText("Name :- "+model.getName());
        holder.tvCountry.setText("Country :- "+ model.getCountry());
        holder.tvCity.setText("City :- " + model.getCity());

        Glide.with(context).load(model.getImgUrl()).placeholder(R.drawable.placeholder).skipMemoryCache(true).into(holder.ivUser);
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivUser;
        private TextView tvName,tvCountry,tvCity;

        public UserViewHolder(View itemView) {
            super(itemView);

            ivUser = (ImageView) itemView.findViewById(R.id.iv_user);
            tvName = (TextView) itemView.findViewById(R.id.tv_user_name);
            tvCountry = (TextView) itemView.findViewById(R.id.tv_country);
            tvCity = (TextView) itemView.findViewById(R.id.tv_city);
        }
    }
}
