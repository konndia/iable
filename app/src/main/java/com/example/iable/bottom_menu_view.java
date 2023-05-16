package com.example.iable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.iable.databinding.ActivityBottomMenuViewBinding;
public class bottom_menu_view extends AppCompatActivity {

    ActivityBottomMenuViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBottomMenuViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Home());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    replaceFragment(new Home());
                    break;
                case R.id.achievements:
                    replaceFragment(new Achievements());
                    break;
                case R.id.user:
                    replaceFragment(new User());
                    break;
                case R.id.notifFrag:
                    replaceFragment(new Notifications());
                    break;
                case R.id.add:
                    replaceFragment(new Add());
                    break;
            }
            return true;
        });
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}