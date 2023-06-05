package com.cs2340group7.games;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.cs2340group7.games.databinding.SelectGameBinding;

public class SelectGameScreen extends Fragment {
    private SelectGameBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = SelectGameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOnClickGameButton(binding.blackjackButton, "blackjack");
        setOnClickGameButton(binding.wordleButton, "wordle");
        setOnClickGameButton(binding.ticTacToeButton, "ticTacToe");
    }

    private void setOnClickGameButton(ImageButton button, String name) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("gameName", name);
                NavHostFragment.findNavController(SelectGameScreen.this)
                        .navigate(R.id.action_GameSelect_to_GamePlay, bundle);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}