package com.julen_demiguel.whatsapp.activities;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.julen_demiguel.whatsapp.adapters.MyViewPagerAdapter;
import com.julen_demiguel.whatsapp.R;
import com.julen_demiguel.whatsapp.fragments.ChatsFragment;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    ViewPager viewPager;
    MyViewPagerAdapter myViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Prueba de cambio (Álvaro)
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.comunidad));
        tabLayout.addTab(tabLayout.newTab().setText("Chats"));
        tabLayout.addTab(tabLayout.newTab().setText("Estados"));
        tabLayout.addTab(tabLayout.newTab().setText("Llamadas"));
        LinearLayout layout = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(0));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.weight = 0.5f; // e.g. 0.5f
        layout.setLayoutParams(layoutParams);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Whatsapp");
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position){
                    case 0:
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.community_menu);
                        break;
                    case  1:
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.chats_menu);
                        break;
                    case 2:
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.estados_menu);
                        break;
                    case 3:
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.llamadas_menu);
                        break;
                }

                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        tabLayout.selectTab(tabLayout.getTabAt(0));
        ChatsFragment chatFragment = (ChatsFragment) myViewPagerAdapter.getItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chats_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_nuevoGrupo:
                return true;
            case R.id.action_nuevaDifusion:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}