package com.galotec.xidd.datawrap;

import org.jdom.Element;

public class DOMDrama{

    public static final String NODE_ROOT        = "drama";
    public static final String NODE_ID          = "id";
    public static final String NODE_NAME        = "name";
    public static final String NODE_IMG         = "img";
    public static final String NODE_CAST        = "cast";
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

    public DOMDrama(Element root){
        
        id     = XmlDOM.getValue(root, NODE_ID);
        name   = XmlDOM.getValue(root, NODE_NAME);
        img    = XmlDOM.getValue(root, NODE_IMG);
        date   = XmlDOM.getValue(root, NODE_DATE);
        price  = XmlDOM.getValue(root, NODE_PRICE);
        des    = XmlDOM.getValue(root, NODE_DES);

        DOMCast castDOM   = new DOMCast(root.getChild(NODE_CAST));
        cast   = castDOM.cast;

        group  = new DOMGroup(root.getChild(NODE_GROUP));
    }


}
