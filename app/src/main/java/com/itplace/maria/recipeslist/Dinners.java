package com.itplace.maria.recipeslist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Вместо фрагмента Dinners можно заиспользовать Breakfasts, т.к. функционал будем одинаковый делать.
// передавать при этом можно тип RecipeType через  args.putSerializable("TYPE", RecipeType.Breakfast); в методе newInstance

// получать  RecipeType внутри фрагнмет (методы onCreateView/onCreate)  можно так:
// Bundle args = getArguments();
//      if (args != null) {
//                RecipeType type= (RecipeType) args.getSerializable("TYPE");
//                }
public class Dinners extends Fragment {

    private static final String ARG_PAGE_DINNERS = "page_dinners";

    public static Dinners newInstance(int page) {
        Dinners fragment = new Dinners();
        Bundle args = new Bundle();

        args.putInt(ARG_PAGE_DINNERS, page); // а page вообще нужен?
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dinners_fragment, container, false);
    }
}

