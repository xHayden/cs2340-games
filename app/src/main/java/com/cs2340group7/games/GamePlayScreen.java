package com.cs2340group7.games;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.cs2340group7.games.databinding.GamePlayScreenBinding;

import java.util.HashMap;
import java.util.Objects;

public class GamePlayScreen extends Fragment {
    private GamePlayScreenBinding binding;
    private TextView gameName;
    private TextView description;
    private String argGameName;
    private HashMap<String, GameInfo> gameInfo;
    private static class GameInfo {
        String description = "";
        String name = "";
        String[] tags;
        int logo;

        GameInfo(String name, String description, String[] tags, int logo) {
            this.name = name;
            this.description = description;
            this.tags = tags;
            this.logo = logo;
        }

        GameInfo(String name, String description, int logo) {
            this(name, description, new String[0], logo);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = GamePlayScreenBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Resources res = getResources();
        gameName = (TextView) view.findViewById(R.id.gameName);
        description = (TextView) view.findViewById(R.id.description);
        ImageView gameLogo = (ImageView) view.findViewById(R.id.gameLogo);
        argGameName = getArguments().get("gameName").toString();

        gameInfo = new HashMap<String, GameInfo>();
        gameInfo.put("wordle", new GameInfo(
                "Wordle",
                getString(R.string.wordle_description),
                R.drawable.wordle_logo
        ));
        gameInfo.put("blackjack", new GameInfo(
                "Blackjack",
                getString(R.string.blackjack_description),
                R.drawable.blackjack_logo
        ));
        gameInfo.put("ticTacToe", new GameInfo(
                "Tic Tac Toe",
                getString(R.string.tictactoe_description),
                R.drawable.tictactoe_logo
        ));
        setGameName(gameInfo.get(argGameName).name);
        setDescription(gameInfo.get(argGameName).description);
        gameLogo.setImageResource(gameInfo.get(argGameName).logo);
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("gameName", argGameName);
                bundle.putInt("gameLogo", gameInfo.get(argGameName).logo);
                NavHostFragment.findNavController(GamePlayScreen.this)
                        .navigate(R.id.action_GamePlay_to_GameConfig, bundle);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setGameName(String text) {
        if (gameName != null) {
            gameName.setText(text);
        }
    }

    public void setDescription(String text) {
        if (description != null) {
            description.setText(text);
        }
    }

}