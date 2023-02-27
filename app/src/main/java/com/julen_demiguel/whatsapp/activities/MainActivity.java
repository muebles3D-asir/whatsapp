package com.julen_demiguel.whatsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.julen_demiguel.whatsapp.R;

import com.julen_demiguel.whatsapp.Models.Chat;
import com.julen_demiguel.whatsapp.Models.User;
import com.julen_demiguel.whatsapp.adapters.ChatRecyclerDataAdapter;
import com.julen_demiguel.whatsapp.adapters.ContactRecyclerDataAdapter;
import com.julen_demiguel.whatsapp.adapters.MyViewPagerAdapter;
import com.julen_demiguel.whatsapp.fragments.ChatsFragment;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements ChatsFragment.ChatListener {

    TabLayout tabLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    ViewPager viewPager;
    Realm realm;
    MyViewPagerAdapter myViewPagerAdapter;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    List<Chat> chats = new RealmList<>();
    RealmResults<Chat> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        fab = findViewById(R.id.idbtnNewChat);


        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.comunidad));
        tabLayout.addTab(tabLayout.newTab().setText("Chats"));
        tabLayout.addTab(tabLayout.newTab().setText("Estados"));
        tabLayout.addTab(tabLayout.newTab().setText("Llamadas"));

        LinearLayout layout = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(0));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.weight = 0.5f; // e.g. 0.5f
        layout.setLayoutParams(layoutParams);

        toolbar = findViewById(R.id.Toolbar);
        toolbar.setTitle("Whatsapp");
        setSupportActionBar(toolbar);

        fab.setOnClickListener(v -> {

            Intent ToTouched = new Intent(MainActivity.this, ContactsActivity.class);
            try {
                startActivity(ToTouched);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }

        });

        viewPager = findViewById(R.id.viewPager);
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.community_menu);
                        break;
                    case 1:
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.buscar));
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.chats_menu);
                        fab.setOnClickListener(v -> {
                            Toast.makeText(MainActivity.this, "Prueba", Toast.LENGTH_SHORT).show();
                            Intent ToTouched = new Intent(MainActivity.this, ContactsActivity.class);
                            try {
                                startActivity(ToTouched);
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case 2:
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.camara));
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.estados_menu);
                        fab.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Camara", Toast.LENGTH_SHORT).show());
                        break;
                    case 3:
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.llamada_de_emergencia));
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.llamadas_menu);
                        break;
                }
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {

                }
            }

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
        switch (item.getItemId()) {
            case R.id.action_nuevoGrupo:
                return true;
            case R.id.action_nuevaDifusion:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public  void openChat(int id) {
        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
        Toast.makeText(this, id + "", Toast.LENGTH_SHORT).show();
    }

}