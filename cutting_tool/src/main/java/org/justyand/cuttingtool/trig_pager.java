package org.justyand.cuttingtool;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import static java.security.AccessController.getContext;


/**
 * Created by justy on 15.10.2016.
 */

public class trig_pager extends AppCompatActivity {

    ViewPager pager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trigonometry);

        pager= (ViewPager) findViewById(R.id.view_pager);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);

        // Fragment manager to add fragment in viewpager we will pass object of Fragment manager to adpater class.
        FragmentManager manager=getSupportFragmentManager();

        //object of PagerAdapter passing fragment manager object as a parameter of constructor of PagerAdapter class.
        TabsPagerAdapter adapter = new TabsPagerAdapter(manager);

        //set Adapter to view pager
        pager.setAdapter(adapter);

        //set tablayout with viewpager
        tabLayout.setupWithViewPager(pager);
        if( getResources().getConfiguration().orientation == 2 ) {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else
        {

        }

        // adding functionality to tab and viewpager to manage each other when a page is changed or when a tab is selected
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Setting tabs from adpater
        tabLayout.setTabsFromPagerAdapter(adapter);

    }

    public void onFocusChanged(View view)
    {

        final EditText txtData = (EditText) view.findViewById( R.id.cut_speed );
        txtData.setOnFocusChangeListener( new View.OnFocusChangeListener() {

            public void onFocusChange( View v, boolean hasFocus ) {
                if( hasFocus ) {
                    txtData.setText( "", TextView.BufferType.EDITABLE );
                }
            }

        } );

    }

    public void toggle(View view)
    {
        ToggleButton t2 = (ToggleButton) pager.findViewById(R.id.toggleButton2);
        ToggleButton t1 = (ToggleButton) pager.findViewById(R.id.toggleButton3);

        TextView tv = (TextView) pager.findViewById(R.id.textView7);

        if( view.getTag().equals("first") )
        {
            t1.setChecked(false);
            t2.setChecked(true);
        }else
        {
            t2.setChecked(false);
            t1.setChecked(true);
        }

    }
}





