package com.galotec.xidd.datawrap;

import org.jdom.Element;

public class DOMPoster{

    public static final String ATTR_ID  = "id";
    public static final String ATTR_IMG = "img";

    public final String id;
    public final String img;

    public DOMPoster(Element e){

        id = e.getAttributeValue(ATTR_ID);
        img= e.getAttributeValue(ATTR_IMG);
    }
}
