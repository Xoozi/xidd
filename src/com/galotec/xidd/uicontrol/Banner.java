package com.galotec.xidd.uicontrol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.galotec.xidd.R;
import com.galotec.xidd.datawrap.AssetTool;
import com.galotec.xidd.datawrap.DOMPoster;
import com.galotec.xidd.uicontrol.SlideOnePageGallery.GalleryTapListener;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Gallery.LayoutParams;

@SuppressWarnings("deprecation")
public class Banner implements OnItemSelectedListener,
       GalleryTapListener{
    
    private Context                 _context;
    private View                    _rootView;
    private SlideOnePageGallery     _gallery;
    private GalleryFooter           _galleryFooter;
    private GalleryAdapter          _galleryAdapter;
    private List<GalleryItem>       _galleryItemList;
    private IOnWidgetInteract       _widgetInteract;
    private int                     _length;    
    private int                     _pos;
    
    public  Banner(Context context, View rootView, 
            IOnWidgetInteract widgetInteract){
        _context = context;
        _rootView= rootView;
        _widgetInteract = widgetInteract;
        
        _gallery = (SlideOnePageGallery) _rootView.findViewById(R.id.gallery_banner);
        
        _galleryFooter = new GalleryFooter(_context, _rootView.findViewById(R.id.field_gallery_footer));
        
        _initWork();
    }
    
    
    private void    _initWork(){
        
        _galleryAdapter = new GalleryAdapter();
        
        _gallery.setOnItemSelectedListener(this);
        
        _gallery.setAdapter(_galleryAdapter);

        _gallery.setTapListener(this);
    }

    public void setData(List<DOMPoster> posters){

        List<GalleryItem> gItemList = new ArrayList<GalleryItem>();

        for(DOMPoster poster: posters){
            GalleryItem item = new GalleryItem();
            item.id = poster.id;
            try {
                item.img = AssetTool.getDrawable(_context, poster.img);
                gItemList.add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        _galleryAdapter.setGalleryItemList(gItemList);
        _galleryFooter.setCount(gItemList.size());
        _gallery.setSelection(Integer.MAX_VALUE / 2);
    }
    
    private class GalleryItem{
        String      title;
        String      id;
        Drawable    img;
    }
    
    
    private class   GalleryAdapter extends BaseAdapter{
        
        public void setGalleryItemList(List<GalleryItem> galleryItemList){
            _galleryItemList    = galleryItemList;
            _length               = _galleryItemList.size();
            notifyDataSetChanged();
        }
        

        @Override
        public int getCount() {
            if(null == _galleryItemList)
                return 0;
            else
                return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup arg2) {
            
            if(pos > _length)
                pos = pos % _length;
            ImageView   imgView;
            if(null == convertView){
                imgView = new ImageView(_context);
                imgView.setAdjustViewBounds(true);
                imgView.setLayoutParams(new LayoutParams(
                            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                imgView.setBackgroundDrawable(_galleryItemList.get(pos).img);
                
                convertView = imgView;
            }else{
                ((ImageView)convertView).setBackgroundDrawable(_galleryItemList.get(pos).img);
            }
            return convertView;
        }
        
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
            long arg3) {
        if(pos > _length)
            pos = pos % _length;
        _galleryFooter.setPos(pos);
        _galleryFooter.setTitle(_galleryItemList.get(pos).title);
        _pos = pos;
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onGalleryTap() {
        GalleryItem item = _galleryItemList.get(_pos);
        _widgetInteract.onClickDrama(item.id);
    }

}
