package com.example.androidproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Movies> listMovies;

    public MyAdapter(ArrayList<Movies> movies){
        this.listMovies = movies;
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movies movie = listMovies.get(position);
        holder.display(movie);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView year;
        private final TextView type;
        private final ImageView poster;

        private Pair<String, String> currentArray;

        public MyViewHolder(final View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            year = (TextView) itemView.findViewById(R.id.year);
            poster = (ImageView) itemView.findViewById(R.id.poster);
            type = (TextView) itemView.findViewById(R.id.type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentArray.first)
                            .setMessage(currentArray.second)
                            .show();
                }
            });
        }

        public void display(Movies movie) {
            //currentMovie = pair;
            title.setText("Title : " + movie.getTitle());
            year.setText("Year : " + movie.getYear());
            type.setText("Type : " + movie.getType());
            Picasso.with(poster.getContext()).load(movie.getUrlPoster()).centerCrop().fit().into(poster);
        }
    }

}
