package com.julen_demiguel.whatsapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.julen_demiguel.whatsapp.fragments.ChatsFragment;
import com.julen_demiguel.whatsapp.fragments.ComunityFragmnet;
import com.julen_demiguel.whatsapp.fragments.EstadosFragment;
import com.julen_demiguel.whatsapp.fragments.LlamadasFragment;

import java.util.ArrayList;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragmentArray;
    private int numberOfTabs;

    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numberOfTabs = behavior;
        fragmentArray = new ArrayList<>();
        fragmentArray.add(new ComunityFragmnet());
        fragmentArray.add(new ChatsFragment());
        fragmentArray.add(new EstadosFragment());
        fragmentArray.add(new LlamadasFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArray.get(position);
    }

    @Override
    public int getCount() {
        return this.numberOfTabs;
    }
}
