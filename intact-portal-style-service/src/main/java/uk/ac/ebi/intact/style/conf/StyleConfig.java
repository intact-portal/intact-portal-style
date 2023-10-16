package uk.ac.ebi.intact.style.conf;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StyleProperties.class)
public class StyleConfig {
}
