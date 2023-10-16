package uk.ac.ebi.intact.style.mapper.ontology.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.ac.ebi.intact.style.conf.StyleProperties;
import uk.ac.ebi.intact.style.mapper.ontology.OBOParser;
import uk.ac.ebi.intact.style.mapper.ontology.archetypes.Archetype;
import uk.ac.ebi.intact.style.mapper.ontology.archetypes.InteractionType;
import uk.ac.ebi.intact.style.mapper.ontology.archetypes.InteractorType;
import uk.ac.ebi.intact.style.mapper.ontology.archetypes.Taxon;
import uk.ac.ebi.intact.style.model.ontology.Ontology;
import uk.ac.ebi.intact.style.model.ontology.OntologyFetcher;
import uk.ac.ebi.intact.style.model.ontology.Term;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MIOntology implements OntologyFetcher {

    private static final Log log = LogFactory.getLog(MIOntology.class);

    private Ontology ontology;

    private final ProxyManager proxyManager;
    private final StyleProperties properties;

    @Autowired
    public MIOntology(ProxyManager proxyManager, StyleProperties properties) {
        this.proxyManager = proxyManager;
        this.properties = properties;
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
            if (properties.getMi().isFullIndex()) {
                URL url = new URL("http://ontologies.berkeleybop.org/mi.obo");
                URLConnection urlConnection = url.openConnection(proxyManager.getProxy());
                log.info("Opening connection " + urlConnection.toString());
                log.info("Start indexing of mi.obo");
                ontology = OBOParser.parseOntology(urlConnection.getInputStream(), null);
                log.info("Finished indexing of mi.obo");
            } else {
                ontology = new Ontology();
                Term root = new Term("MI:0000", "molecular interaction");

                Term rootInteractor = new Term(InteractorType.getRootId(), "interactor type");
                rootInteractor.setChildren(Arrays.stream(InteractorType.values()).map(Archetype::toTerm).collect(Collectors.toList()));

                Term rootInteraction = new Term(InteractionType.getRootId(), "interaction type");
                rootInteraction.setChildren(Arrays.stream(InteractionType.values()).map(Archetype::toTerm).collect(Collectors.toList()));

                root.setChildren(List.of(rootInteractor, rootInteraction));

                ontology.setRoot(root);
                ontology.populateTerms();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}