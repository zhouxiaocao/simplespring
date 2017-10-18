package com.springstudy.simplespring;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by BG276137 on 2017/9/25.
 */
public class TestIoc {

    @Test
    public void testIoc(){
        //1.创建 beanFactory
        BeanFactory beanFactory = new BeanFactory();

        //2. 注册bean
        BeanDefinition bd = new BeanDefinition();
        bd.setBeanClassName("com.springstudy.simplespring.HelloWorld");
        BeanDefinitionHolder bdh = new BeanDefinitionHolder("helloWorld", bd);
        beanFactory.registerBeanDefinition(bdh.getBeanName(),bdh.getBeanDefinition());

        //3. 获取bean
        HelloWorld helloWorld = (HelloWorld) beanFactory.getBean("helloWorld");
        Assert.assertEquals("Hello World!",helloWorld.sayHello());
    }

    @Test
    public void testIoCProperty() {
        // 1. 创建beanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2. 注册bean
        BeanDefinition bd = new BeanDefinition();
        bd.setBeanClassName("com.springstudy.simplespring.HelloWorld");

        // 注入Property
        PropertyValues pvs = new PropertyValues();
        pvs.addPropertyValue(new PropertyValue("msg", "Hello IoC Property!"));
        bd.setPropertyValues(pvs);

        BeanDefinitionHolder bdh = new BeanDefinitionHolder("helloWorld", bd);
        beanFactory.registerBeanDefinition(bdh.getBeanName(), bdh.getBeanDefinition());

        // 3. 获取bean
        HelloWorld hello = (HelloWorld) beanFactory.getBean("helloWorld");
        Assert.assertEquals("Hello IoC Property!", hello.sayHello());
    }
}
