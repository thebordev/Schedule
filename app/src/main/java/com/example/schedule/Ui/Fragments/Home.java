package com.example.schedule.Ui.Fragments;

import android.os.Bundle;
import android.util.Log;
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
import com.example.schedule.databinding.HomeBinding;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Home extends Fragment {
    private static final String TAG = "Home";
    private HomeBinding binding;
    private GroupViewModel viewModel;
    private GroupAdapter adapter;
    private ArrayList<Group> groupList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(GroupViewModel.class);

        initRecyclerView();
        observeData();
        setUpItemTouchHelper();
        viewModel.getGroups();
    }

    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPokemonPosition = viewHolder.getAdapterPosition();
                Group group = adapter.getGroupAt(swipedPokemonPosition);
                viewModel.insertGroup(group);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(),"Pokemon added to favorites.",Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.pokemonRecyclerView);
    }


    private void observeData() {
        viewModel.getGroupList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Group>>() {
            @Override
            public void onChanged(ArrayList<Group> pokemons) {
                Log.e(TAG, "onChanged: " + pokemons.size() );
                adapter.updateList(pokemons);
            }
        });
    }

    private void initRecyclerView() {
        binding.pokemonRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GroupAdapter(getContext(),groupList);
        binding.pokemonRecyclerView.setAdapter(adapter);
    }
}