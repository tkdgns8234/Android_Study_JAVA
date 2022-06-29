package com.test.viewbinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.test.viewbinding.databinding.FragmentTestBinding;

public class FragmentTest extends Fragment {
    FragmentTestBinding mFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBinding = FragmentTestBinding.inflate(inflater, container, false);

        mFragmentBinding.btnFrgTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentBinding.tvFrgTest.setText("Fragment binding test complete");
            }
        });

        return mFragmentBinding.getRoot();
    }
}
