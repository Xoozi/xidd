package com.galotec.xidd;




import java.io.IOException;
import java.util.List;

import com.galotec.xidd.datawrap.AssetTool;
import com.galotec.xidd.datawrap.DOMDrama;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivitySeason extends Activity implements OnClickListener {

    private TextView     _textTitle;
    private TextView[]   _textName = new TextView[11];
    private TextView[]   _textDate  = new TextView[11];
    private ImageView[]  _img  = new ImageView[11];
    private List<DOMDrama> _dramaList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season);

        _initWork();
    }

    @Override
    public void onClick(View view) {
        
        switch(view.getId()){
            case R.id.btn_back:

                finish();
                break;
            
            default:
                String id = (String)view.getTag();
                Intent intent = new Intent(this,ActivityDrama.class);
                intent.putExtra(ActivityDrama.KEY_ID, id);
                startActivity(intent);
                break;
        }
    }

    private void _initWork(){
        _dramaList = ActivityMain._xiddData.dramaList;

        _textTitle = (TextView)findViewById(R.id.text_title);
        _textTitle.setText(getResources().getString(R.string.title_season));
        findViewById(R.id.btn_back).setOnClickListener(this);

        _textName[0] = (TextView)findViewById(R.id.text_title_0);
        _textDate[0] = (TextView)findViewById(R.id.text_date_0);
        _img[0] = (ImageView)findViewById(R.id.img_d_0);

        _textName[1] = (TextView)findViewById(R.id.text_title_1);
        _textDate[1] = (TextView)findViewById(R.id.text_date_1);
        _img[1] = (ImageView)findViewById(R.id.img_d_1);

        _textName[2] = (TextView)findViewById(R.id.text_title_2);
        _textDate[2] = (TextView)findViewById(R.id.text_date_2);
        _img[2] = (ImageView)findViewById(R.id.img_d_2);
        
        _textName[3] = (TextView)findViewById(R.id.text_title_3);
        _textDate[3] = (TextView)findViewById(R.id.text_date_3);
        _img[3] = (ImageView)findViewById(R.id.img_d_3);

        _textName[4] = (TextView)findViewById(R.id.text_title_4);
        _textDate[4] = (TextView)findViewById(R.id.text_date_4);
        _img[4] = (ImageView)findViewById(R.id.img_d_4);

        _textName[5] = (TextView)findViewById(R.id.text_title_5);
        _textDate[5] = (TextView)findViewById(R.id.text_date_5);
        _img[5] = (ImageView)findViewById(R.id.img_d_5);


        _textName[6] = (TextView)findViewById(R.id.text_title_6);
        _textDate[6] = (TextView)findViewById(R.id.text_date_6);
        _img[6] = (ImageView)findViewById(R.id.img_d_6);

        _textName[7] = (TextView)findViewById(R.id.text_title_7);
        _textDate[7] = (TextView)findViewById(R.id.text_date_7);
        _img[7] = (ImageView)findViewById(R.id.img_d_7);

        _textName[8] = (TextView)findViewById(R.id.text_title_8);
        _textDate[8] = (TextView)findViewById(R.id.text_date_8);
        _img[8] = (ImageView)findViewById(R.id.img_d_8);
        
        _textName[9] = (TextView)findViewById(R.id.text_title_9);
        _textDate[9] = (TextView)findViewById(R.id.text_date_9);
        _img[9] = (ImageView)findViewById(R.id.img_d_9);

        _textName[10] = (TextView)findViewById(R.id.text_title_10);
        _textDate[10] = (TextView)findViewById(R.id.text_date_10);
        _img[10] = (ImageView)findViewById(R.id.img_d_10);

        for(int index=0; index<11; index++){
            DOMDrama drama = _dramaList.get(index);
            _textName[index].setText(drama.name);
            _textDate[index].setText(drama.date);
            try {
                Drawable draw = AssetTool.getDrawable(this, drama.img);
                _img[index].setImageDrawable(draw);
                _img[index].setOnClickListener(this);
                _img[index].setTag(drama.id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

