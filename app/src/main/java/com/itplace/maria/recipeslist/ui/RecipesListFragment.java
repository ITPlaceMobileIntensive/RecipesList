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
    private static final String ARG_RECIPE_ID = "recipe_id";  // ARG_RECIPE_ID относится к CardActivity. Поэтмоу в CardActivity и нужно объявлять

    private RecyclerView recycler;
    RecipesAdapter adapter; // сделать private
    private final RecipesPresenter presenter = new RecipesPresenter();

    Bundle arguments; // не нужное поле, удалить

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
        adapter.initList(); // не нужен этот метод

        return v;
    }

    private void configureRecyclerView() {
        adapter = new RecipesAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onItemClick(String RecipeId) {  //RecipeId - с маленькой буквы. http://www.oracle.com/technetwork/java/codeconventions-150003.pdf
        arguments = new Bundle();
        arguments.putString(ARG_RECIPE_ID, RecipeId);
        Intent intent = CardActivity.createStartIntent(getContext());
        intent.putExtras(arguments);// интент со всеми данными нужно созавать в функции CardActivity.createStartIntent. Не нужно здесь созвать arguments
        startActivity(intent);
    }

    @Override
    public void onRecipesReceived(List<Recipe> recipes) {
        Bundle args = getArguments();

        if (args != null) {
            RecipeType type = (RecipeType) args.getSerializable(ARG_TYPE_PAGE);

            /*if (type != null) {  // закомментируемый код удалить
                for (int i = 0; i < recipes.size(); i++) {
                    Recipe recipe = recipes.get(i);

                    if (recipe.getType() == type) {
                        adapter.addItem(recipe);
                    }
                }
            }*/
            presenter.addByListType(adapter, recipes, type); //  элементы UI нельзя передавать в презентор.
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
