package com.jakesmommy.zblrestapi.decorator;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsAuthorized {

    boolean isAuthorized() default false;

}
