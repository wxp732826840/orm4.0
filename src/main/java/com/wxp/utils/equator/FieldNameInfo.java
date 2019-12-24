package com.wxp.utils.equator;


import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldNameInfo {
    String value() default "";
}
