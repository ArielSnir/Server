package com.yummynoodlebar.config;

import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(2)
public class WebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

  //{!begin addToRootContext}
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { SecurityConfig.class, PersistenceConfig.class, CoreConfig.class };
	}
  //{!end addToRootContext}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		return new Filter[] { characterEncodingFilter};
	}
	
	 /* private void configureSpringMvc(ServletContext servletContext, WebApplicationContext rootContext) {
		    AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		    mvcContext.register(MVCConfig.class);

		    mvcContext.setParent(rootContext);

		    ServletRegistration.Dynamic appServlet = servletContext.addServlet(
		        "webservice", new DispatcherServlet(mvcContext));
		    appServlet.setLoadOnStartup(1);
		    Set<String> mappingConflicts = appServlet.addMapping("/");

		    if (!mappingConflicts.isEmpty()) {
		      for (String s : mappingConflicts) {
		        LOG.error("Mapping conflict: " + s);
		      }
		      throw new IllegalStateException(
		          "'webservice' cannot be mapped to '/'");
		    }
		  }*/

}
