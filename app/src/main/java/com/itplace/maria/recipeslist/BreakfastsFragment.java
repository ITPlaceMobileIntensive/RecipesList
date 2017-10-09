package com.itplace.maria.recipeslist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.itplace.maria.recipeslist.RecipeType.*;
import static com.itplace.maria.recipeslist.RecipeType.Breakfast;

// BreakfastsFragment - так как это фрагмент используется для  Breakfast и Dinner, то название должно более универсальное
public class BreakfastsFragment extends Fragment {

    private static final String ARG_TYPE_PAGE = "type_page"; // не используемая переменная

    private RecyclerView recycler;
    private RecipesAdapter adapter;

    public static BreakfastsFragment newInstance(RecipeType type) {
        BreakfastsFragment fragment = new BreakfastsFragment();
        Bundle args = new Bundle();
        args.putSerializable("ARG_TYPE_PAGE", type); // нужно использовать константу ARG_TYPE_PAGE
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.breakfasts_fragment, container, false);
        recycler = v.findViewById(R.id.recycler);
        configureRecyclerView();
        return v;
    }

    private void configureRecyclerView() {
        adapter = new RecipesAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        Bundle args = getArguments();
        if (args != null) {
            RecipeType type = (RecipeType) args.getSerializable("ARG_TYPE_PAGE"); // нужно использовать константу ARG_TYPE_PAGE
            switch (type) {
                case Breakfast:
                    adapter.addItem(new Recipe(Breakfast, "Кофе", "кофе, печенька", R.drawable.pic));
                    adapter.addItem(new Recipe(Breakfast, "Омлет", "яйца, молоко, соль", R.drawable.omlet));
                    adapter.addItem(new Recipe(Breakfast, "Салат с крабовыми палочками",
                            "крабовые палочки, капуста, огурцы, майонез, соль", R.drawable.salat_krab));
                    break;

                case Dinner:
                    adapter.addItem(new Recipe(Dinner, "Вкусный ужин", "продукты", R.drawable.pic));
                    break;
            }
        }
    }
}
