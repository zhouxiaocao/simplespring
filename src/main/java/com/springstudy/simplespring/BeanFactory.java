package com.springstudy.simplespring;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BG276137 on 2017/9/21.
 */
public class BeanFactory {
    //用于存储 bean 的单例实体
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>();

    //用于存储beanDefinition
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    //注册BeanDeifinition
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    //获取bean,如果不存在，则创建
    public Object getBean(String beanName) {
        Object bean = singletonObjects.get(beanName);
        if (bean == null) {
            try {
                bean = doCreateBean(beanName, beanDefinitionMap.get(beanName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            singletonObjects.put(beanName, bean);
        }
        return bean;
    }

    private Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }

    private Object doCreateBean(final String beanName, final BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    private Object createBeanInstance(final BeanDefinition beanDefinition) throws Exception {
        Object bean = beanDefinition.getBeanClass().newInstance();
        return bean;
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        PropertyValues pvs = beanDefinition.getPropertyValues();
        if (pvs != null) {
            for (PropertyValue pv : pvs.getPropertyValueList()) {
                Field field = bean.getClass().getDeclaredField(pv.getName());
                field.setAccessible(true);
                field.set(bean, pv.getValue());
            }
        }
    }
}
