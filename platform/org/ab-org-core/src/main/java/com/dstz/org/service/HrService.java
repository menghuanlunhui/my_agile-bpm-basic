package com.dstz.org.service;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenyun
 * @projectName springboot
 * @description: TODO
 * @date 2019/10/9 10:46
 */
@Service
public class HrService {

    public List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();


    public  List<Map<String,Object>> parse(String soap) throws DocumentException{
        Document doc = DocumentHelper.parseText(soap);//报文转成doc对象
        Element root = doc.getRootElement();//获取根元素，准备递归解析这个XML树
        getCode(root);
        return mapList;
    }

    public  void getCode(Element root){
        boolean flag =false;
        Map<String, Object> map1 = new HashMap<String, Object>();
        if (root.elements() != null) {
            List<Element> list = root.elements();//如果当前跟节点有子节点，找到子节点
            for (Element e : list) {//遍历每个节点
                if (e.elements().size() > 0) {
                    getCode(e);//当前节点不为空的话，递归遍历子节点；
                }
                if (e.elements().size() == 0) {
                    flag = true;
                    map1.put(e.getName(), e.getTextTrim());
                }//如果为叶子节点，那么直接把名字和值放入map
            }
        }
        if (flag) {
            mapList.add(map1);
            flag = false;
        }
    }
}
