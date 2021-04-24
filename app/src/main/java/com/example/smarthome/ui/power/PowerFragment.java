package com.example.smarthome.ui.power;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smarthome.R;
import com.example.smarthome.ui.light.LightViewModel;

public class PowerFragment extends Fragment {

    private PowerViewModel powerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        powerViewModel =
                new ViewModelProvider(this).get(PowerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_power, container, false);
        final TextView textView = root.findViewById(R.id.text_power);
        powerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}