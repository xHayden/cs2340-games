package com.cs2340group7.games;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.cs2340group7.games.databinding.WordleBinding;

public class Wordle extends Fragment {
    private WordleBinding binding;
    private LinearLayout keyboardContainer;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = WordleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView profileImage = view.findViewById(R.id.player_profile);
        TextView playerName = view.findViewById(R.id.player_name);

        SelectedSpriteViewModel viewModel = new ViewModelProvider(requireActivity()).get(SelectedSpriteViewModel.class);
        int selectedSpriteResId = viewModel.getSelectedSpriteResId();
        if (selectedSpriteResId != -1) {
            playerName.setText(getArguments().get("playerName").toString());
            profileImage.setImageResource(selectedSpriteResId);
        }

        WordleController wc = WordleController.getInstance();
        ProgressBar healthBar = view.findViewById(R.id.healthBar);
//        RecyclerView tiles = view.findViewById(R.id.wordleTiles);
        LinearLayout tiles = view.findViewById(R.id.wordleTiles);
        TextView scoreboard = view.findViewById(R.id.score);
        LinearLayout keyboardContainer = view.findViewById(R.id.keyboardContainer);
        WordleKeyboard wordleKeyboard = new WordleKeyboard(getContext(), keyboardContainer);
        wc.setHealthBar(healthBar);
        wc.setTiles(tiles);
        wc.setScoreboard(scoreboard);
        wc.setKeyboard(wordleKeyboard);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}