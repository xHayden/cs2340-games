package com.cs2340group7.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.cs2340group7.games.databinding.GameConfigurationBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.List;

public class GameConfigurationScreen extends Fragment {
    private GameConfigurationBinding binding;
    private TextInputEditText playerName;
    private String playerNameTest;
    private int gameLogoResId;
    private String gameName;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = GameConfigurationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        SelectedSpriteViewModel viewModel = new ViewModelProvider(requireActivity()).get(SelectedSpriteViewModel.class);

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
            }
        });
        recyclerView.setAdapter(adapter);

        ImageView gameLogo = view.findViewById(R.id.configGameLogo);
        gameLogo.setImageResource(getArguments().getInt("gameLogo"));
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                playerName = view.findViewById(R.id.nameField);
                if (playerName == null || playerName.getText() == null || playerName.getText().toString().trim().equals("")) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("playerName", playerName.getText().toString());
                switch (getArguments().getString("gameName")) {
                    case "ticTacToe": {
                        NavHostFragment.findNavController(GameConfigurationScreen.this)
                                .navigate(R.id.action_toTicTacToe, bundle);
                        break;
                    }
                    case "wordle": {
                        NavHostFragment.findNavController(GameConfigurationScreen.this)
                                .navigate(R.id.action_toWordle, bundle);
                        break;
                    }
                    case "blackjack": {
                        NavHostFragment.findNavController(GameConfigurationScreen.this)
                                .navigate(R.id.action_toBlackjack, bundle);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        });
    }
    // Getter for playerName
    public String getPlayerName() {
        return playerNameTest;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setPlayerName(String playerNameTest) {
        this.playerNameTest = playerNameTest;
    }
    // Setter method for game logo resource ID
    public void setGameLogoResId(int gameLogoResId) {
        this.gameLogoResId = gameLogoResId;
    }

    // Setter method for game name
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}