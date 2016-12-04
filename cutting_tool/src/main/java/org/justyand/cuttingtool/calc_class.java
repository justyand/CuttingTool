package org.justyand.cuttingtool;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;

/**
 * Created by justy on 16.10.2016.
 */

public class calc_class extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root_view = inflater.inflate(R.layout.calc_layout, container, false);



        return root_view;
    }

}
