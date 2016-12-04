package com.example.rennan.listajogos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rennan.listajogos.model.Jogo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rennan on 30/10/2016.
 */

public class JogoAdapter extends ArrayAdapter<Jogo> {

    public JogoAdapter(Context context, List<Jogo> jogos) {
        super(context, 0, jogos);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Jogo jogo = getItem(i);
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.jogo_adapter, viewGroup, false);
            holder = new ViewHolder();
            holder.ImgThumb = (ImageView) view.findViewById(R.id.imgThumb);
            holder.txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            holder.txtPublisher = (TextView) view.findViewById(R.id.txtPublisher);
            holder.rtBarScore = (RatingBar)view.findViewById(R.id.rbarScore);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (jogo.thumb.isEmpty()) {
            holder.ImgThumb.setImageResource(R.mipmap.ic_launcher);
        } else {
            Picasso.with(getContext())
                    .load(jogo.thumb)
                    .into(holder.ImgThumb);
        }
        holder.txtTitle.setText(jogo.title);
        holder.txtPublisher.setText(jogo.publisher);
        holder.rtBarScore.setRating(Float.parseFloat(jogo.score.equals("") ? "0" : jogo.score));

        return view;

    }

    static class ViewHolder {
        ImageView ImgThumb;
        TextView txtTitle;
        TextView txtPublisher;
        RatingBar rtBarScore;
    }


}
