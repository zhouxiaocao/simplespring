package com.springstudy.simplespring;

/**
 * Created by BG276137 on 2017/9/21.
 */
public class BeanDefinitionHolder {
    private final BeanDefinition beandefinition;
    private final String beanName;

    public BeanDefinitionHolder(String beanName, BeanDefinition beandefinition) {
        this.beanName = beanName;
        this.beandefinition = beandefinition;
    }

    public BeanDefinition getBeanDefinition() {
        return beandefinition;
    }

    public String getBeanName() {
        return beanName;
    }
}
