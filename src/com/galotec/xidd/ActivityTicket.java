package com.galotec.xidd;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.galotec.xidd.datawrap.AssetTool;
import com.galotec.xidd.datawrap.DOMDrama;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityTicket extends Activity 
    implements OnClickListener, OnItemClickListener{

    private ListView            _listDrama;
    private List<DramaItem>     _itemArray = new ArrayList<DramaItem>();
    private DramaAdapter        _dramaAdapter = new DramaAdapter();
    private LayoutInflater          _layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        
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
    public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {

        DramaItem item = _itemArray.get(pos); 
        
        Intent intent = new Intent(this,ActivityDrama.class);
        intent.putExtra(ActivityDrama.KEY_ID, item.id);
        startActivity(intent);
    }

    private void _initWork(){
        _layoutInflater = LayoutInflater.from(this);

        TextView textTitle= (TextView)findViewById(R.id.text_title);
        textTitle.setText(getResources().getString(R.string.title_ticket));
        findViewById(R.id.btn_back).setOnClickListener(this);

        

        for(DOMDrama drama: ActivityMain._xiddData.dramaList){
            DramaItem item = new DramaItem();
            item.id = drama.id;
            item.name = drama.name;
            item.org = drama.org;
            item.price=drama.price;
            try {
                item.draw = AssetTool.getDrawable(this, drama.img);
                _itemArray.add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        _listDrama = (ListView)findViewById(R.id.list_drama);
        _listDrama.setAdapter(_dramaAdapter);
        _listDrama.setOnItemClickListener(this);
    }

    private class DramaItem{
        String id;
        String name;
        String org;
        String price;
        Drawable draw;
    } 

    private class DramaAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return _itemArray.size();
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
            if(null == convertView){

                convertView = _layoutInflater.inflate(
                        R.layout.item_drama_list,
                        null);
            }
            DramaItem item = _itemArray.get(pos); 
            ImageView img = (ImageView)convertView.findViewById(R.id.img_item);
            TextView title = (TextView)convertView.findViewById(R.id.text_title);
            TextView org = (TextView)convertView.findViewById(R.id.text_org);
            TextView price = (TextView)convertView.findViewById(R.id.text_price);

            img.setImageDrawable(item.draw);
            title.setText(item.name);
            org.setText(item.org);
            price.setText(item.price);
            return convertView;
        }

    }

}

