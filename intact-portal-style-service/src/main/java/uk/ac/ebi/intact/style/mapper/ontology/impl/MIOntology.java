package uk.ac.ebi.intact.style.mapper.ontology.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.ac.ebi.intact.style.mapper.ontology.OBOParser;
import uk.ac.ebi.intact.style.model.ontology.Ontology;
import uk.ac.ebi.intact.style.model.ontology.OntologyFetcher;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

@Component
public class MIOntology implements OntologyFetcher {

    private static final Log log = LogFactory.getLog(MIOntology.class);

    private Ontology ontology;

    private final ProxyManager proxyManager;

    @Autowired
    public MIOntology(ProxyManager proxyManager) {
        this.proxyManager = proxyManager;
        index();
    }

    public Ontology get() {
        return ontology;
    }

    @Override
    public void update() {
        index();
    }

    @Override
    public void index() {
        try {
            URL url = new URL("http://ontologies.berkeleybop.org/mi.obo");
            URLConnection urlConnection = url.openConnection(proxyManager.getProxy());
            log.info("Opening connection " + urlConnection.toString());
            log.info("Start indexing of mi.obo");
            ontology = OBOParser.parseOntology(urlConnection.getInputStream(), null);
            log.info("Finished indexing of mi.obo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}