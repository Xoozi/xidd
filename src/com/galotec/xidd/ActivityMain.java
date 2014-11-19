package com.galotec.xidd;

import java.io.IOException;

import org.jdom.JDOMException;

import com.galotec.xidd.datawrap.AssetTool;
import com.galotec.xidd.datawrap.DOMXiddData;
import com.galotec.xidd.uicontrol.Banner;
import com.galotec.xidd.uicontrol.BidirSlidingLayout;
import com.galotec.xidd.uicontrol.DramaGallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
public class ActivityMain extends Activity {

    private BidirSlidingLayout  _bidirSldingLayout;
    private DOMXiddData         _xiddData;
    private Banner              _banner;
    private DramaGallery        _dramaGallery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        _initWork();
    }

    private void _initWork(){
        _bidirSldingLayout = (BidirSlidingLayout) findViewById(R.id.bidir_sliding_layout);
        View btnLogin = findViewById(R.id.btn_login);
        View btnMenu = findViewById(R.id.btn_menu);

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
        
        _banner = new Banner(this, findViewById(R.id.field_banner));
        _banner.setData(_xiddData.posterList);

        _dramaGallery = new DramaGallery(this, findViewById(R.id.field_gallery));
        _dramaGallery.setData(_xiddData.dramaList);
    }

}
