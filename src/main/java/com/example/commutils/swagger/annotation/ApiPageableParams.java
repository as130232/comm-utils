/**
 * ApiPageImplicitParams.java created 2018年9月19日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.example.commutils.swagger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * swagger-ui 分頁設定
 * 
 * @author brian
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
public @interface ApiPageableParams {

}
