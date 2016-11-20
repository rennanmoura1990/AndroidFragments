package com.example.rennan.listajogos;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rennan.listajogos.model.Jogo;

public class MainActivity extends AppCompatActivity implements OnJogoClick {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onJogoClick(Jogo jogo) {
        if(getResources().getBoolean(R.bool.phone)) {
            Intent it = new Intent(this, JogoDetailActivity.class);
            it.putExtra("jogo", jogo);
            startActivity(it);
        }else{
            JogoDetailFragment jdf = JogoDetailFragment.newInstance(jogo);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_jogo_detail,jdf,"detail")
                    .commit();
        }
    }




}
