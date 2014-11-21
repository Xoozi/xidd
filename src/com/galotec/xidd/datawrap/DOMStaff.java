package com.galotec.xidd.datawrap;

import java.util.List;

import org.jdom.Element;

public class DOMStaff{

    public static final String NODE_NAME = "name";
    public static final String NODE_IMG = "img";
    public static final String NODE_DES = "des";
    public static final String NODE_LINE = "line";

    public final String name;
    public final String img;
    public final String des;

    public DOMStaff(Element root){

        name = XmlDOM.getValue(root, NODE_NAME);
        img  = XmlDOM.getValue(root, NODE_IMG);
        des  = _loadDes(XmlDOM.getChildElement(root, NODE_DES));
    }


    private String _loadDes(Element root){

        if(null == root)
            return "";

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        List<Element> children = XmlDOM.getChildElements(root, NODE_LINE);
        
        for(Element e:children){
            if(first){
                first = false;
            }else{
                sb.append("\n");
            }
            sb.append(e.getValue());
        }

        return sb.toString();
    }
}
