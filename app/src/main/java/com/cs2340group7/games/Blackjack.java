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
import androidx.navigation.fragment.NavHostFragment;

import com.cs2340group7.games.databinding.BlackjackBinding;

public class Blackjack extends Fragment {
    private BlackjackBinding binding;

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
        SelectedSpriteViewModel viewModel = new ViewModelProvider(requireActivity()).get(SelectedSpriteViewModel.class);
        int selectedSpriteResId = viewModel.getSelectedSpriteResId();
        if (selectedSpriteResId != -1) {
            playerName.setText(getArguments().get("playerName").toString());
            profileImage.setImageResource(selectedSpriteResId);
        }
        // Load the animation from the XML resource
        Animation fadeInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);

        // Apply the animation to the ImageView and TextView
        profileImage.startAnimation(fadeInAnimation);
        playerName.startAnimation(fadeInAnimation);
        BlackjackController.getInstance().setBlackjackContext(getContext());
        BlackjackController.getInstance().instantiateView(view);
        BlackjackController.getInstance().reset();
        Button exitButton = view.findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Blackjack.this)
                        .navigate(R.id.action_Blackjack_to_SelectGame, savedInstanceState);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}