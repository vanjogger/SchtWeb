package com.scht.util;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;

import java.io.*;

/**
 * Created by Administrator on 2014/11/3.
 */
public class XmlUtil {
    /**
     * 格式化Document对象
     *
     * @param doc
     * @return
     */
    public static StringBuffer formatDocToStr(Document doc) {
        return formatDocToStr(doc, "GBK");
    }

    public static StringBuffer formatDocToStr(Document doc, String encoding) {
        // StringBuffer contentStr = new StringBuffer();
        try {
            XMLOutputter outputter = new XMLOutputter();
            Format format = Format.getPrettyFormat();
            format.setEncoding(encoding);
            format.setLineSeparator("\n");
            outputter.setFormat(format);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            outputter.output(doc, bos);
            //contentStr = outputter.outputString(doc);
            //contentStr = bos.toString();
            //contentStr = new StringBuffer(bos.toString());
            //contentStr.append(bos);
            return new StringBuffer(bos.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 创建xml文件
     *
     * @param doc
     * @param file
     * @return
     */
    public static boolean creatXmleFile(Document doc, File file) {
        try {
            Format format = Format.getCompactFormat();
            format.setIndent("	");
            format.setEncoding("GBK");
            XMLOutputter out = new XMLOutputter(format);

            out.output(doc, new FileOutputStream(file));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 转换xml文件为Doc
     */

    public static Document parseXmlFileToDoc(String file) {
        SAXBuilder builder = new SAXBuilder(false);
        try {
            Document doc = builder.build(file);
            return doc;
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换xml字符串为Document
     *
     * @param str
     * @return
     */
    public static Document parseStrToDoc(String str) {
        try {
            StringReader read = new StringReader(str);
            InputSource is = new InputSource(read);

            SAXBuilder builder = new SAXBuilder();
            return builder.build(is);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

   public static void main(String[] args){
       String file = "e:\\china.xml";


    }

}
