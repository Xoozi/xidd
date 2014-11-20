package com.galotec.xidd;



import java.io.IOException;

import com.galotec.xidd.datawrap.AssetTool;
import com.galotec.xidd.datawrap.DOMDrama;
import com.galotec.xidd.uicontrol.DrawerPanel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDrama extends Activity implements OnClickListener {

    public static final String KEY_ID = "id";

    private DOMDrama     _drama;
    private TextView     _textTitle;
    private TextView     _textCast;
    private TextView     _textPrice;
    private ImageView    _imgPoster;
    private DrawerPanel  _drawerPanelDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drama);
        
        _initWork();
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btn_back:

                finish();
                break;

        }
    }

    private void _initWork(){
        Intent intent = getIntent();
        String id = intent.getStringExtra(KEY_ID);

        _drama = ActivityMain._xiddData.queryDrama(id);

        _textTitle = (TextView)findViewById(R.id.text_title);
        _textCast = (TextView)findViewById(R.id.text_cast);
        _textPrice = (TextView)findViewById(R.id.text_price);
        _imgPoster = (ImageView)findViewById(R.id.img_poster);

        findViewById(R.id.btn_back).setOnClickListener(this);

        _drawerPanelDes = new DrawerPanel(this, 
                findViewById(R.id.field_drawer_des));

        _textTitle.setText(_drama.name);
        _textCast.setText(_drama.getLOC());
        _textPrice.setText(_drama.price);
        _drawerPanelDes.setContent(_drama.des);
        _drawerPanelDes.setDrawerState(true);

        try {
            Drawable draw = AssetTool.getDrawable(this, _drama.img);
            _imgPoster.setImageDrawable(draw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

