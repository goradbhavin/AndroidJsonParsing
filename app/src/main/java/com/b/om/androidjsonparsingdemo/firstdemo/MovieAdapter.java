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

        Movie movie = movieList.get(position);

        Glide.with(mContext).load(movie.getImage()).into(holder.ivMovieIcon);
        holder.tvMovie.setText(movie.getMovie());
        holder.tvTagline.setText(movie.getTagline());
        holder.tvYear.setText("Year: " + movie.getYear());
        holder.tvDuration.setText("Duration:" + movie.getDuration());
        holder.tvDirector.setText("Director:" + movie.getDirector());

        // rating bar
        holder.rbMovieRating.setRating(movie.getRating()/2);

        StringBuffer stringBuffer = new StringBuffer();
        for(Movie.Cast cast : movie.getCastList()){
            stringBuffer.append(cast.getName() + ", ");
        }

        holder.tvCast.setText("Cast:" + stringBuffer);
        holder.tvStory.setText(movie.getStory());

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
