package com.itplace.maria.recipeslist;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new RecipesFragmentPagerAdapter(getSupportFragmentManager(), getBaseContext());// Activity  наследуется от context, поэтому не нужно вызывать getBaseContext
        // getBaseContext не стоит использовать бех явной необхоимости
        viewPager.setAdapter(pagerAdapter);

        // закоментированный код зло - удали
        /*viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    //updateT((TextView) findViewById(R.id.text));
                }
                if (position == 1) {
                    //updateD((TextView) findViewById(R.id.text));
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });**/
    }
}
