package com.galotec.xidd.uicontrol;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.galotec.xidd.R;
import com.galotec.xidd.datawrap.DOMColumn;

public class Columns{

    @SuppressWarnings("unused")
    private Context                 _context;
    private View                    _rootView;
    private TextView                _textTitle;
    private TextView                _textFirst;
    private TextView                _textContent;

    private List<DOMColumn>         _columnList;

    private int                     _pos;

    public Columns(Context context, View rootView){

        _context = context;
        _rootView= rootView;

        _textTitle   = (TextView)_rootView.findViewById(R.id.text_title);
        _textFirst   = (TextView)_rootView.findViewById(R.id.text_first);
        _textContent = (TextView)_rootView.findViewById(R.id.text_content);
    }

    public void setColumnList(List<DOMColumn> columnList){
        _columnList = columnList;
        _pos = 0;
        if(null != _columnList && _columnList.size() > 0){
            _setColumn(_columnList.get(_pos));
        }
    }

    private void _setColumn(DOMColumn column){
        _textTitle.setText(column.title);
        _textFirst.setText(column.first);
        _textContent.setText(column.content);
    }
}
