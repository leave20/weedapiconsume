package com.example.weedapiconsume.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weedapiconsume.R;
import com.example.weedapiconsume.models.Films;

import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.ViewHolder>{

    Context context;
    List<Films> filmsList;

    public FilmsAdapter(Context context, List<Films>filmsList){
        this.context=context;
        this.filmsList=filmsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.film, parent,false);
        ViewHolder viewHolder;
        viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(filmsList.get(position).getTitle());
        holder.episode_id.setText(String.valueOf(filmsList.get(position).getEpisode_id()));
        holder.opening.setText(filmsList.get(position).getOpening_crawl());
        holder.director.setText(filmsList.get(position).getDirector());
        holder.producer.setText(filmsList.get(position).getProducer());
        holder.url.setText(filmsList.get(position).getUrl());
        holder.created.setText(filmsList.get(position).getCreated());
        holder.edited.setText(filmsList.get(position).getEdited());


    }

    @Override
    public int getItemCount() {
        return filmsList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,episode_id,opening, director, producer,url,created,edited;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title= itemView.findViewById(R.id.title);
            episode_id=itemView.findViewById(R.id.episode_id);
            opening=itemView.findViewById(R.id.opening_crawl);
            director=itemView.findViewById(R.id.director);
            producer=itemView.findViewById(R.id.producer);
            url=itemView.findViewById(R.id.Url);
            created=itemView.findViewById(R.id.created);
            edited=itemView.findViewById(R.id.edited);

        }
    }
}
