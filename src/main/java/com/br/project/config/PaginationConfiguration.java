//package com.wms.api.config;
//
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.ConversionService;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.data.web.config.SpringDataWebConfiguration;
//
//@Configuration
//public class PaginationConfiguration extends SpringDataWebConfiguration {
//
//    /**
//     * @param context           must not be {@literal null}.
//     * @param conversionService must not be {@literal null}.
//     */
//    public PaginationConfiguration(ApplicationContext context,
//                                   @Qualifier("mvcConversionService") ObjectFactory<ConversionService> conversionService) {
//        super(context, conversionService);
//    }
//
//    @Bean
//    public PageableHandlerMethodArgumentResolver pageableResolver() {
//        PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver =
//                new PageableHandlerMethodArgumentResolver(sortResolver());
//
//        pageableHandlerMethodArgumentResolver.setMaxPageSize(Integer.MAX_VALUE);
//
//        return pageableHandlerMethodArgumentResolver;
//    }
//
//}


