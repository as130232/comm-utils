/**
 * SwaggerDefaultConfiguation.java created 2018年10月18日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.example.commutils.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;

import com.example.commutils.swagger.annotation.ApiPageableParams;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

/**
 * @author brian
 *
 */
public abstract class SwaggerDefaultConfiguation {
   abstract public String packageName();

   @Bean
   public Docket machineServiceDomain() {
      // @formatter:off
       return new Docket(DocumentationType.SWAGGER_2)
                  .groupName("Default")
                  .forCodeGeneration(true)
                  .select()
                  .apis(Predicates.not(RequestHandlerSelectors.withMethodAnnotation(ApiPageableParams.class)))
                  .apis(Predicates.not(RequestHandlerSelectors.basePackage(packageName() + ".feign")))
                  .apis(RequestHandlerSelectors.basePackage(packageName())) //這裡改成你的api package路徑
                  .paths(PathSelectors.any())
                  .build()
                  .apiInfo(getApiInfo());
       // @formatter:on
   }

   /**
    * Pageable docket
    * 
    * @return
    */
   @Bean
   public Docket pageableServiceDomain() {
      // @formatter:off
       return new Docket(DocumentationType.SWAGGER_2)
                  .groupName("Pageable")
                  .forCodeGeneration(true)
                  .select()
                  .apis(RequestHandlerSelectors.withMethodAnnotation(ApiPageableParams.class))
                  .apis(Predicates.not(RequestHandlerSelectors.basePackage(packageName() + ".feign")))
                  .apis(RequestHandlerSelectors.basePackage(packageName())) //這裡改成你的api package路徑
                  .paths(PathSelectors.any())
                  .build()
                  .globalOperationParameters(operationParameters())
                  .apiInfo(getApiInfo());
       // @formatter:on
   }

   private List<Parameter> operationParameters() {
      List<Parameter> parameters = new ArrayList<Parameter>();
      // @formatter:off
      parameters.add(new ParameterBuilder()
               .name("page")
               .description("Results page you want to retrieve (0..N)")
               .modelRef(new ModelRef("integer"))
               .defaultValue("0")
               .parameterType("query")
               .required(false)
               .build());
      parameters.add(new ParameterBuilder()
               .name("size")
               .description("Number of records per page.")
               .modelRef(new ModelRef("integer"))
               .defaultValue("10")
               .parameterType("query")
               .required(false)
               .build());
      parameters.add(new ParameterBuilder()
               .name("sort")
               .description("Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
               .allowMultiple(true)
               .modelRef(new ModelRef("string"))
               .parameterType("query")
               .required(false)
               .build());   
      // @formatter:on
      return parameters;
   }

   @Bean
   public UiConfiguration uiConfig() { // 控制Swagger ui
      return UiConfigurationBuilder.builder()
      // @formatter:off
               .deepLinking(true)
               .displayOperationId(false)
               .defaultModelsExpandDepth(0)
               .defaultModelExpandDepth(1)
               .defaultModelRendering(ModelRendering.EXAMPLE)
               .displayRequestDuration(true)
               .docExpansion(DocExpansion.LIST)
               .filter(false)
               .maxDisplayedTags(null)
               .operationsSorter(OperationsSorter.METHOD)
               .showExtensions(false)
               .tagsSorter(TagsSorter.ALPHA)
               .validatorUrl(null)
               .build();
      // @formatter:on
   }

   abstract protected ApiInfo getApiInfo();
}
