package com.krupnov.sbertroyka_java.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.krupnov.sbertroyka_java.R;
import com.krupnov.sbertroyka_java.pojo.Film;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    private List<Film> films;

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        Film film = films.get(position);
        holder.textViewFilmName.setText(film.getTitle());
        holder.textViewDirectorName.setText(film.getDirector());
        holder.textViewProducerName.setText(film.getProducer());
        holder.textViewReleaseDate.setText(film.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return films.size();
    }



    class FilmViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewFilmName;
        private TextView textViewDirectorName;
        private TextView textViewProducerName;
        private TextView textViewReleaseDate;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFilmName = itemView.findViewById(R.id.textViewFilmName);
            textViewDirectorName = itemView.findViewById(R.id.textViewDirectorName);
            textViewProducerName = itemView.findViewById(R.id.textViewProducerName);
            textViewReleaseDate = itemView.findViewById(R.id.textViewReleaseDate);
        }
    }
}
