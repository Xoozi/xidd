package com.galotec.xidd.uicontrol;

import java.io.IOException;

import com.galotec.xidd.R;
import com.galotec.xidd.datawrap.AssetTool;
import com.galotec.xidd.datawrap.DOMGroup;
import com.galotec.xidd.datawrap.DOMStaff;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerList implements OnClickListener{

    private Context                 _context;
    private View                    _rootView;
    private ImageButton             _btnSwitch;
    private ViewGroup               _fieldContent;
    private boolean                 _state;
    private LayoutInflater          _layoutInflater;
    private IOnWidgetInteract       _owi;

    public DrawerList(Context context, View rootView, IOnWidgetInteract owi){
        _context = context;
        _rootView= rootView;
        _owi = owi;
        _layoutInflater = LayoutInflater.from(_context);

        _fieldContent = (ViewGroup)_rootView.findViewById(R.id.field_group_content);

        _btnSwitch = (ImageButton)_rootView.findViewById(R.id.btn_group_switch);

        _rootView.findViewById(R.id.btn_drawer_group).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        setDrawerState(!_state); 
    }

    public void setDrawerState(boolean state){
        _state = state;

        if(_state){
            _fieldContent.setVisibility(View.VISIBLE);
            _btnSwitch.setImageResource(R.drawable.btn_fold);
        }else{
            _fieldContent.setVisibility(View.GONE);
            _btnSwitch.setImageResource(R.drawable.btn_exp);
        }

        if(!_state){
            _owi.onClickGroupItem(this);
        }
    }

    public void setData(DOMGroup groupData){

        //添加团队介绍视图
        _addGroupItem(groupData);        

        //循环添加staff
        for(DOMStaff staff: groupData.staffList){
            _addStaffItem(staff);
        }
    }

    private void _addGroupItem(DOMGroup groupData){

        //如果没信息就不添加
        if(groupData.des.length() == 0)
            return ;

        View view = _layoutInflater.inflate(
                R.layout.item_staff, null);
        Drawable draw;
        ImageView img = (ImageView)view.findViewById(R.id.img_item);
        TextView name = (TextView)view.findViewById(R.id.text_name);
        TextView des = (TextView)view.findViewById(R.id.text_des);
        try {
            draw = AssetTool.getDrawable(_context, groupData.img);
            img.setImageDrawable(draw); 
        } catch (IOException e) {
            draw = null;
            img.setVisibility(View.GONE);
        }


        name.setText(groupData.name);
        des.setText(groupData.des);
        _fieldContent.addView(view);
    }

    private void _addStaffItem(DOMStaff staffData){
        
        //如果没信息就不添加
        if(staffData.name.length() == 0)
            return ;

        View view = _layoutInflater.inflate(
                R.layout.item_staff, null);
        Drawable draw;
        ImageView img = (ImageView)view.findViewById(R.id.img_item);
        TextView name = (TextView)view.findViewById(R.id.text_name);
        TextView des = (TextView)view.findViewById(R.id.text_des);
        try {
            draw = AssetTool.getDrawable(_context, staffData.img);
        } catch (IOException e) {
            draw = null;
        }

        name.setText(staffData.name);
        des.setText(staffData.des);
        img.setImageDrawable(draw); 
        _fieldContent.addView(view);
    }
}
