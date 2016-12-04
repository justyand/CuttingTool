package org.justyand.cuttingtool;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class hlavni extends AppCompatActivity {

    private JSONObject data_json = null;
    private static JSONArray materials  = null;
    private JSONArray type_work  = null;
    private static JSONArray rounds     = null;
    private JSONArray feed       = null;
    private JSONArray cats       = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hlavni);

        loadJSONFile();

        checkUpdates cU = new checkUpdates(hlavni.this,"http://support.justyand.org/cuttingtool.php?data");
        cU.execute();
    }

    public static String thisVersion = "0.0.0";
    public static String thisDBVersion = "0.0.0";

    public void loadJSONFile()
    {

        try {
            data_json = new JSONObject(loadJSON());

            materials = data_json.getJSONArray("materialy");
            type_work = data_json.getJSONArray("typ_obrabeni");
            rounds    = data_json.getJSONArray("otacky");
            feed      = data_json.getJSONArray("posuv");
            cats      = data_json.getJSONArray("kat_nastroje");

        } catch (Exception e)
        {}

    }

    public static String[] getDataJSON(int id, int index) throws JSONException {
        ArrayList<String> res = new ArrayList<String>();
        JSONArray dj = (JSONArray) materials;
        JSONArray ot = (JSONArray) rounds;
        if( id == 0 )
        {
            // return materials

            for( int i = 0; i < dj.length(); i++)
            {
                try {
                    String st = dj.getString(i);
                    res.add(st);
                } catch (Exception e) {
                }

            }

        } else
        if( id == 1 )
        {
            JSONArray inner = ot.getJSONArray( index );

            for( int i = 0; i < inner.length(); i++)
            {
               try {
                     String st = inner.getString(i);
                     res.add(st);
                   } catch (Exception e) {
                     }

            }
        }

        String ret[] = new String[res.size()];
        ret = res.toArray(ret);

        //Log.e("jsontes1", ret.toString());

       return ret;
    }

    public String loadJSON() {
        String json = null;
        try {
            InputStream is = getAssets().open( "data.json" );
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void cutting_calc(View view)
    {

        Intent i = new Intent(this, trig_pager.class);
        startActivity(i);

    }

    public void gonio(View view)
    {

        //setContentView(R.layout.gonio);

    }

}