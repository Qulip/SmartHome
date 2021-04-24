package com.example.smarthome.ui.curtain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CurtainViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CurtainViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Curtain fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}