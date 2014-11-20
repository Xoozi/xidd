package com.galotec.xidd.datawrap;

import java.util.List;

import org.jdom.Element;

public class DOMColumn{

    public static final String NODE_TITLE = "title";
    public static final String NODE_FIRST = "first";
    public static final String NODE_LINE = "line";


    public final String title;
    public final String first;
    public final String content;

    public DOMColumn(Element root){

        title = XmlDOM.getValue(root, NODE_TITLE);
        first = XmlDOM.getValue(root, NODE_FIRST);

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

        content = sb.toString();
    }
}
