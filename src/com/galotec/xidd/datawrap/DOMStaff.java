package com.galotec.xidd.datawrap;

import org.jdom.Element;

public class DOMStaff{

    public static final String NODE_NAME = "name";
    public static final String NODE_IMG = "img";
    public static final String NODE_DES = "des";

    public final String name;
    public final String img;
    public final String des;

    public DOMStaff(Element root){

        name = XmlDOM.getValue(root, NODE_NAME);
        img  = XmlDOM.getValue(root, NODE_IMG);
        des  = XmlDOM.getValue(root, NODE_DES);
    }
}
