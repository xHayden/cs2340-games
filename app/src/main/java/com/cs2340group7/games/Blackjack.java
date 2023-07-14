package com.cs2340group7.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cs2340group7.games.databinding.BlackjackBinding;

public class Blackjack extends Fragment {
    private BlackjackBinding binding;
    private LinearLayout playerHandLayout;
    private LinearLayout dealerHandLayout;
    private TextView playerScoreTextView;
    private TextView dealerScoreTextView;
    private ImageView deckImageView;
    private Button hitButton;
    private Button standButton;
    // Game Status UI Component
    private TextView gameStatusTextView;

    // Game Control UI Component
    private Button newGameButton;
    private Button quitGameButton;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = BlackjackBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView profileImage = view.findViewById(R.id.player_profile);
        TextView playerName = view.findViewById(R.id.player_name);
        playerHandLayout = view.findViewById(R.id.player_cards);
        playerScoreTextView = view.findViewById(R.id.player_number);
        dealerHandLayout = view.findViewById(R.id.aI_cards);
        dealerScoreTextView = view.findViewById(R.id.ai_number);
//        deckImageView = view.findViewById(R.id.deck_image_view);

        // Initialize the Action UI Components
        hitButton = view.findViewById(R.id.hit);
        standButton = view.findViewById(R.id.stand);
        // Initialize the Game Status UI Component
//        gameStatusTextView = view.findViewById(R.id.);
//        newGameButton = view.findViewById(R.id.); need to put the new game button for blackjack
//        quitGameButton = view.findViewById(R.id.quit_game_button); i don't think we have this one but we can make one


        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BlackjackController.getInstance().playerHit(); have not made the method yet cause we don't know where it would be at
            }
        });
        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BlackjackController.getInstance().playerStand(); have not made the method yet cause we don't know where it would be at
            }
        });
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BlackjackController.getInstance().startNewGame(); have not made the method yet cause we don't know where it would be at
            }
        });

        quitGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BlackjackController.getInstance().quitGame(); have not made the method yet cause we don't know where it would be at
            }
        });


        SelectedSpriteViewModel viewModel = new ViewModelProvider(requireActivity()).get(SelectedSpriteViewModel.class);
        int selectedSpriteResId = viewModel.getSelectedSpriteResId();
        if (selectedSpriteResId != -1) {
            playerName.setText(getArguments().get("playerName").toString());
            profileImage.setImageResource(selectedSpriteResId);
        }


    }
    public void updatePlayerScore(int score) {
        playerScoreTextView.setText("Score: " + score);
    }

    public void updateDealerScore(int score) {
        dealerScoreTextView.setText("Score: " + score);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}