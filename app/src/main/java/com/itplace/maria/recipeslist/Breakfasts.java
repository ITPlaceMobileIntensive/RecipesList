package com.itplace.maria.recipeslist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.itplace.maria.recipeslist.RecipeType.Breakfast;

// Называть нужно BreakfastsFragment
public class Breakfasts extends Fragment {

    private static final String ARG_PAGE_BREAKFASTS = "page_breakfasts";

    RecyclerView recycler;  // уровень доступа private
    RecipesAdapter adapter;// уровень доступа private

    public static Breakfasts newInstance(int page) {
        Breakfasts fragment = new Breakfasts();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_BREAKFASTS, page); // а page вообще нужен?
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // метод можно удалить
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.breakfasts_fragment, container, false);
        recycler = (RecyclerView) v.findViewById(R.id.recycler);
        configureRecyclerView();
        return v;
    }

    private void configureRecyclerView() {
        adapter = new RecipesAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter.addItem(new Recipe(Breakfast, "Кофе", "кофе, печенька", R.drawable.pic ));  // у картинок расширение должно быть с малельнкой букмы
        adapter.addItem(new Recipe(Breakfast, "Омлет", "яйца, молоко, соль", R.drawable.omlet ));
        adapter.addItem(new Recipe(Breakfast, "Салат с крабовыми палочками",
                "крабовые палочки, капуста, огурцы, майонез, соль", R.drawable.salat_krab ));
    }
}
