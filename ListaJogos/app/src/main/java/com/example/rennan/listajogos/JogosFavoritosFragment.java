package com.example.rennan.listajogos;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rennan.listajogos.model.Jogo;
import com.example.rennan.listajogos.sqlite.DAOJogos;
import com.example.rennan.listajogos.sqlite.FavoritoEvent;
import com.example.rennan.listajogos.sqlite.RNJogos;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class JogosFavoritosFragment extends Fragment {

    ListView mListJogos;
    List<Jogo> mJogos;
    RNJogos rnJogos = new RNJogos();

    public JogosFavoritosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_jogosfavoritos, container, false);

        mListJogos = (ListView) view.findViewById(R.id.list_jogosfavoritos);

        updateList();

        mListJogos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (getActivity() instanceof OnJogoClick) {
                    Jogo jogo = (Jogo) mListJogos.getItemAtPosition(i);
                    ((OnJogoClick) getActivity()).onJogoClick(jogo);
                }
            }
        });

        return view;
    }

    //Quando crio a Activity eu Registro o Evento
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    //Quando eu destruo a Activity eu Desregistro o Evento
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void updateList(){
        mJogos = rnJogos.listaJogosFavoritos(getContext());
        mListJogos.setAdapter(new JogoAdapter(getContext(), mJogos));
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FavoritoEvent event){

        updateList();

    }

}
