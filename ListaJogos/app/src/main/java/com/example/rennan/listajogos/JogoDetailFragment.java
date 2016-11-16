package com.example.rennan.listajogos;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rennan.listajogos.model.Jogo;
import com.squareup.picasso.Picasso;


public class JogoDetailFragment extends Fragment {

    Jogo mJogo;
    TextView mDescription;
    TextView mPublisher;
    RatingBar mScore;
    ImageView mThumbDetail;

    public static JogoDetailFragment newInstance(Jogo jogo){
        Bundle bundle = new Bundle();
        bundle.putSerializable("jogo",jogo);
        JogoDetailFragment jdf = new JogoDetailFragment();
        jdf.setArguments(bundle);
        return jdf;
    }

    public JogoDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_jogo_detail, container, false);

        if (getResources().getBoolean(R.bool.phone)) {
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Adicionar aos favoritos
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


        mJogo = (Jogo)getArguments().getSerializable("jogo");

        if(getResources().getBoolean(R.bool.phone)){
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout)view
                    .findViewById(R.id.toolbar_layout);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(mJogo.title);
        }

        mPublisher = (TextView)view.findViewById(R.id.txtPublisherDetail);
        mDescription = (TextView)view.findViewById(R.id.txtDescriptionDetail);
        mScore = (RatingBar)view.findViewById(R.id.DetailratingBar);
        mThumbDetail = (ImageView)view.findViewById(R.id.thumb_detail);



        String auxScore = mJogo.score.equals("") ? "0" : mJogo.score;
        mPublisher.setText(mJogo.publisher);
        mDescription.setText(mJogo.short_description);
        mScore.setRating(Float.parseFloat(auxScore));
        if (mJogo.thumb.isEmpty()) {
            mThumbDetail.setImageResource(R.mipmap.ic_launcher);
        } else {
            Picasso.with(getActivity())
                    .load(mJogo.thumb)
                    .into(mThumbDetail);
        }


        return view;
    }

}
