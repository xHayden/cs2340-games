package com.cs2340group7.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cs2340group7.games.databinding.TictactoeBinding;

public class Tictactoe extends Fragment {
    private @NonNull TictactoeBinding binding;
    private TextView gameTime;
    private TictactoeManager tm;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = TictactoeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView profileImage = view.findViewById(R.id.player_profile);
        TextView playerName = view.findViewById(R.id.player_name);
        gameTime = view.findViewById(R.id.gameTime);
        TextView text = view.findViewById(R.id.tictactoeText);
        TextView aiScore = view.findViewById(R.id.aiScore);
        TextView playerScore = view.findViewById(R.id.playerScore);
        Button playAgainButton = view.findViewById(R.id.playAgainButton);

        // Load the animation from the XML resource
        Animation fadeInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);

        // Apply the animation to the ImageView and TextView
        profileImage.startAnimation(fadeInAnimation);
        playerName.startAnimation(fadeInAnimation);
        playerScore.startAnimation(fadeInAnimation);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TictactoeManager.restart();
                playAgainButton.setVisibility(View.GONE);
            }
        });

        SelectedSpriteViewModel viewModel = new ViewModelProvider(requireActivity()).get(SelectedSpriteViewModel.class);
        int selectedSpriteResId = viewModel.getSelectedSpriteResId();
        if (selectedSpriteResId != -1) {
            playerName.setText(getArguments().get("playerName").toString());
            profileImage.setImageResource(selectedSpriteResId);
        }
        tm = new TictactoeManager(gameTime, text, playerScore, aiScore, playAgainButton);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
