package com.example.rennan.listajogos;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.rennan.listajogos.model.Jogo;

import java.util.ArrayList;
import java.util.List;


public class JogoListFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Jogo>>, SearchView.OnQueryTextListener {

    ListView mListJogos;
    LoaderManager mLoaderManager;
    List<Jogo> mJogos;

    public JogoListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_jogo_list, container, false);

        mListJogos = (ListView) view.findViewById(R.id.list_jogos);

        if (savedInstanceState != null) {
            mJogos = savedInstanceState.getParcelableArrayList("listajogos");
            if (mJogos != null)
                mListJogos.setAdapter(new JogoAdapter(getActivity(), mJogos));
        }

        mListJogos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (getActivity() instanceof OnJogoClick) {
                    Jogo jogo = (Jogo) mListJogos.getItemAtPosition(i);
                    ((OnJogoClick) getActivity()).onJogoClick(jogo);
                }
            }
        });

        mLoaderManager = getLoaderManager();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public Loader<List<Jogo>> onCreateLoader(int id, Bundle args) {
        String q = args.getString("q");
        return new JogosSearchTask(getActivity(), q);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<Jogo>> loader, List<Jogo> data) {
        if (data != null) {
            mJogos = data;
            mListJogos.setAdapter(new JogoAdapter(getActivity(), data));
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<Jogo>> loader) {

    }


    @Override
    //Clica no botão de buscar
    public boolean onQueryTextSubmit(String query) {
        //Bundle é tipo passagem de dados de uma activity para outra
        Bundle bundle = new Bundle();
        bundle.putString("q", query);
        mLoaderManager.restartLoader(0, bundle, this);
        return false;
    }

    @Override
    //Evento Text_Changed
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("listajogos", (ArrayList<? extends Parcelable>) mJogos);
    }

}