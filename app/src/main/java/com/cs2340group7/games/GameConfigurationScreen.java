package com.cs2340group7.games;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.cs2340group7.games.databinding.GameConfigurationBinding;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class GameConfigurationScreen extends Fragment {
    private GameConfigurationBinding binding;
    private TextInputEditText playerName;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = GameConfigurationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        SelectedSpriteViewModel viewModel = new ViewModelProvider(this).get(SelectedSpriteViewModel.class);

        // Replace these with your actual drawable resources
        List<Integer> spriteList = Arrays.asList(
                R.drawable.yellowjacket,
                R.drawable.blobfish,
                R.drawable.duck,
                R.drawable.rabbit
        );

        SpriteAdapter adapter = new SpriteAdapter(spriteList, new SpriteAdapter.OnSpriteClickListener() {
            @Override
            public void onSpriteClick(int spriteResId) {
                viewModel.setSelectedSpriteResId(spriteResId);
                Log.d("SELECTED SPRITE", Integer.toString(viewModel.getSelectedSpriteResId()));
            }
        });
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                playerName = view.findViewById(R.id.nameField);
                Bundle bundle = new Bundle();
                bundle.putString("playerName", playerName.getText().toString());
                NavHostFragment.findNavController(GameConfigurationScreen.this)
                        .navigate(R.id.action_toBlackjack);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}