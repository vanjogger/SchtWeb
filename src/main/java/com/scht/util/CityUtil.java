package com.scht.util;

import com.scht.admin.bean.CityBean;
import org.jdom.Document;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class CityUtil {
    private static String file = CityUtil.class.getResource("/").getPath()+"china.xml";

    public static List<CityBean>  getAllCitys(){
        List<CityBean> list = new ArrayList<>();
        Document doc = XmlUtil.parseXmlFileToDoc(file);
        if(doc!=null){
            Element root = doc.getRootElement();
            if(root!=null){
                List<Element> provinceChild = root.getChildren();
                for(Element pe:provinceChild){
                    CityBean province = new CityBean();
                    province.setNo(pe.getAttributeValue("no"));
                    province.setName(pe.getAttributeValue("name"));
                    province.setLevel("1");
                    province.setPid("0");//ʡ���ϼ�Ϊ 0:����

                    List<CityBean> clist = new ArrayList<>();

                    List<Element> cityChild = pe.getChildren();
                    for(Element ce:cityChild){
                        CityBean city = new CityBean();
                        city.setNo(ce.getAttributeValue("no"));
                        city.setName(ce.getAttributeValue("name"));
                        city.setLevel("2");
                        city.setPid(province.getNo());

                        List<CityBean> arealist = new ArrayList<>();
                        List<Element> areaChild = ce.getChildren();
                        for(Element ae:areaChild){
                            CityBean area = new CityBean();
                            area.setNo(ae.getAttributeValue("no"));
                            area.setName(ae.getAttributeValue("name"));
                            area.setLevel("3");
                            area.setPid(city.getNo());

                            arealist.add(area);
                        }
                        city.setSublist(arealist);

                        clist.add(city);
                    }
                    province.setSublist(clist);
                    list.add(province);
                }
            }
        }
        return list;
    }

    public static List<CityBean>  getCitysByPNo(String no){
        List<CityBean> list = new ArrayList<>();
        Document doc = XmlUtil.parseXmlFileToDoc(file);
        if(doc!=null){
            Element root = doc.getRootElement();
            if(root!=null){
                List<Element> provinceChild = root.getChildren();
                for(Element pe:provinceChild){

                    if(pe.getAttributeValue("no").equals(no)){//ʡ��
                        List<Element> cityChild = pe.getChildren();
                        for(Element ce:cityChild) {
                            CityBean city = new CityBean();
                            city.setNo(ce.getAttributeValue("no"));
                            city.setName(ce.getAttributeValue("name"));
                            city.setLevel("2");
                            city.setPid(pe.getAttributeValue("no"));

                            list.add(city);

                        }

                    }else{
                        List<Element> cityChild = pe.getChildren();
                        for(Element ce:cityChild){
                            if(ce.getAttributeValue("no").equals(no)){
                                List<Element> areaChild = ce.getChildren();
                                for(Element ae:areaChild){
                                    CityBean area = new CityBean();
                                    area.setNo(ae.getAttributeValue("no"));
                                    area.setName(ae.getAttributeValue("name"));
                                    area.setLevel("3");
                                    area.setPid(ce.getAttributeValue("no"));

                                    list.add(area);
                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }



    public static List<CityBean>  getCitysByLevel(String level){
        List<CityBean> list = new ArrayList<>();
        Document doc = XmlUtil.parseXmlFileToDoc(file);
        if(doc!=null){
            Element root = doc.getRootElement();
            if(root!=null){
                List<Element> provinceChild = root.getChildren();
                for(Element pe:provinceChild){
                    CityBean province = new CityBean();
                    province.setNo(pe.getAttributeValue("no"));
                    province.setName(pe.getAttributeValue("name"));
                    province.setLevel("1");
                    province.setPid("0");//ʡ���ϼ�Ϊ 0:����
                    if(level.equals("1")){
                        list.add(province);
                    }

                    List<Element> cityChild = pe.getChildren();
                    for(Element ce:cityChild){
                        CityBean city = new CityBean();
                        city.setNo(ce.getAttributeValue("no"));
                        city.setName(ce.getAttributeValue("name"));
                        city.setLevel("2");
                        city.setPid(province.getNo());

                        if(level.equals("2")){
                            list.add(city);
                        }
                        List<Element> areaChild = ce.getChildren();
                        for(Element ae:areaChild){
                            CityBean area = new CityBean();
                            area.setNo(ae.getAttributeValue("no"));
                            area.setName(ae.getAttributeValue("name"));
                            area.setLevel("3");
                            area.setPid(city.getNo());

                            if(level.equals("3")){
                                list.add(city);
                            }
                        }

                    }
                }
            }
        }
        return list;
    }

    public static CityBean getCityNameByNo(String no){
        CityBean dto = null;
        Document doc = XmlUtil.parseXmlFileToDoc(file);
        if(doc!=null){
            Element root = doc.getRootElement();
            if(root!=null){
                List<Element> provinceChild = root.getChildren();
                for(Element pe:provinceChild){
                    if(pe.getAttributeValue("no").equals(no)){
                        dto = new CityBean();
                        dto.setNo(pe.getAttributeValue("no"));
                        dto.setName(pe.getAttributeValue("name"));
                        dto.setLevel("1");
                        dto.setPid("0");
                        break;
                    }

                    List<Element> cityChild = pe.getChildren();
                    for(Element ce:cityChild){
                        if(pe.getAttributeValue("no").equals(no)) {
                            dto = new CityBean();
                            dto.setNo(ce.getAttributeValue("no"));
                            dto.setName(ce.getAttributeValue("name"));
                            dto.setLevel("2");
                            dto.setPid(pe.getAttributeValue("no"));
                            break;
                        }

                        List<Element> areaChild = ce.getChildren();
                        for(Element ae:areaChild){
                            if(ae.getAttributeValue("no").equals(no)){
                                dto = new CityBean();
                                dto.setNo(ae.getAttributeValue("no"));
                                dto.setName(ae.getAttributeValue("name"));
                                dto.setLevel("3");
                                dto.setPid(ce.getAttributeValue("no"));
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dto;
    }


    public static void main(String[] args){
        System.out.println(file);
    }
}
