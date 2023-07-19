package com.cs2340group7.games;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.cs2340group7.games.databinding.WordleBinding;

public class Wordle extends Fragment {
    private WordleBinding binding;
    private LinearLayout keyboardContainer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = WordleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView profileImage = view.findViewById(R.id.player_profile);
        TextView playerName = view.findViewById(R.id.player_name);
        // Load the animation from the XML resource
        Animation fadeInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);

        // Apply the animation to the ImageView and TextView
        profileImage.startAnimation(fadeInAnimation);
        playerName.startAnimation(fadeInAnimation);
        binding.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Wordle.this)
                        .navigate(R.id.action_Wordle_to_SelectGame, savedInstanceState);
            }
        });
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
        LinearLayout healthBar = view.findViewById(R.id.healthbar);
        LinearLayout tiles = view.findViewById(R.id.wordleTiles);
        TextView scoreboard = view.findViewById(R.id.score);
        LinearLayout keyboardContainer = view.findViewById(R.id.keyboardContainer);
        WordleKeyboard wordleKeyboard = new WordleKeyboard(getContext(), keyboardContainer);
        wc.setHealthBar(healthBar, new WordleHealthBar(healthBar));
        wc.setTiles(tiles, new WordleTiles(tiles));
        wc.setScoreboard(scoreboard, new WordleScoreboard(scoreboard, view.findViewById(R.id.scorePlayAgain)));
        wc.setKeyboard(wordleKeyboard);
        wc.setPlayAgainButton(view.findViewById(R.id.playAgainButton));
        wc.setPlayAgain(view.findViewById(R.id.playAgain));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}