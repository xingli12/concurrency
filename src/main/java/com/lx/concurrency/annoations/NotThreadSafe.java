package com.lx.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description 课程里用来标记【线程不安全】的类或者写法
 * projectName  concurrency
 * package com.lx.concurrency.annoations
 *
 * @author xingli12
 * @version v1
 * @date Created in 2018-09-05 10:34
 * modified By
 * updateDate
 */
//注解作用的目标
@Target(ElementType.TYPE)
//注解作用的范围
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {

    String value() default "";

}
