/**
 * 
 */
package carbook.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
 


@Component
public class ApplicationProperties {
	
	@Value("${spring.datasource.driver.classname}")
	private String driverClassname;
	
	@Value("${spring.datasource.url}")
	private String datasourceUrl;
	
	@Value("${spring.datasource.username}")
	private String datasourceUsername;
	
	@Value("${spring.datasource.password}")
	private String datasourcePassword;
	
	
	
	
//	@Value("${security.oauth2-endpoint}")
	//private String oauthEndpoint;
	
	//@Value("${security.jwt.client-id}")
	//private String clientId;
	
//	@Value("${security.jwt.client-secret}")
//	private String clientSecret;
	
	//public String getOauthEndpoint() {
	//	return oauthEndpoint;
	//}

//	public String getClientId() {
//		return clientId;
//	}

//	public String getClientSecret() {
//		return clientSecret;
//	}

	public String getDatasourceUrl() {
		return datasourceUrl;
	}

	public String getDatasourceUsername() {
		return datasourceUsername;
	}

	public String getDatasourcePassword() {
		return datasourcePassword;
	}

	public String getDriverClassname() {
		return driverClassname;
	}

}