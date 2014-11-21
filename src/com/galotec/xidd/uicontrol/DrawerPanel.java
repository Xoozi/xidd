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
    private ImageButton             _btnSwitch;
    private TextView                _textContent;
    private boolean                 _state;
    private IOnWidgetInteract       _owi;


    public DrawerPanel(Context context, View rootView, IOnWidgetInteract owi){
        _context = context;
        _rootView= rootView;
        _owi = owi;

        _textContent = (TextView)_rootView.findViewById(R.id.text_des_content);
        _btnSwitch = (ImageButton)_rootView.findViewById(R.id.btn_des_switch);

        _rootView.findViewById(R.id.btn_drawer_des).setOnClickListener(this);

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
            _textContent.setVisibility(View.VISIBLE);
            _btnSwitch.setImageResource(R.drawable.btn_fold);
        }else{
            _textContent.setVisibility(View.GONE);
            _btnSwitch.setImageResource(R.drawable.btn_exp);
        }
        
        if(!_state){
            _owi.onClickGroupItem(this);
        }
    }


}
