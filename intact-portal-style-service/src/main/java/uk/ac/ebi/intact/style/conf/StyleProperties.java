package uk.ac.ebi.intact.style.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "intact.style")
@Getter
@Setter
public class StyleProperties {
    private final Taxonomy taxonomy = new Taxonomy();
    private final MI mi = new MI();
    @Getter
    @Setter
    public static class Taxonomy {
        /**
         * true  => Index the complete ontology <br>
         * false => Index only the archetypes
         */
        private boolean fullIndex = true;
    }
    @Getter
    @Setter
    public static class MI {
        /**
         * true  => Index the complete ontology <br>
         * false => Index only the archetypes
         */
        private boolean fullIndex = true;
    }

}
