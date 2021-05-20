package com.example.schedule.Ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedule.Adapters.GroupAdapter;
import com.example.schedule.Model.Group;
import com.example.schedule.ViewModel.GroupViewModel;
import com.example.schedule.databinding.FavoritesBinding;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Favorites extends Fragment {
    private FavoritesBinding binding;
    private GroupViewModel viewModel;
    private GroupAdapter adapter;
    private ArrayList<Group> groupList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FavoritesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(GroupViewModel.class);

        initRecyclerView();
        setUpItemTouchHelper();
        observeData();
        //viewModel.getFavoritePokemon();
    }

    private void observeData() {
        viewModel.getFavoriteGroupList().observe(getViewLifecycleOwner(), new Observer<List<Group>>() {
            @Override
            public void onChanged(List<Group> groups) {

                if(groups == null || groups.size() == 0)
                    binding.noFavoritesText.setVisibility(View.VISIBLE);
                else{
                    ArrayList<Group> list = new ArrayList<>();
                    list.addAll(groups);
                    adapter.updateList(list);
                }
            }
        });
    }

    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPokemonPosition = viewHolder.getAdapterPosition();
                Group group = adapter.getGroupAt(swipedPokemonPosition);
                viewModel.deleteGroup(group.getName());
                Toast.makeText(getContext(),"Pokemon removed from favorites.",Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.favoritesRecyclerView);
    }


    private void initRecyclerView() {
        binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GroupAdapter(getContext(),groupList);
        binding.favoritesRecyclerView.setAdapter(adapter);
    }

}