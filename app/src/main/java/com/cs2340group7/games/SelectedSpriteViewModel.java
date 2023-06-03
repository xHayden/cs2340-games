package com.cs2340group7.games;

import androidx.lifecycle.ViewModel;

public class SelectedSpriteViewModel extends ViewModel {
    private int selectedSpriteResId = -1;

    public int getSelectedSpriteResId() {
        return selectedSpriteResId;
    }

    public void setSelectedSpriteResId(int selectedSpriteResId) {
        this.selectedSpriteResId = selectedSpriteResId;
    }
}