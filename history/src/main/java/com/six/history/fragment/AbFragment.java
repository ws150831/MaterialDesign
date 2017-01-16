package com.six.history.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.six.history.R;

/**
 * Created by Administrator on 2016/12/16/016.
 */

public class AbFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View about=inflater.inflate(R.layout.about_fragment, null);
        return about;
    }
}
