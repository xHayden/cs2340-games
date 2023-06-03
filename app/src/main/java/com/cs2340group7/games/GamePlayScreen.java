package com.cs2340group7.games;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.cs2340group7.games.databinding.GamePlayScreenBinding;

public class GamePlayScreen extends Fragment {
    private GamePlayScreenBinding binding;
    private TextView gameName;
    private TextView description;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = GamePlayScreenBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        gameName = (TextView) view.findViewById(R.id.gameName);
        description = (TextView) view.findViewById(R.id.description);
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                setGameName("Test");
//                setDescription("test2");
                NavHostFragment.findNavController(GamePlayScreen.this)
                        .navigate(R.id.action_GamePlay_to_GameConfig);
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