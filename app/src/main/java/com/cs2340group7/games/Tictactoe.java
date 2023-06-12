package com.cs2340group7.games;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cs2340group7.games.databinding.GamePlayScreenBinding;
import com.cs2340group7.games.databinding.TictactoeBinding;

public class Tictactoe extends Fragment {
    private @NonNull TictactoeBinding binding;
    private TextView gameTime;

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

        SelectedSpriteViewModel viewModel = new ViewModelProvider(requireActivity()).get(SelectedSpriteViewModel.class);
        int selectedSpriteResId = viewModel.getSelectedSpriteResId();
        if (selectedSpriteResId != -1) {
            playerName.setText(getArguments().get("playerName").toString());
            profileImage.setImageResource(selectedSpriteResId);
        }
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                gameTime.setText("Time Left: " + millisUntilFinished / 1000);
                // add in the method to call so that the PC makes its move and users move is skipped

            }


            public void onFinish() {
                gameTime.setText("Your turn was skipped");
                // add in the method to call so that the PC makes its move and users move is skipped


            }
        }.start();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}