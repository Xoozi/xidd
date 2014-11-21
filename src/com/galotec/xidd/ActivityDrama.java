package com.galotec.xidd;



import java.io.IOException;

import com.galotec.xidd.datawrap.AssetTool;
import com.galotec.xidd.datawrap.DOMDrama;
import com.galotec.xidd.uicontrol.DrawerList;
import com.galotec.xidd.uicontrol.DrawerPanel;
import com.galotec.xidd.uicontrol.IOnWidgetInteract;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDrama extends Activity 
    implements OnClickListener, IOnWidgetInteract{

    public static final String KEY_ID = "id";

    private DOMDrama     _drama;
    private TextView     _textTitle;
    private TextView     _textCast;
    private TextView     _textPrice;
    private ImageView    _imgPoster;
    private DrawerPanel  _drawerPanelDes;
    private DrawerList   _drawerListGroup;

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

    @Override
    public void onClickDrama(String id) {
    }

    @Override
    public void onClickGroupItem(Object item) {
        if(_drawerListGroup.equals(item)){
                _drawerPanelDes.setDrawerState(true);
        }else if(_drawerPanelDes.equals(item)){
                _drawerListGroup.setDrawerState(true);
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
                findViewById(R.id.field_drawer),
                this);

        _drawerListGroup = new DrawerList(this,
                findViewById(R.id.field_drawer),
                this);


        _textTitle.setText(_drama.name);
        _textCast.setText(_drama.getLOC());
        _textPrice.setText(_drama.price);
        _drawerPanelDes.setContent(_drama.des);
        _drawerListGroup.setData(_drama.group);
        _drawerListGroup.setDrawerState(false);
        try {
            Drawable draw = AssetTool.getDrawable(this, _drama.img);
            _imgPoster.setImageDrawable(draw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

