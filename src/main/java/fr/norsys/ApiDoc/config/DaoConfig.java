package fr.norsys.ApiDoc.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class DaoConfig {
    @Bean
    @Primary
    public PropertiesFactoryBean sqlQueries() {
        PropertiesFactoryBean prop = new PropertiesFactoryBean();
        ClassPathResource[] resources = new ClassPathResource[]{
                new ClassPathResource("sql/user.properties"),

        };
        prop.setLocations(resources);
        prop.setIgnoreResourceNotFound(true);
        return prop;
    }

    @Bean
    public PropertiesFactoryBean docProperties() {
        PropertiesFactoryBean prop = new PropertiesFactoryBean();
        ClassPathResource[] resources = new ClassPathResource[]{
                new ClassPathResource("sql/document.properties"),
        };
        prop.setLocations(resources);
        prop.setIgnoreResourceNotFound(true);
        return prop;
    }

    @Bean
    public PropertiesFactoryBean autorisationProperties() {
        PropertiesFactoryBean prop = new PropertiesFactoryBean();
        ClassPathResource[] resources = new ClassPathResource[]{
                new ClassPathResource("sql/autorisation.properties"),
        };
        prop.setLocations(resources);
        prop.setIgnoreResourceNotFound(true);
        return prop;
    }
    @Bean
    public PropertiesFactoryBean metadataProperties() {
        PropertiesFactoryBean prop = new PropertiesFactoryBean();
        ClassPathResource[] resources = new ClassPathResource[]{
                new ClassPathResource("sql/metadata.properties"),
        };
        prop.setLocations(resources);
        prop.setIgnoreResourceNotFound(true);
        return prop;
    }


}
