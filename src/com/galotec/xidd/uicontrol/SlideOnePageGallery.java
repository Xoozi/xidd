package com.galotec.xidd.uicontrol;


import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

@SuppressWarnings("deprecation")
public class SlideOnePageGallery extends Gallery {

    private GalleryTapListener _tapListener = null;

    public SlideOnePageGallery(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    public SlideOnePageGallery(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public SlideOnePageGallery(Context context){
        super(context);
    }

    public void setTapListener(GalleryTapListener tapListener){
        _tapListener = tapListener;
    }

    private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
        return e2.getX() > e1.getX();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int kEvent;
        if (isScrollingLeft(e1, e2)) {
            // Check if scrolling left
            kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
        } else {
            // Otherwise scrolling right
            kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
        }

        onKeyDown(kEvent, null);
        return true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if(null != _tapListener){
            _tapListener.onGalleryTap();
            return true;
        }
        return false;
    }


    public interface GalleryTapListener{
        void onGalleryTap();
    }
}
