package uk.ac.ebi.intact.style.model.ontology;

public interface OntologyFetcher {
    void download();
    void update();
    void index();
}
