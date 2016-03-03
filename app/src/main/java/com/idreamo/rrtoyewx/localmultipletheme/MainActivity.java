package com.idreamo.rrtoyewx.localmultipletheme;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idreamo.rrtoyewx.multiplethemelibrary.MultipleTheme;
import com.idreamo.rrtoyewx.multiplethemelibrary.multipleattrs.ColorAttr;
import com.idreamo.rrtoyewx.multiplethemelibrary.multipleattrs.DrawAttr;
import com.idreamo.rrtoyewx.multiplethemelibrary.multipleattrs.TextColorAttr;
import com.idreamo.rrtoyewx.multiplethemelibrary.multipleattrs.TextSizeAttr;
import com.idreamo.rrtoyewx.multiplethemelibrary.multipleitems.MultipleItem;

public class MainActivity extends Activity {
    private Activity mActivity;

    private Button mDayBtn;
    private Button mNightBtn;
    private ImageView mShowImage;
    private RelativeLayout mBackLayout;
    private TextView mContentText;

    private boolean isNight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MultipleTheme.init();
        mActivity = this;

        mDayBtn = (Button) findViewById(R.id.btn_day_mode);
        mNightBtn = (Button) findViewById(R.id.btn_night_mode);
        mShowImage = (ImageView) findViewById(R.id.iv_show);
        mBackLayout = (RelativeLayout) findViewById(R.id.rl);
        mContentText = (TextView) findViewById(R.id.tv_content);

        generateMultipleItems();

        bindEvent();

    }

    private void generateMultipleItems() {
        MultipleItem textItem = new MultipleItem.MultipleItemBuilder()
                .setTargetView(mContentText)
                .addAttrs(new TextColorAttr(mContentText,R.attr.text_color,this))
                .addAttrs(new TextSizeAttr(mContentText,R.attr.text_size,this))
                .create();

        MultipleTheme.newSingleInstance()
                .addMultipleItem(mBackLayout,new ColorAttr(mBackLayout,R.attr.background_color,this))
                .addMultipleItem(mShowImage,new DrawAttr(mShowImage,R.attr.image_backgroud,this))
                .addMultipleItem(textItem);
    }


    private void bindEvent() {
        mDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNight = false;
                mActivity.setTheme(R.style.DayTheme);
                MultipleTheme.newSingleInstance().startDispatchChangeTheme(R.style.DayTheme);
            }
        });

        mNightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNight = true;
                mActivity.setTheme(R.style.NightTheme);
                MultipleTheme.newSingleInstance().startDispatchChangeTheme(R.style.NightTheme);
            }
        });

    }


    @Override
    protected void onDestroy() {
        MultipleTheme.newSingleInstance().clearAll();
        super.onDestroy();
    }
}
