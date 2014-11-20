package com.galotec.xidd.datawrap;

import java.io.IOException;

import org.jdom.Element;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class DOMDrama{

    public static final String NODE_ROOT        = "drama";
    public static final String NODE_ID          = "id";
    public static final String NODE_NAME        = "name";
    public static final String NODE_IMG         = "img";
    public static final String NODE_CAST        = "cast";
    public static final String NODE_LOCATION    = "location";
    public static final String NODE_ORG         = "org";
    public static final String NODE_DATE        = "date";
    public static final String NODE_PRICE       = "price";
    public static final String NODE_DES         = "des";

    public static final String NODE_GROUP       = "group";
    public static final String NODE_STAFFS      = "staffs";

    public final DOMGroup    group;

    public final String      id;
    public final String      name;
    public final String      img;
    public final String      date;
    public final String      price;
    public final String      des;
    public final String      cast;
    public final String      location;
    public final String      org;
    public final Drawable    draw;

    public DOMDrama(Context context, Element root){
        
        id     = XmlDOM.getValue(root, NODE_ID);
        name   = XmlDOM.getValue(root, NODE_NAME);
        img    = XmlDOM.getValue(root, NODE_IMG);
        date   = XmlDOM.getValue(root, NODE_DATE);
        price  = XmlDOM.getValue(root, NODE_PRICE);
        des    = XmlDOM.getValue(root, NODE_DES);

        DOMCast castDOM   = new DOMCast(root.getChild(NODE_CAST));
        DOMLocation locationDOM  = new DOMLocation(root.getChild(NODE_LOCATION));
        DOMOrg orgDOM = new DOMOrg(root.getChild(NODE_ORG));


        location = locationDOM.location;
        org = orgDOM.org;
        cast   = castDOM.cast;

        group  = new DOMGroup(root.getChild(NODE_GROUP));

        draw = _loadDrawable(context, img);
    }

    /** 
     * location + org + cast
     */
    public String getLOC(){
        StringBuilder sb = new StringBuilder();
        sb.append(location);
        sb.append("\n");
        sb.append(org);
        sb.append("\n");
        sb.append(cast);

        return sb.toString();
    }

    private Drawable _loadDrawable(Context context, String img){
        Drawable ret ;
        try {
            ret = AssetTool.getDrawable(context, img);
        } catch (IOException e) {
            ret = null;
        }

        return ret;
    }
}
