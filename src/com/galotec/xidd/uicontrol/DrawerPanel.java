package com.galotec.xidd.uicontrol;

import com.galotec.xidd.R;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class DrawerPanel implements OnClickListener{

    @SuppressWarnings("unused")
    private Context                 _context;
    private View                    _rootView;
    private View                    _displayArea;
    private ImageButton             _btnSwitch;
    private TextView                _textContent;
    private boolean                 _state;


    public DrawerPanel(Context context, View rootView){
        _context = context;
        _rootView= rootView;

        _displayArea = _rootView.findViewById(R.id.field_display_area);
        _textContent = (TextView)_rootView.findViewById(R.id.text_content);
        _btnSwitch = (ImageButton)_rootView.findViewById(R.id.btn_drawer_switch);

        _rootView.findViewById(R.id.field_drawer_click).setOnClickListener(this);

        setDrawerState(false);
    }


    @Override
    public void onClick(View view) {
        setDrawerState(!_state); 
    }

    public void setContent(String content){
        _textContent.setText(content);
    }

    public void setDrawerState(boolean state){
        _state = state;

        if(_state){
            _displayArea.setVisibility(View.VISIBLE);
            _btnSwitch.setImageResource(R.drawable.btn_fold);
        }else{
            _displayArea.setVisibility(View.GONE);
            _btnSwitch.setImageResource(R.drawable.btn_exp);
        }
    }


}
