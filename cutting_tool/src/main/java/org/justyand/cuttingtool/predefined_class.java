package org.justyand.cuttingtool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_item;
import static org.justyand.cuttingtool.hlavni.getDataJSON;

/**
 * Created by justy on 16.10.2016.
 */

public class predefined_class extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View res_layout = inflater.inflate(R.layout.predef_layout, container, false);

        final Spinner mat = (Spinner) res_layout.findViewById(R.id.material);

        final EditText dia = (EditText) res_layout.findViewById(R.id.diam);

        mat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dia.setText( dia.getText() );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        String data[] = new String[0];
        try {
            data = hlavni.getDataJSON(0,0);
        } catch (JSONException e) {

        }

        mat
                .setAdapter(new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item,
                        data));

        dia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String e_text =  s.toString();
                TextView result = (TextView) res_layout.findViewById(R.id.textView7);

                float fl = 0;

                try {
                    fl = Float.parseFloat(e_text);
                } catch (Exception e)
                {  };

                if( ( fl >= 2 ) & ( fl <= 15 ) ) {

                    // load vrtani
                    String[] vrt = new String[0];
                    try {
                        vrt = getDataJSON(1, 0);
                    } catch (JSONException e) {

                    }

                    int mat_type = mat.getSelectedItemPosition();
                    Float mt = Float.valueOf(vrt[mat_type]);

                    e_text = String.valueOf(Math.round( mt / fl ));

                } else
                { e_text = "N/A"; }

                result.setText( e_text );

            }
        });

        return res_layout;
    }

}
