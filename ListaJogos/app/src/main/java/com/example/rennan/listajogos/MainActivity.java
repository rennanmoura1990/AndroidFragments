package com.example.rennan.listajogos;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.example.rennan.listajogos.model.Jogo;

public class MainActivity extends AppCompatActivity implements OnJogoClick {

    Toolbar toolbar;
    JogoListFragment jogoListFragment;
    JogosFavoritosFragment jogosFavoritosFragment;
    ViewPager mViewPager;
    SelectorPageAdapter selectorPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //por causa da AppCompatActivity


        buildViewPager();

    }

    private void buildViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.container_jogoslist);
        selectorPageAdapter = new SelectorPageAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(selectorPageAdapter);

        mViewPager.setPageTransformer(true,new AccordionTransformer());

        TabLayout tab = (TabLayout) findViewById(R.id.table);

        tab.setupWithViewPager(mViewPager);

        tab.getTabAt(0).setIcon(R.drawable.ic_joystick_icon);
        tab.getTabAt(1).setIcon(R.drawable.ic_star_favorited);

    }

    @Override
    public void onJogoClick(Jogo jogo) {
        if (getResources().getBoolean(R.bool.phone)) {
            Intent it = new Intent(this, JogoDetailActivity.class);
            it.putExtra("jogo", jogo);
            startActivity(it);
        } else {
            JogoDetailFragment jdf = JogoDetailFragment.newInstance(jogo);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_jogodetail, jdf, "detail")
                    .commit();
        }
    }

    public class SelectorPageAdapter extends FragmentPagerAdapter {

        public SelectorPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (jogoListFragment == null) {
                        jogoListFragment = new JogoListFragment();
                    }
                    return jogoListFragment;
                case 1:
                default:
                    if (jogosFavoritosFragment == null) {
                        jogosFavoritosFragment = new JogosFavoritosFragment();
                    }
                    return jogosFavoritosFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.list);
                case 1:
                default:
                    return getResources().getString(R.string.favorites);
            }
        }

    }
}


