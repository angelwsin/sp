package com.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.spring.aop.AopTest;
import com.spring.event.DefEvent;
import com.tx.UserService;

public class Test {
	
	
	
	
	
	public static void main(String[] args) throws Exception{
	    
	    /**
	     * bean
	     * 
	     *  1.GenericBeanDefinition bd = new GenericBeanDefinition();
	     *   设置beanClass
	     *   
	     *  2.AbstractBeanDefinition parseBeanDefinitionAttributes(ele, beanName, containingBean, bd)
	     *   解析属性 lazy-init,destroy-method,init-method
	     *  3.parseMetaElements(ele, bd);
	     *    解析 key  value
	     *  4.parseLookupOverrideSubElements(ele, bd.getMethodOverrides())
	     *    解析 lookup-method
	     *  5.parseReplacedMethodSubElements(ele, bd.getMethodOverrides())
	     *    解析 replaced-method
	     *  6.parseConstructorArgElements(ele, bd)
	     *    解析 constructor-arg
	     *  7.parsePropertyElements(ele, bd)
	     *    解析 property
	     *  8.parseQualifierElements(ele, bd)
	     *    qualifier
	     *  9.封装成 BeanDefinitionHolder(beanDefinition, beanName, aliasesArray)
	     *  
	     *  10.注册 registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition())
	     *
	     */   
		
		    ClassPathXmlApplicationContext context  = new ClassPathXmlApplicationContext("user-test.xml");
		  /* User user =  (User) context.getBean("zhangsan");
		  
		   System.out.println(user.getEmail()+":"+user.getUserName());*/
		   
		   ClassPathResource resource = new ClassPathResource("user-test.xml");
		   DefaultListableBeanFactory  factory = new DefaultListableBeanFactory();
		   XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		   reader.loadBeanDefinitions(resource);
		   
		   DocumentBuilderFactory fa = DocumentBuilderFactory.newInstance();
		   DocumentBuilder docBuilder = fa.newDocumentBuilder();
		   Document doc =  docBuilder.parse(resource.getInputStream());
		   Element root = doc.getDocumentElement();
		   NodeList nl = root.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				System.out.println(node.getNodeName());
				if (node instanceof Element) {
					Element ele = (Element) node;
					Node x = ele;
					System.out.println(x.getNamespaceURI());
					NamedNodeMap attributes = ele.getAttributes();
					for (int j = 0; j < attributes.getLength(); j++) {
						Node nodej = attributes.item(j);
						System.out.println(nodej.getNodeName()+"-----"+ele.getNamespaceURI());
					}
				}
			}
			
			//public event
			ApplicationEvent event = new DefEvent("");
			context.publishEvent(event);
			AopTest ap = (AopTest) context.getBean("aopTest");
			ap.say();
			//context.getBean("user");
		
			/*DataSource dataSource = (DataSource) context.getBean("dataSource");
			JdbcTemplate jdbc = new JdbcTemplate();
			jdbc.setDataSource(dataSource);
			
			jdbc.execute(new ConnectionCallback<Integer>() {

                public Integer doInConnection(Connection con) throws SQLException,
                                                         DataAccessException {
                   // con.setAutoCommit(false);
                    Statement st = con.createStatement();
                    st.execute("insert into user(id,user_Name) values(9,'zhangsan')");
                    
                    
                  //  con.commit();
                   // throw new SQLException("操作错误");
                   return null;
                }
            });*/
			
		  UserService userService = 	(UserService) context.getBean("userService");
		  userService.getUser();
			
	}

}
