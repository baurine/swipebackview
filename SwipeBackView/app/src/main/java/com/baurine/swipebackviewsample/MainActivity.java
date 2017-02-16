package com.baurine.swipebackviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        int bgColorResId = R.color.eggplant;
        int iconResId = R.drawable.icon_1;
        switch (view.getId()) {
            case R.id.rl_icon_1:
                bgColorResId = R.color.eggplant;
                iconResId = R.drawable.icon_1;
                break;
            case R.id.rl_icon_2:
                bgColorResId = R.color.saffron;
                iconResId = R.drawable.icon_2;
                break;
            case R.id.rl_icon_3:
                bgColorResId = R.color.sienna;
                iconResId = R.drawable.icon_3;
                break;
        }
        DetailActivity.launch(this, bgColorResId, iconResId);
    }
}
