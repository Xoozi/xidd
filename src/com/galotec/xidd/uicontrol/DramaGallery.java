package com.galotec.xidd.uicontrol;

import java.util.ArrayList;
import java.util.List;

import com.galotec.xidd.R;
import com.galotec.xidd.datawrap.DOMDrama;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class DramaGallery implements OnItemClickListener{

    private Context                 _context;
    private LayoutInflater          _layoutInflater;
    private View                    _rootView;
    private IOnWidgetInteract       _widgetInteract;
    private Gallery                 _gallery;
    private DramaAdapter            _dramaAdapter;
    private List<DramaItem>         _dramaList;
    private String                  _selectedId = null;
    private View                    _lastView = null;

    public DramaGallery(Context context, View rootView,
            IOnWidgetInteract widgetInteract){

        _context = context;
        _rootView= rootView;
        _widgetInteract = widgetInteract;
        _layoutInflater = LayoutInflater.from(_context);
        
        _gallery = (Gallery)_rootView.findViewById(R.id.gallery_drama);

        _dramaAdapter = new DramaAdapter();

        _gallery.setAdapter(_dramaAdapter);

        _gallery.setOnItemClickListener(this);
    }

    public void setData(List<DOMDrama> dramas){

        List<DramaItem> gItemList = new ArrayList<DramaItem>();

        for(DOMDrama drama: dramas){
            DramaItem item = new DramaItem();
            item.id = drama.id;
            item.img = drama.draw;
            gItemList.add(item);
        }
        _dramaAdapter.setDramaItemList(gItemList);
        _gallery.setSelection(Integer.MAX_VALUE / 2);
    }

    private class DramaItem{
        String id;
        Drawable img;
    }

    private class DramaAdapter extends BaseAdapter {

        public void setDramaItemList(List<DramaItem> dramaList){
            _dramaList    = dramaList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if(null == _dramaList){
                return 0;
            }else{
                return Integer.MAX_VALUE;
            }
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
            int length = _dramaList.size();
            if(pos > length)
                pos = pos % length;
            DramaItem item = _dramaList.get(pos);
            ImageView imgView;
            if(null == convertView){
                convertView = _layoutInflater.inflate(R.layout.item_gallery, 
                        null);
            }
            imgView = (ImageView)convertView.findViewById(R.id.img_item);
            View mask = convertView.findViewById(R.id.field_mask);
            if(_isSameId(_selectedId, item.id)){
                mask.setVisibility(View.VISIBLE);
            }else{
                mask.setVisibility(View.INVISIBLE);
            }
            imgView.setImageDrawable(item.img);
            convertView.setTag(item.id);
            return convertView;
        }

        private boolean _isSameId(String selectedId, String id){
            boolean ret = false;
            
            do{
                if(null == selectedId)
                    break;

                if(selectedId.equals(id))
                    ret = true;
            }while(false);

            return ret;
        }

    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {

        _selectedId = (String)view.getTag(); 
        if(null != _lastView){
            _lastView.setVisibility(View.INVISIBLE);
        }

        _lastView = view.findViewById(R.id.field_mask);
        _lastView.setVisibility(View.VISIBLE);
        _widgetInteract.onClickDrama(_selectedId);
    }

}
