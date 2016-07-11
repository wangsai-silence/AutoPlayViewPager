package com.silence.autoplayviewpagerdmeo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.silence.autoplayviewpager.AutoPlayPagerAdapter;
import com.silence.autoplayviewpager.AutoPlayViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AutoPlayViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (AutoPlayViewPager) findViewById(R.id.pager);

        List<Integer> resList = new ArrayList<>();
        resList.add(R.drawable.pic_1);
        resList.add(R.drawable.pic_2);
        resList.add(R.drawable.pic_3);
        resList.add(R.drawable.pic_4);

        AutoPlayPagerAdapter<ImageView, Integer> adapter = new AutoPlayPagerAdapter<ImageView, Integer>(resList) {
            @Override
            protected ImageView createNewItem() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            protected void loadData(ImageView view, Integer data) {
                view.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                view.getLayoutParams().height = (int) (getScreenHeight() * 0.4f);
                view.setImageResource(data);
            }
        };

        pager.setAdapter(adapter);
    }

    public int getScreenHeight() {
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}
