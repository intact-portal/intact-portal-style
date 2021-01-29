package uk.ac.ebi.intact.style.model.ontology;

import java.net.MalformedURLException;

public interface OntologyFetcher {
    void download();
    void update();
    void index();
}
