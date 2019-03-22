package aop.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lenovo on 2019-03-21.
 * Pointcut
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Pointcut {
    public String value() default "";
}
