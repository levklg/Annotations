package myjunit;



import java.lang.annotation.*;


@Target({ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented


public @interface Before {
}