package com.galotec.xidd.uicontrol;

import java.util.ArrayList;
import java.util.List;

import com.galotec.xidd.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Gallery.LayoutParams;

@SuppressWarnings("deprecation")
public class GalleryFooter {
    
    private Context             _context;
    private View                    _footView;
    private ViewGroup           _markersView;
    private TextView                _textTitle;
    private Drawable                _markerNormal;
    private Drawable            _markerSelect;
    private List<ImageView>_markerList;
    private int                     _oldPos = -1;
    
    public GalleryFooter(Context context, View footView){
        _context = context;
        _footView= footView;
        
        _markersView = (ViewGroup)_footView.findViewById(R.id.field_gallery_markers);
        
        _textTitle = (TextView)_footView.findViewById(R.id.text_gallery_title);
        
        Resources res = _context.getResources();
        _markerNormal = res.getDrawable(R.drawable.img_dot);
        _markerSelect   = res.getDrawable(R.drawable.img_dot_sel);
        
        _markerList = new ArrayList<ImageView>();
    }
    
    public void setTitle(String title){
        _textTitle.setText(title);
    }
    
    public void setCount(int count){
        _markersView.removeAllViews();
        _markerList.clear();
        
        for(int index = 0; index< count; index++){
            ImageView img = new ImageView(_context);
            img.setAdjustViewBounds(true);
            img.setLayoutParams(new LayoutParams(20,20));
            img.setImageDrawable(_markerNormal);
            _markerList.add(img);
            _markersView.addView(img);
        }
        
    }
    
    public void setPos(int pos){
        
        if(pos < 0 || pos > _markerList.size())
            return;
        
        if(-1 != _oldPos){
            _markerList.get(_oldPos).setImageDrawable(_markerNormal);
        }
        _markerList.get(pos).setImageDrawable(_markerSelect);
        _oldPos = pos;
    }
    

}
