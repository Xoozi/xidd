package com.galotec.xidd.datawrap;

import java.util.List;

import org.jdom.Element;

public class DOMCast{

    public static final String NODE_LINE = "line";

    String cast = "";

    public DOMCast(Element root){

        if(null == root)
            return ;

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

        cast = sb.toString();
    }
}
