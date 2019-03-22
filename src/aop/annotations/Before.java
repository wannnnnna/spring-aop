package aop.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lenovo on 2019-03-21.
 * Before
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Before {
    public String value() default "";
}
