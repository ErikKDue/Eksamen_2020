package annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StoreableAttribute {
    String tableName() default "";
    String rowName() default "";
}