package com.example.rennan.listajogos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rennan.listajogos.model.Jogo;
import com.squareup.picasso.Picasso;

import static android.R.attr.data;

public class JogoDetailActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);


        Jogo jogo = (Jogo) getIntent().getParcelableExtra("jogo");
        JogoDetailFragment jdf = JogoDetailFragment.newInstance(jogo);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_jogo_detail,jdf,"detail")
                .commit();
    }

}
