package com.example.smarthome.ui.curtain;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smarthome.R;

public class CurtainFragment extends Fragment {

    private CurtainViewModel curtainViewModel;
    ImageView white_rect;
    GradientDrawable drawable;
    ImageView context;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        curtainViewModel =
                new ViewModelProvider(this).get(CurtainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_curtain, container, false);

        white_rect = (ImageView) white_rect.findViewById(R.id.cur_whiterect);

        GradientDrawable drawable = (GradientDrawable) context.getDrawable(R.drawable.background_rounding);

        white_rect.setBackground(drawable);
        white_rect.setClipToOutline(true);

        return root;
    }
}