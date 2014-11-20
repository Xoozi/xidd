package com.galotec.xidd.datawrap;

import java.io.IOException;
import java.io.InputStream;

import org.jdom.JDOMException;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

public class AssetTool{

    public static final String DATA_FOLDER      = "data";
    public static final String IMG_FOLDER       = "img";
    public static final String DRAMAS_ROOT      = "dramas.xml";
    public static final String POSTERS_ROOT     = "posters.xml";
    public static final String COLUMN_ROOT      = "columns.xml";

    public static DOMXiddData loadConfig(Context context)
        throws IOException, JDOMException {

        AssetManager am =  context.getAssets();

        
        InputStream isDramas = am.open(DATA_FOLDER + "/" + DRAMAS_ROOT);
        XmlDOM domDramas = new XmlDOM(isDramas);                                        

        InputStream isPosters = am.open(DATA_FOLDER + "/" + POSTERS_ROOT);
        XmlDOM domPosters = new XmlDOM(isPosters);


        InputStream isColumns = am.open(DATA_FOLDER + "/" + COLUMN_ROOT);
        XmlDOM domColumns = new XmlDOM(isColumns);
        
        DOMXiddData ret = new DOMXiddData(context, 
                                domDramas.getRoot(),
                                domPosters.getRoot(),
                                domColumns.getRoot());
        isDramas.close();
        isPosters.close();
        isColumns.close();

        return ret;
    }

    public static DOMDrama loadDrama(Context context, String data)
            throws JDOMException, IOException {


        AssetManager am =  context.getAssets();

        
        InputStream isConfig = am.open(DATA_FOLDER + "/" + data);
        XmlDOM dom = new XmlDOM(isConfig);                                        
        
        DOMDrama ret = new DOMDrama(context, dom.getRoot());

        isConfig.close();

        return ret;
    }
            

    public static Bitmap getImage(Context context, String filename)
            throws IOException {

        Bitmap img = null;
        AssetManager am = context.getAssets();

        InputStream is = am.open(IMG_FOLDER + "/" + filename);
        img = BitmapFactory.decodeStream(is);
        is.close();

        return img;
    }

    public static BitmapDrawable getDrawable(Context context, String filename)
            throws IOException {

        BitmapDrawable bd = null;
        Resources res = context.getResources();
        AssetManager am = context.getAssets();

        InputStream is = am.open(IMG_FOLDER + "/" + filename);
        bd = new BitmapDrawable(res, is);
        is.close();
        return bd;
    }

}
