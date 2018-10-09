package com.recyan.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Yan_Jiang on 2018/10/8.
 * 线程标注帮助类
 */

/**
 * 标注类或写法是[线程非安全]的
 */
@Target(ElementType.TYPE) //注解作用目标 -- ElementType.TYPE 给类加上注释
@Retention(RetentionPolicy.SOURCE) //存在范围
public @interface NotThreadSafe {

    String value() default ""; //设置默认值
}
