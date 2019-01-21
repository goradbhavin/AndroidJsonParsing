package com.b.om.androidjsonparsingdemo.firstdemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.b.om.androidjsonparsingdemo.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhavin on 1/20/2019.
 */

public class MovieAdapter extends ArrayAdapter{

    private Context mContext;
    private List<Movie> movieList = new ArrayList<>();
    private int resource;
    private LayoutInflater inflater;

    public MovieAdapter(Context context,int resource,List<Movie> movies){
        super(context,resource,movies);
        mContext = context;
        this.resource = resource;
        movieList = movies;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        MovieViewHolder holder = null;

        if (convertView == null){
            convertView = inflater.inflate(resource,null);
            holder = new MovieViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MovieViewHolder) convertView.getTag();
        }

        Glide.with(mContext).load(movieList.get(position).getImage()).into(holder.ivMovieIcon);
        holder.tvMovie.setText(movieList.get(position).getMovie());
        holder.tvTagline.setText(movieList.get(position).getTagline());
        holder.tvYear.setText("Year: " + movieList.get(position).getYear());
        holder.tvDuration.setText("Duration:" + movieList.get(position).getDuration());
        holder.tvDirector.setText("Director:" + movieList.get(position).getDirector());

        // rating bar
        holder.rbMovieRating.setRating(movieList.get(position).getRating()/2);

        StringBuffer stringBuffer = new StringBuffer();
        for(Movie.Cast cast : movieList.get(position).getCastList()){
            stringBuffer.append(cast.getName() + ", ");
        }

        holder.tvCast.setText("Cast:" + stringBuffer);
        holder.tvStory.setText(movieList.get(position).getStory());

        return convertView;
    }

    static class MovieViewHolder{

        private ImageView ivMovieIcon;
        private TextView tvMovie;
        private TextView tvTagline;
        private TextView tvYear;
        private TextView tvDuration;
        private TextView tvDirector;
        private RatingBar rbMovieRating;
        private TextView tvCast;
        private TextView tvStory;
        
        public MovieViewHolder(View view){
            ivMovieIcon = (ImageView)view.findViewById(R.id.iv_icon);
            tvMovie = (TextView)view.findViewById(R.id.tv_movie_name);
            tvTagline = (TextView)view.findViewById(R.id.tv_Tagline);
            tvYear = (TextView)view.findViewById(R.id.tv_year);
            tvDuration = (TextView)view.findViewById(R.id.tv_duration);
            tvDirector = (TextView)view.findViewById(R.id.tv_director);
            rbMovieRating = (RatingBar)view.findViewById(R.id.rbmovie);
            tvCast = (TextView)view.findViewById(R.id.tv_cast);
            tvStory = (TextView)view.findViewById(R.id.tv_story);
        }
    }
}
