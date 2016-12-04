package com.example.rennan.listajogos;


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
import android.widget.Toast;

import com.example.rennan.listajogos.model.Jogo;
import com.example.rennan.listajogos.sqlite.FavoritoEvent;
import com.example.rennan.listajogos.sqlite.RNJogos;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class JogoDetailFragment extends Fragment {

    Jogo mJogo;
    TextView mDescription;
    TextView mPublisher;
    RatingBar mScore;
    ImageView mThumbDetail;
    FloatingActionButton mFab;
    CollapsingToolbarLayout appBarLayout;
    RNJogos rnJogos = new RNJogos();

    public static JogoDetailFragment newInstance(Jogo jogo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("jogo", jogo);
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

        }


        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_removeBookmarks();
            }
        });


        mJogo = getArguments().getParcelable("jogo");

        if (rnJogos.verificaJogoFavoritado(getActivity(), mJogo.title))
            changeFloatingButton(true);
        else
            changeFloatingButton(false);

        if (getResources().getBoolean(R.bool.phone)) {
            appBarLayout= (CollapsingToolbarLayout) view
                    .findViewById(R.id.toolbar_layout);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mJogo.title);
        }

        mPublisher = (TextView) view.findViewById(R.id.txtPublisherDetail);
        mDescription = (TextView) view.findViewById(R.id.txtDescriptionDetail);
        mScore = (RatingBar) view.findViewById(R.id.DetailratingBar);
        mThumbDetail = (ImageView) view.findViewById(R.id.thumb_detail);


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

    public void add_removeBookmarks() {
        if (!rnJogos.verificaJogoFavoritado(getActivity(), mJogo.title)) {
            rnJogos.inserirJogoFavorito(getActivity(), mJogo.title, mJogo.score, mJogo.publisher, mJogo.short_description, mJogo.thumb);
            changeFloatingButton(true);
            Toast.makeText(getActivity(), getResources().getString(R.string.favorited_game), Toast.LENGTH_SHORT).show();
        } else {
            rnJogos.excluirJogoFavorito(getActivity(), rnJogos.buscaIDJogo(getActivity(), mJogo.title));
            changeFloatingButton(false);
            Toast.makeText(getActivity(), getResources().getString(R.string.unfavorited_game), Toast.LENGTH_SHORT).show();

        }
        EventBus.getDefault().post(new FavoritoEvent());

    }

    public void changeFloatingButton(boolean favorite) {
        if (favorite)
            mFab.setImageResource(R.drawable.ic_star_favorited);
        else
            mFab.setImageResource(R.drawable.ic_star_notfavorited);

    }
}
