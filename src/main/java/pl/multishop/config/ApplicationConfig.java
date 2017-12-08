package pl.multishop.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import pl.multishop.converter.RoleToUserProfileConverter;

/**
 * This is the main configuration class of Spring
 * @author Michal Stawarski
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.multishop")
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Autowired
    RoleToUserProfileConverter roleToUserProfileConverter;

    /**
     * This method configures the ViewResolver to deliver preferred views.
     * @return viewResolver
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * This method configures the MessageSource bean to lookup any validation/error message
     * in internationalized property files.
     * @return messageSource
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    /**
     * This method configures ResourceHandlers to serve static resources like CSS, JS, Img etc...
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/theme1/css/**").addResourceLocations("/resources/theme1/css/");
        registry.addResourceHandler("/resources/theme1/js/**").addResourceLocations("/resources/theme1/js/");
    }

    /**
     * This method configures Validator of content.
     * @return
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    /**
     * This method configures Converter interface to be used.
     * In our case, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserProfileConverter);
    }

    /**
     * This method is not necessary, but optional. It's only required when handling '.' in @PathVariables
     * which otherwise ignore everything after last '.' in @PathVaidables argument. It's a known bug in Spring,
     * and still present in Spring 4.3.12. This is a workaround for this issue.
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }

    /**
     * The methods of Apache Tiles configuration.
     *
     * @return
     */
    /*@Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[]{"/WEB-INF/layout/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }*/
}
