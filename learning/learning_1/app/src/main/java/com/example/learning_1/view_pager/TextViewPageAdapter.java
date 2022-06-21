package com.example.learning_1.view_pager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.learning_1.R;

public class TextViewPageAdapter extends PagerAdapter {
    public static final String TAG = "TextViewPageAdapter";
    public static final int MAXIMUM_PAGE = 10;
    private Context mAppContext;

    TextViewPageAdapter(Context context) {
        mAppContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d(TAG, "instantiateItem");
        LayoutInflater inflater =
                (LayoutInflater) mAppContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_viewpagertest, container, false);
        ((TextView)view.findViewById(R.id.tv_page1)).setText("tab Page " + position);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // container => viewPager
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        // set maximum page count
        Log.d(TAG, "getCount");
        return MAXIMUM_PAGE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        Log.d(TAG, "isViewFromObject");
        return (view == (View)object);
    }
}
