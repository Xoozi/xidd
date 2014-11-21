package com.galotec.xidd;

import java.io.IOException;

import org.jdom.JDOMException;

import com.galotec.xidd.datawrap.AssetTool;
import com.galotec.xidd.datawrap.DOMXiddData;
import com.galotec.xidd.uicontrol.Banner;
import com.galotec.xidd.uicontrol.BidirSlidingLayout;
import com.galotec.xidd.uicontrol.Columns;
import com.galotec.xidd.uicontrol.DramaGallery;
import com.galotec.xidd.uicontrol.IOnWidgetInteract;
import com.xoozi.andromeda.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
public class ActivityMain extends Activity 
    implements IOnWidgetInteract, OnClickListener{

    static DOMXiddData          _xiddData;
    private BidirSlidingLayout  _bidirSldingLayout;
    private Banner              _banner;
    private DramaGallery        _dramaGallery;
    private Columns             _columns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        _initWork();
    }

    @Override
    public void onClickDrama(String id) {
        Utils.amLog("got a click id:"+id);
        if(_xiddData.hasDrama(id)){
            Intent intent = new Intent(this,ActivityDrama.class);
            intent.putExtra(ActivityDrama.KEY_ID, id);
            startActivity(intent);
        }
    }
    @Override
    public void onClickGroupItem(Object item) {
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_exit:
                finish();
                break;
            case R.id.btn_hall:
                Intent intent = new Intent(this,ActivitySeason.class);
                startActivity(intent);
                break;
            case R.id.btn_ticket:
                Intent intent2 = new Intent(this,ActivityTicket.class);
                startActivity(intent2);
                break;
        }
    }
    private void _initWork(){
        _bidirSldingLayout = (BidirSlidingLayout) 
            findViewById(R.id.bidir_sliding_layout);

        View btnLogin = findViewById(R.id.btn_menu_left);
        View btnMenu = findViewById(R.id.btn_menu_right);

        _bidirSldingLayout.setScrollEvent(findViewById(R.id.content));
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_bidirSldingLayout.isLeftLayoutVisible()) {
                    _bidirSldingLayout.scrollToContentFromLeftMenu();
                } else {
                    _bidirSldingLayout.initShowLeftState();
                    _bidirSldingLayout.scrollToLeftMenu();
                }
            }
        });
        btnMenu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_bidirSldingLayout.isRightLayoutVisible()) {
                    _bidirSldingLayout.scrollToContentFromRightMenu();
                } else {
                    _bidirSldingLayout.initShowRightState();
                    _bidirSldingLayout.scrollToRightMenu();
                }
            }
        });

        try {
            _xiddData  = AssetTool.loadConfig(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }

        //dummy
        /*
        {
            for(DOMDrama drama: _xiddData.dramaList){
                
                Utils.amLog("name:"+drama.name);
                Utils.amLog("\n" + drama.cast);
            }
            for(DOMPoster poster: _xiddData.posterList){
                
                Utils.amLog("img:"+poster.img);
            }
        }*/
        
        _banner = new Banner(this, findViewById(R.id.field_banner), this);
        _banner.setData(_xiddData.posterList);

        _dramaGallery = new DramaGallery(this, 
                findViewById(R.id.field_gallery), this);
        _dramaGallery.setData(_xiddData.dramaList);

        _columns = new Columns(this, findViewById(R.id.field_columns));
        _columns.setColumnList(_xiddData.columnList);

        findViewById(R.id.btn_exit).setOnClickListener(this);
        findViewById(R.id.btn_ticket).setOnClickListener(this);
        findViewById(R.id.btn_hall).setOnClickListener(this);
    }


}
