package com.itplace.maria.recipeslist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
// Не правильно в названии классов использовать подчеркивание и с маленькой буквы.
// Можно написать MainActivity/ RecipesListActivity
// Вместо FragmentActivity  наследоваться от AppCompatActivity
public class RecipesList_main extends FragmentActivity {

    static final int PAGE_COUNT = 2; // уровенить доступа private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_list_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);  // на 26 sdk  (ViewPager) можно не писать
        PagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {  // studio подчеркивает, что метод устарел и нужно использовать addOnPageChangeListener

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
        });
    }

    // MyFragmentPagerAdapter нужно в отдельный класс выносить. "My" в названии нигде не нужно использовать. Не понятно, чем "My" если в команда 2 и более человек
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:  // создать константы
                    fragment = Breakfasts.newInstance(position);
                    break;

                case 1:// создать константы
                    fragment = Dinners.newInstance(position);
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String s = "";
            switch (position) {
                case 0: // создать константы
                    s = getString(R.string.Breakfasts);
                    break;

                case 1: // создать константы
                    s = getString(R.string.Dinners);
                    break;
            }
            return s;
        }
    }

}
