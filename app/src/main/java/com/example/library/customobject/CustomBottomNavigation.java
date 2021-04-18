package com.example.library.customobject;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomBottomNavigation extends BottomNavigationView {

    private Typeface typeface;

    public CustomBottomNavigation(@NonNull Context context) {
        super(context);
    }

    public CustomBottomNavigation(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomBottomNavigation(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewGroup bottomNavigationViewGroup = (ViewGroup) getChildAt(0);
        BottomNavigationItemView navigationView;

        if (typeface == null) {
            typeface = Typeface.createFromAsset(getContext().getAssets(), "iran_sans_mobile_fa.ttf");
        }

        for (int i = 0; i < bottomNavigationViewGroup.getChildCount(); i++) {
            navigationView = (BottomNavigationItemView) bottomNavigationViewGroup.getChildAt(i);

            ViewGroup itemTitleViewGroup = (ViewGroup) navigationView.getChildAt(1);

            View title = itemTitleViewGroup.getChildAt(1);

            ((TextView) title).setTypeface(typeface);

        }

    }

}
