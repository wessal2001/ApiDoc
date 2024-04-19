package fr.norsys.ApiDoc.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Autorisez les requêtes CORS pour les URL correspondantes
                .allowedOrigins("http://localhost:4200") // Autorise les requêtes provenant de http://localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Autorisez les méthodes HTTP spécifiées
                .allowedHeaders("*"); // Autorisez tous les en-têtes HTTP
    }
}

