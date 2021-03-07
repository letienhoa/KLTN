package carbook.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import carbook.service.CustomAccessDeniedHandler;
import carbook.service.JwtAuthenticationTokenFilter;
import carbook.service.RestAuthenticationEntryPoint;




@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
    jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
    return jwtAuthenticationTokenFilter;
  }
  @Bean
  public RestAuthenticationEntryPoint restServicesEntryPoint() {
    return new RestAuthenticationEntryPoint();
  }
  @Bean
  public CustomAccessDeniedHandler customAccessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }
  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
      return new WebMvcConfigurerAdapter() {
          @Override
          public void addCorsMappings(CorsRegistry registry) {
              registry.addMapping("/**").allowedOrigins("http://localhost:4200");
          }
      };
  }
  
  protected void configure(HttpSecurity http) throws Exception {
	  

	    
		  http.csrf().ignoringAntMatchers("/api/**");
		  
		   http.cors().and()
		   .antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
	      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
	      .antMatchers(HttpMethod.POST,"/api/ben/**").access("hasRole('ROLE_ADMIN')")
	      .antMatchers(HttpMethod.POST,"/api/tuyenxe/**").access("hasRole('ROLE_ADMIN')")
	      .antMatchers("/api/ben/get").access("hasRole('ROLE_USER')")
	      .antMatchers( "/api/tram-dung/get").access("hasRole('ROLE_USER')")
	      .antMatchers( "/api/khach-hang/logout").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	      .antMatchers( "/api/khach-hang/change-password").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	      .antMatchers( "/api/khach-hang/update/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	      //.antMatchers(HttpMethod.GET,"/api/khach-hang/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	      .antMatchers( "/api/khach-hang/test").access("hasRole('ROLE_USER')")
	      .antMatchers( "/api/ve/thong-ke-theo-khach-hang").access("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")
	      .antMatchers("/api/admin/**").access("hasRole('ROLE_ADMIN')")
	      .antMatchers("/api/xe/**").access("hasRole('ROLE_ADMIN')")
	      .antMatchers("/api/khach-hang/get-detail-point/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	      .antMatchers("/api/tuyenxe/list-tuyen-xe-theo-ve").access("hasRole('ROLE_ADMIN')")
	      .antMatchers("/api/excel/xuat-file").access("hasRole('ROLE_ADMIN')")
	      
	      .and()
	      .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
	      
	      .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
	    

	    
	    
	  }

}
