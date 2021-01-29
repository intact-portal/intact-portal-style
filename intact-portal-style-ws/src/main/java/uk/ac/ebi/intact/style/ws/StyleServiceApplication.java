package uk.ac.ebi.intact.style.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import uk.ac.ebi.intact.style.mapper.ontology.archetypes.Archetype;
import uk.ac.ebi.intact.style.model.serializer.ArchetypeSerializer;
import uk.ac.ebi.intact.style.model.serializer.ColorDeserializer;
import uk.ac.ebi.intact.style.model.serializer.ColorSerializer;

import java.awt.*;

@SpringBootApplication(scanBasePackages = "uk.ac.ebi.intact")
public class StyleServiceApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StyleServiceApplication.class);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonSerialization() {
        return mapperBuilder -> {
            mapperBuilder.deserializerByType(Color.class, new ColorDeserializer());
            mapperBuilder.serializerByType(Color.class, new ColorSerializer());
            mapperBuilder.serializerByType(Archetype.class, new ArchetypeSerializer());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(StyleServiceApplication.class, args);
    }
}
