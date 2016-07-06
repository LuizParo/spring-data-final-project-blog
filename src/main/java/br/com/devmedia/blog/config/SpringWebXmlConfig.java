package br.com.devmedia.blog.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebXmlConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContex) throws ServletException {
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(SpringMvcConfig.class);
        webContext.setServletContext(servletContex);
        
        servletContex.addListener(new ContextLoaderListener(webContext));
        
        DispatcherServlet dispatcher = new DispatcherServlet(webContext);
        dispatcher.setThrowExceptionIfNoHandlerFound(true);
        
        ServletRegistration.Dynamic dynamicRegistration = servletContex.addServlet("dispatcher", dispatcher);
        dynamicRegistration.setLoadOnStartup(1);
        dynamicRegistration.addMapping("/");

        FilterRegistration.Dynamic encodingFilter = servletContex.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
        
        FilterRegistration.Dynamic inviewSession = servletContex.addFilter("String OpenEntityManagerInViewFilter", new OpenEntityManagerInViewFilter());
        inviewSession.setAsyncSupported(true);
        inviewSession.addMappingForUrlPatterns(null, true, "/*");
    }
}