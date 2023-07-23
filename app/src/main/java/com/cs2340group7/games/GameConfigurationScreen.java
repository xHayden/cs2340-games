package com.cs2340group7.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.cs2340group7.games.databinding.GameConfigurationBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameConfigurationScreen extends Fragment {
    private GameConfigurationBinding binding;
    private TextInputEditText playerName;
    private String playerNameTest;
    private int gameLogoResId;
    private String gameName;
    private Switch[] aiMode;

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
        Bundle bundle = new Bundle();
        aiMode = new Switch[]{view.findViewById(R.id.aiMode)};
        if (getArguments().getString("gameName") == "ticTacToe") {
            aiMode[0].setVisibility(View.VISIBLE);
        } else {
            aiMode[0].setVisibility(View.GONE);
        }
        Spinner s = (Spinner) view.findViewById(R.id.aiDifficulty);
        List<String> arraySpinner = new ArrayList<>();
        arraySpinner.add("Easy");
        arraySpinner.add("Medium");
        arraySpinner.add("Hard");
        s.setVisibility(View.GONE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        final Boolean[] aiModee = { false };
        aiMode[0].setChecked(false);
        aiMode[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clicked) {
                if (aiModee[0]) {
                    aiModee[0] = false;
                    s.setVisibility(View.GONE);
                    bundle.putString("aiDifficulty", "None");
                } else {
                    aiModee[0] = true;
                    s.setVisibility(View.VISIBLE);
                }
                bundle.putBoolean("aiMode", aiModee[0]);
            }
        });
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String difficulty = parent.getItemAtPosition(position).toString();
                bundle.putString("aiDifficulty", difficulty);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bundle.putString("aiDifficulty", "Easy");
            }
        });

        binding.startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                playerName = view.findViewById(R.id.nameField);
                if (playerName == null || playerName.getText() == null || playerName.getText().toString().trim().equals("")) {
                    return;
                }
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

    @Override
    public void onResume() {
        super.onResume();
        if (aiMode != null) {
            aiMode[0].setChecked(false);
        }
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