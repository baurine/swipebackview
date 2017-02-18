package com.baurine.swipebackviewsample;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    private static final String KEY_BG_COLOR = "KEY_BG_COLOR";
    private static final String KEY_ICON = "KEY_ICON";

    private int bgColorResId;
    private int iconResId;

    public static void launch(Context from, int colorResId, int iconResId) {
        Intent intent = new Intent(from, DetailActivity.class);
        intent.putExtra(KEY_BG_COLOR, colorResId);
        intent.putExtra(KEY_ICON, iconResId);
        from.startActivity(intent);
    }

    //////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getBundleData();
        initViews();
    }

    private void getBundleData() {
        bgColorResId = getIntent().getIntExtra(KEY_BG_COLOR, R.color.eggplant);
        iconResId = getIntent().getIntExtra(KEY_ICON, R.drawable.icon_1);
    }

    private void initViews() {
        Drawable drawable = getResources().getDrawable(bgColorResId);
        findViewById(R.id.rl_root).setBackground(drawable);
        ((ImageView) findViewById(R.id.iv_icon)).setImageResource(iconResId);
        // if you want define custom activity in and out animation,
        // then set yourself SwipeBackListener
        // ((SwipeBackView) findViewById(R.id.swipe_back_view)).setSwipeBackListener(
        //         new SwipeBackView.SwipeBackListener() {
        //             @Override
        //             public void onSwipeBack() {
        //                 finish();
        //                 overridePendingTransition(R.anim.nothing,
        //                         R.anim.out_slide_to_right);
        //             }
        //         }
        // );
    }
}
