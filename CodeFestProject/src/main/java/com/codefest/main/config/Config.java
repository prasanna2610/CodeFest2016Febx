package com.codefest.main.config;  
  
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
  
@Configuration //Marks this class as configuration
//Specifies which package to scan
@ComponentScan("com.codefest.main")
//Enables Spring's annotations 
@EnableWebMvc   
public class Config extends WebMvcConfigurerAdapter{  
	static Logger log = Logger.getLogger(Config.class);
    @Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    	super.addResourceHandlers(registry);
    	log.info("mapping static resource");
    	registry.addResourceHandler("/index.jsp").addResourceLocations("/WEB-INF/views/index.html");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
    }
	
    @Bean  
    public UrlBasedViewResolver setupViewResolver() {
    	log.info("view resolver is mapped");
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();  
        resolver.setPrefix("/WEB-INF/views/");  
        resolver.setSuffix(".jsp");  
        resolver.setViewClass(JstlView.class);  
        return resolver;  
    }  
}  
