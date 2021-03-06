package com.itplace.maria.recipeslist.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itplace.maria.recipeslist.R;
import com.itplace.maria.recipeslist.presenter.RecipesPresenter;
import com.itplace.maria.recipeslist.recipedatastruct.Recipe;
import com.itplace.maria.recipeslist.recipedatastruct.RecipeType;
import com.itplace.maria.recipeslist.adapters.RecipesAdapter;
import com.itplace.maria.recipeslist.view.RecipesView;

import java.util.List;

/**
 * Здесь отображается список элементов - рецептов
 */
public class RecipesListFragment extends Fragment
        implements RecipesAdapter.OnItemClickListener, RecipesView {

    private static final String ARG_TYPE_PAGE = "type_page";

    private RecyclerView recycler;
    private RecipesAdapter adapter;
    private final RecipesPresenter presenter = new RecipesPresenter();

    public static RecipesListFragment newInstance(RecipeType type) {
        RecipesListFragment fragment = new RecipesListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TYPE_PAGE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipes_list, container, false);
        recycler = v.findViewById(R.id.recycler);
        configureRecyclerView();

        adapter.setOnItemClickListener(this);

        presenter.attachView(this);
        presenter.loadRecipes();

        return v;
    }

    private void configureRecyclerView() {
        adapter = new RecipesAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onItemClick(String recipeId) {
        Intent intent = CardActivity.createStartIntent(getContext(), recipeId);
        startActivity(intent);
    }

    @Override
    public void onRecipesReceived(List<Recipe> recipes) {
        // TODO фильтрация должна происходить в рамках получения данныъ loadRecipes. В rx есть метод map.
        Bundle args = getArguments();

        if (args != null) {
            RecipeType type = (RecipeType) args.getSerializable(ARG_TYPE_PAGE);

            if (type != null) {
                for (int i = 0; i < recipes.size(); i++) {
                    Recipe recipe = recipes.get(i);

                    if (recipe.getType() == type) {
                        adapter.addItem(recipe);
                    }
                }
            }
        }
    }

    @Override
    public void showError(Throwable throwable) {
        Toast toast = Toast.makeText(this.getContext(), throwable.getMessage(), Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }
}
