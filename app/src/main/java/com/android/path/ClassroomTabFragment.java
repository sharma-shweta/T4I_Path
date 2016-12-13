package com.android.path;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ClassroomTabFragment extends Fragment {// In this case, the fragment displays simple text based on the page
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static ClassroomTabFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ClassroomTabFragment fragment = new ClassroomTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        if (mPage == 1){
            view = inflater.inflate(R.layout.class_data_fragment, container, false);
        }
        else if (mPage == 2){
            view = inflater.inflate(R.layout.class_distribution, container, false);
        }
        else {
            view = inflater.inflate(R.layout.class_test, container, false);
        }
        return view;
    }

}
