package com.example.androidproject;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Movies> listMovies;
    private final MainActivity activity;

    public MyAdapter(ArrayList<Movies> movies, MainActivity mainActivity){
        this.listMovies = movies;
        this.activity = mainActivity;
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final int posi = position;
        Movies movie = listMovies.get(position);
        holder.display(movie, holder.getAdapterPosition());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesManager preferencesManager = new PreferencesManager(activity);
                preferencesManager.addSharedPreferences(listMovies.get(position));
                Intent intent = new Intent(view.getContext(), MovieActivity.class);
                intent.putExtra("targetMovie", listMovies.get(position).getTitle());
                view.getContext().startActivity(intent);
            }
        });

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView year;
        private final TextView type;
        private final ImageView poster;
        private int position;

        public MyViewHolder(final View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            year = (TextView) itemView.findViewById(R.id.year);
            poster = (ImageView) itemView.findViewById(R.id.poster);
            type = (TextView) itemView.findViewById(R.id.type);
        }

        public void display(Movies movie, int position) {
            title.setText("Title : " + movie.getTitle());
            year.setText("Year : " + movie.getYear());
            type.setText("Type : " + movie.getType());
            this.position = position;
            if(!movie.getUrlPoster().equals("N/A"))Picasso.with(poster.getContext()).load(movie.getUrlPoster()).centerCrop().fit().into(poster);
            else Picasso.with(poster.getContext()).load("http://staging1.lebanesefeast.com.au/wp-content/uploads/2018/10/picture-not-available.jpg").centerCrop().fit().into(poster);
        }
    }

}
