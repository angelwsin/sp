package com.spring.conf.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class SpringXmlParse {
    
    private Class<?> documentReaderClass = DefaultBeanDefinitionDocumentReader.class;
    
    public static void main(String[] args)throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
      //  docBuilder.parse(is)
    }
    
    
    public void    parseXml(InputStream is)throws ParserConfigurationException,SAXException,IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc =   docBuilder.parse(is);
        DefaultBeanDefinitionDocumentReader reader =
                (DefaultBeanDefinitionDocumentReader) BeanUtils.instantiateClass(this.documentReaderClass);
        Element root = doc.getDocumentElement();
       // reader.registerBeanDefinitions(doc, readerContext);
       
       
    }
    
    

}
