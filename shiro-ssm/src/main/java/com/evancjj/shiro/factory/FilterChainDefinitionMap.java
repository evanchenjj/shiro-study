package com.evancjj.shiro.factory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 实例工厂方法建立权限关系，方便管理
 */
public class FilterChainDefinitionMap {
    public Map<String,String> getFilterChainDefinitionMapFactory(){
//        必须是LinkedHashMap，保证权限的顺序，底层就是LinkedHashMap实现的
        Map<String,String> filterMap= new LinkedHashMap<>();
//        通过数据库查询获取到权限数据，添加到map中并返回
//         List<权限数据对象> list = 通过service.get（）获取；
//        遍历list
//        调用权限数据对象的get方法放入map中

//        如/index.jsp=anon map.put("/index.jsp","anon")
        return filterMap;
    }
}
