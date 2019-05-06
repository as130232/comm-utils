package com.example.commutils.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;

import com.example.commutils.error.customize.ValidateFailureException;

import io.swagger.annotations.ApiModelProperty;

/**
 * 檢查是否有效值
 * @author charles.chen
 * @date 2018年6月5日 下午3:04:53
 */
public class ValidateUtil {

   public interface Create {};
   public interface Read {};
   public interface Update {};
   public interface Delete {};

   /**
    *
    * @param obj
    * @param groups
    *
    * @author brian.huang
    * @date 2018年12月27日 下午5:19:23
    */
   public static void validateParams(Object obj, Class<?>... groups) {
//      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//      Validator validator = factory.getValidator();
//      Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj, groups);
//      if (constraintViolations.isEmpty() == false) {
//         for (ConstraintViolation<Object> cons : constraintViolations) {
//            String[] detail = { MSG.QUESTION_MARK.i18n, MSG.IS.i18n, MSG.NULL.i18n };
//            String[] data = { cons.getPropertyPath().toString() };
//            String log = cons.getPropertyPath().toString() + " " + cons.getMessage();
//            throw new ValidateFailureException(detail, data, log);
//         }
//      }
   }
   
	/**
	 * 檢查是否為空值
	 * @author charles.chen
	 * @date 2018年6月5日 下午3:08:38
	 * @param property 檢查的屬性或欄位
	 * @param value 檢查的屬性或欄位的值
	 * @throws ValidateFailureException
	 */
	public static void checkIsNull(String property, Object value) {
//		if(value == null || "".equals(value)) {
//			String[] detail = {MSG.QUESTION_MARK.i18n, MSG.IS.i18n, MSG.NULL.i18n};
//			String[] data = {property};
//			String log = property + "屬性是空值";
//			throw new ValidateFailureException(detail, data, log);
//		}
	}
	
	/**
	 * 檢查DTO及VO中所有屬性對應swagger設定的是否為非必要值
	 * 若@ApiModelProperty(required = true)，則必須給值，否則會拋出ValidateFailureException
	 * @author charles.chen
	 * @date 2018年7月6日 下午3:45:11
	 */
	public static void checkPropertiesIsNull(Object dtoOrVo) throws ValidateFailureException{
		Class<?> c = dtoOrVo.getClass();
		Field[] fields = c.getDeclaredFields();
		for(Field f : fields) {
			Annotation apiModelPropertyAnnotation = f.getAnnotation(ApiModelProperty.class);
			if(apiModelPropertyAnnotation == null) {
				continue;
			}
			
			ApiModelProperty apiModelProperty = (ApiModelProperty)apiModelPropertyAnnotation;
			boolean required = apiModelProperty.required();
			//若swagger設定是必要的屬性值，則檢查該屬性是否有值
			if(required) {
				try {
					
					String property = f.getName();
					PropertyDescriptor pd = new PropertyDescriptor(property, c);
					Method getMethod = pd.getReadMethod();	//取得get方法
					Object value = getMethod.invoke(dtoOrVo);	//取得該屬性的值
					
					//取得屬性的型態
					String propertyClass = f.getType().getSimpleName();
					propertyClass = propertyClass.toUpperCase().charAt(0) + propertyClass.substring(1);	//字首轉大寫
					//檢查該屬性的型態，若是基本型態則檢查是否有值，若非基本型態表示為物件還有其他物件，則在檢查該物件內所有屬性
					if("String".equals(propertyClass) || "Char".equals(propertyClass) || "Integer".equals(propertyClass) || "Int".equals(propertyClass)
							|| "Short".equals(propertyClass) || "Long".equals(propertyClass) || "BigDecimal".equals(propertyClass)
							|| "Boolean".equals(propertyClass) || "Byte".equals(propertyClass) || "Date".equals(propertyClass)
							){
						ValidateUtil.checkIsNull(property, value);
					}else {
						//該物件屬性有可能是集合
						if("List".equals(propertyClass) || "Set".equals(propertyClass) ) {
							for(Object obj : (Collection<?>)value){
								checkPropertiesIsNull(obj);
							}
						}else if("Map".equals(propertyClass)){
							//Map暫不檢查，待未來優化
						}else {
							//利用遞迴再檢查一次該物件內所有屬性
							checkPropertiesIsNull(value);
						}
					}
				} catch (IntrospectionException e) {
					e.printStackTrace();
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
