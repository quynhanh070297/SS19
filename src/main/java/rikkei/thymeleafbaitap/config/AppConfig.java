package rikkei.thymeleafbaitap.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "rikkei")
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware
{
    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("utf-8");
        return templateResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("utf-8");
        return resolver;
    }

    @Bean
    public DataSource getDateSource(){
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/EmployeeThymeLeaf");
        source.setUsername("root");
        source.setPassword("Ilove12a");
        return source;
    }

    @Bean
    public SessionFactory getSessionFactory(){
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(getDateSource());
        sf.setPackagesToScan("rikkei.thymeleafbaitap.entity");
        Properties props = new Properties();
        props.put("hibernate.show_sql",true);
        props.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        sf.setHibernateProperties(props);
        try {
            sf.afterPropertiesSet();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sf.getObject();
    }
}
