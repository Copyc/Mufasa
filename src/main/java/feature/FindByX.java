package feature;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;

@Retention(RetentionPolicy.RUNTIME)
@Target({
		java.lang.annotation.ElementType.FIELD,
		java.lang.annotation.ElementType.TYPE })
public @interface FindByX {
	How how() default How.UNSET;
	
/*	WebElement parent;*/

	String using() default "";

	String id() default "";

	String name() default "";

	String className() default "";

	String css() default "";

	String tagName() default "";

	String linkText() default "";

	String partialLinkText() default "";

	String xpath() default "";
}