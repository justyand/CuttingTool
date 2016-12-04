package org.justyand.cuttingtool;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.MutableInt;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;

/**
 * Created by justy on 01.12.2016.
 */

public class checkUpdates extends AsyncTask<Void, Void, String> {
    private String mUrl;
    private Context mContext;
    private Boolean isDataOnline;

    public checkUpdates(Context context, String url) {
        mUrl = url;
        mContext = context;
    }

    public boolean checkAppUpdate()
    {
      return false;
    };

    public boolean checkDBUpdate()
    {
        return false;
    }

    @Override
    protected String doInBackground(Void... params) {
        String resultString = null;

        resultString = getJSON(mUrl);

        return resultString;
    }

    @Override
    protected void onPostExecute(String strings) {
        super.onPostExecute(strings);

        JSONObject updatesURL = null;
        StringTokenizer versions = null;

        try {
            updatesURL = new JSONObject(strings);
        } catch ( JSONException je )
        {

        }

        Integer imajor = 0;
        Integer iminor = 0;
        Integer ibuild = 0;

        Integer tmajor = 0;
        Integer tminor = 0;
        Integer tbuild = 0;

        Integer dbsize = 0;

        try {
            imajor = parseVersions(updatesURL.get("appVersion").toString(), ".", 0);
            iminor = parseVersions(updatesURL.get("appVersion").toString(), ".", 1);
            ibuild = parseVersions(updatesURL.get("appVersion").toString(), ".", 2);

            tmajor = parseVersions(hlavni.thisVersion, ".", 0);
            tminor = parseVersions(hlavni.thisVersion, ".", 1);
            tbuild = parseVersions(hlavni.thisVersion, ".", 2);

            dbsize = updatesURL.getInt("dbSize");

        } catch (Exception je )
        {

        }

        if( !isDataOnline )
        {
            Toast.makeText(mContext, "Kontrola aktualizace se nezdařila", Toast.LENGTH_SHORT).show();
        }
        else
        if( ibuild > tbuild )
        {
            new AlertDialog.Builder(mContext).setMessage("Je nová verze ke stažení.\n\nPřejete si ji stáhnout?")
                    .setPositiveButton("Ano", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://support.justyand.org/cuttingtool.php"));
                            Bundle b = new Bundle();
                            b.putBoolean("new_window", true); //sets new window
                            intent.putExtras(b);
                            mContext.startActivity(intent);
                        }
                    })
                    .setNegativeButton("Ne, děkuji", new DialogInterface.OnClickListener()
                    {
                       public void onClick(DialogInterface dialog, int id)
                       {
                           dialog.cancel();
                       }
                    })
                    .setCancelable(false)
                    .setTitle("Stav update").show();
        }else
        {
            Toast.makeText(mContext,"blbl",Toast.LENGTH_LONG).show();

            //new AlertDialog.Builder(mContext).setMessage("Aplikace je aktuální. DB Size: " + humanReadableByteCount(dbsize, true)).setTitle("Stav update").show();
        }

    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    private Integer parseVersions(String data, String delimiter, Integer index )
    {
        String[] versions = data.split("\\.");
        return  Integer.parseInt(versions[index]);
    }

    private String getJSON(String url)
    {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.connect();
            int status = c.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    isDataOnline = true;
                    return sb.toString();
            }

        } catch (Exception ex) {
            isDataOnline = false;
            return ex.toString();
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    //disconnect error
                    Log.v("disconnect error", ex.toString());
                }
            }
        }
        return null;
    }
}
