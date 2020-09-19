package TeamTask.config;

//import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
//@ComponentScan(basePackages = "com.shalom")
//@PropertySource("classpath:persistence-mysql.properties")
public class Config {

	// properties variable
	@Autowired
	private Environment env;

	// logger
	private Logger logger = Logger.getLogger(getClass().getName());

	// ViewResolver
//	@Bean
//	public ViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//
//		viewResolver.setPrefix("WEB-INF/view/");
//		viewResolver.setSuffix(".jsp");
//
//		return viewResolver;
//	}

	@Bean(name = "mailSender")
	public MailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
//		javaMailSender.setProtocol("smtp");
		javaMailSender.setUsername("teamtaskcalendar@gmail.com");
		javaMailSender.setPassword("Damnjan1977");
		Properties props = javaMailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");
		return javaMailSender;
	}
	
	@Bean
     public MessageSource messageSource() {
     final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
     messageSource.setBasename("classpath:messages");
     messageSource.setUseCodeAsDefaultMessage(true);
     messageSource.setDefaultEncoding("UTF-8");
     messageSource.setCacheSeconds(0);
     return messageSource;
     }
}
