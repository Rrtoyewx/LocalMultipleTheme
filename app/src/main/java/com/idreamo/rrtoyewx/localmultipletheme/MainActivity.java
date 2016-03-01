package com.idreamo.rrtoyewx.localmultipletheme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private RelativeLayout relativeLayout;
    private boolean isNight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl);
        float textSize = textView.getTextSize();
        Log.e("TAG", textSize + "textSize");


        ColorAttr backgroudAttr = new ColorAttr(textView, R.attr.background_color, this);
        TextColorAttr textColorAttr = new TextColorAttr(textView, R.attr.text_color, this);
        TextSizeAttr textSizeAttr = new TextSizeAttr(textView, R.attr.text_size, this);



        DrawAttr backgroudAttr1 = new DrawAttr(relativeLayout, R.attr.background_drawable, this);


        MultipleTheme.newSingleInstance()
                .addMultipleItem(textView, textColorAttr, backgroudAttr, textSizeAttr)
                .addMultipleItem(relativeLayout, true, backgroudAttr1);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNight) {
                    MainActivity.this.setTheme(R.style.DayTheme);
                    MultipleTheme.newSingleInstance().startDispatchChangeTheme(R.style.DayTheme);
                } else {
                    MainActivity.this.setTheme(R.style.NightTheme);
                    MultipleTheme.newSingleInstance().startDispatchChangeTheme(R.style.NightTheme);
                }
                isNight = !isNight;
            }
        });

    }
}
