package uk.ac.ebi.intact.style.mapper.ontology.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.ac.ebi.intact.style.mapper.ontology.OBOParser;
import uk.ac.ebi.intact.style.model.ontology.Ontology;
import uk.ac.ebi.intact.style.model.ontology.OntologyFetcher;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MIOntology implements OntologyFetcher {
    private static final Log log = LogFactory.getLog(MIOntology.class);

    private Ontology ontology;

    public MIOntology() {
        index();
    }

    public Ontology get() {
        return ontology;
    }

    @Override
    public void download() {
        try {
            URL url = new URL("http://ontologies.berkeleybop.org/mi.obo");
            Path path = Paths.get(MIOntology.class.getResource("/").getPath(), "mi.obo");
            log.info("Start downloading of mi.obo to " + path.toAbsolutePath().toString());
            Files.copy(url.openStream(), path, StandardCopyOption.REPLACE_EXISTING);
            log.info("Finished downloading of mi.obo to " + path.toAbsolutePath().toString());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void update() {
        download();
        index();
    }

    @Override
    public void index() {
        try {
            InputStream inputStream = MIOntology.class.getResource("/mi.obo").openStream();
            String path = MIOntology.class.getResource("/mi.obo").getPath();
            log.info("Start indexing of mi.obo from" + path);
            ontology = OBOParser.parseOntology(inputStream, null);
            log.info("Finished indexing of mi.obo from" + path);
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (NullPointerException e) {
            update();
        }
    }
}
