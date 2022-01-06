package env03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class ApplicationConfig {
	
	@Value("${admin.id}")
	private String adminId;
	@Value("${admin.pw}")
	private String adminPw;
	@Value("${sub_admin.id}")
	private String sub_adminId;
	@Value("${sub_admin.pw}")
	private String sub_adminPw;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer Properties() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		//PropertySourcesPlaceholderConfigurer -> 환경파일 잡을때 설정
		
		System.out.println("2. Properties Run");
		Resource[] locations = new Resource[2];
		locations[0] = new ClassPathResource("admin3.properties");
		locations[1] = new ClassPathResource("sub_admin3.properties");
		//System.out.println("PropertySourcesPlaceholderConfigurer1 adminId->" + adminId);
		configurer.setLocations(locations);		
		return configurer;
	}
	
	@Bean
	public AdminConnection adminConfig() {
		AdminConnection adminConnection = new AdminConnection();
		System.out.println("3. adminConfig Run");
		adminConnection.setAdminId(adminId);
		adminConnection.setAdminPw(adminPw);
		adminConnection.setSub_adminId(sub_adminId);
		adminConnection.setSub_adminPw(sub_adminPw);
		return adminConnection;
	}
	
}
