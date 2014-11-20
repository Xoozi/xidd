package com.galotec.xidd.datawrap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;
import org.jdom.JDOMException;

import android.content.Context;

public class DOMXiddData{

    public static final String NODE_DRAMA  = "drama";
    public static final String NODE_POSTER = "poster";
    public static final String NODE_COLUMN = "column";
    public static final String ATTR_DATA   = "data";

    public final List<DOMDrama>  dramaList = new ArrayList<DOMDrama>();
    public final List<DOMPoster> posterList= new ArrayList<DOMPoster>();
    public final List<DOMColumn> columnList= new ArrayList<DOMColumn>();

    private Map<String, DOMDrama> _dramaMap = new HashMap<String, DOMDrama>();

    public DOMXiddData(Context context, Element dramas, 
            Element posters, Element columns){
        List<Element> children = XmlDOM.getChildElements(dramas, NODE_DRAMA);
        for(Element e:children){
            String data = e.getAttributeValue(ATTR_DATA);
            DOMDrama drama;
            try {
                drama = AssetTool.loadDrama(context, data);
                dramaList.add(drama);
                _dramaMap.put(drama.id, drama);
            } catch (JDOMException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        children = XmlDOM.getChildElements(posters, NODE_POSTER);
        for(Element e:children){
            DOMPoster poster = new DOMPoster(e);
            posterList.add(poster); 
        }

        children = XmlDOM.getChildElements(columns, NODE_COLUMN);
        for(Element e:children){
            DOMColumn column = new DOMColumn(e);
            columnList.add(column);
        }
    }

    public DOMDrama queryDrama(String id){
        return _dramaMap.get(id);
    }

    public boolean hasDrama(String id){
        return _dramaMap.containsKey(id);
    }
}
