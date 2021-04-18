package com.example.library.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.library.R;
import com.example.library.customobject.CustomBottomNavigation;

public class MainActivity extends AppCompatActivity {

    private CustomBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigation, navController);
    }

    private void findViews() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }
}