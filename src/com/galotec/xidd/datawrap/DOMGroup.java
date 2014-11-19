package com.galotec.xidd.datawrap;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

public class DOMGroup{

    public static final String NODE_NAME = "name";
    public static final String NODE_IMG = "img";
    public static final String NODE_DES = "des";
    public static final String NODE_STAFFS = "staffs";
    public static final String NODE_STAFF = "staff";

    public final String name;
    public final String img;
    public final String des;
    public final List<DOMStaff>  staffList = new ArrayList<DOMStaff>();

    public DOMGroup(Element root){

        name = XmlDOM.getValue(root, NODE_NAME);
        img  = XmlDOM.getValue(root, NODE_IMG);
        des  = XmlDOM.getValue(root, NODE_DES);

        _loadStaffs(XmlDOM.getChildElement(root, NODE_STAFFS));
    }
    
    private void _loadStaffs(Element staffs){
        if(null == staffs)
           return; 
        List<Element> children = XmlDOM.getChildElements(staffs, NODE_STAFF);
        
        for(Element e:children){

            staffList.add(new DOMStaff(e));
        }
    }
}
