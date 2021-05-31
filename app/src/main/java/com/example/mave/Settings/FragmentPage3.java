package com.example.mave.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mave.R;

public class FragmentPage3 extends Fragment {
    ViewGroup viewGroup;
    private TextView FlowerState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_page_3, container, false);

<<<<<<< HEAD
//        FlowerState = (TextView) viewGroup.findViewById(R.id.FlowerState);
=======
        FlowerState = (TextView) viewGroup.findViewById(R.id.flowerState);
>>>>>>> 2d266af40c31712d9609ec691a46f2510a2dc9b4

        FlowerState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StateOfFlower.class);
                startActivity(intent);
            }
        });

        return viewGroup;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
