package uk.ac.ebi.intact.style.mapper.ontology.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.ac.ebi.intact.style.conf.StyleProperties;
import uk.ac.ebi.intact.style.mapper.ontology.AbstractOntologyMapper;
import uk.ac.ebi.intact.style.mapper.ontology.OBOParser;
import uk.ac.ebi.intact.style.mapper.ontology.archetypes.Archetype;
import uk.ac.ebi.intact.style.mapper.ontology.archetypes.Taxon;
import uk.ac.ebi.intact.style.model.legend.NetworkNodeLegend;
import uk.ac.ebi.intact.style.model.ontology.Ontology;
import uk.ac.ebi.intact.style.model.ontology.OntologyFetcher;
import uk.ac.ebi.intact.style.model.ontology.Term;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TaxonMapper extends AbstractOntologyMapper<Taxon, Color> implements OntologyFetcher {

    private static final Log log = LogFactory.getLog(TaxonMapper.class);

    private final StyleProperties properties;

    private Ontology ontology;

    private final ProxyManager proxyManager;

    @Autowired
    public TaxonMapper(ProxyManager proxyManager, StyleProperties properties) {
        this.proxyManager = proxyManager;
        this.properties = properties;
        index();
        indexTree();
    }

    @Override
    protected Term getRootOfOntology() {
        return ontology.getRoot();
    }

    @Override
    protected Taxon matchArchetype(String key) {
        return Taxon.getTaxon(key);
    }

    @Override
    public Taxon getDefaultArchetype() {
        return Taxon.CHEMICAL_SYNTHESIS;
    }

    @Override
    @Deprecated
    public Map<String, Color> createLegend(Collection<String> facets) {
        return super.createLegend(facets);
    }

    public NetworkNodeLegend setupNodeLegend(NetworkNodeLegend legend, Collection<String> taxIds) {
        taxIds.stream()
                .map(this::getArchetype)
                .distinct()
                .sorted()
                .forEach(taxon -> {
                    if (taxon.isSpecies) legend.getSpeciesColor().put(taxon.getName(), taxon.getVisualProperty());
                    else legend.getKingdomColor().put(taxon.getName(), taxon.getVisualProperty());
                });
        return legend;
    }

    @Override
    public void update() {
        index();
        indexTree();
    }

    @Override
    public void index() {
        try {
            if (properties.getTaxonomy().isFullIndex()) {
                URL url = new URL("https://github.com/obophenotype/ncbitaxon/releases/latest/download/ncbitaxon.obo");
                URLConnection urlConnection = url.openConnection(proxyManager.getProxy());
                log.info("Opening connection " + urlConnection.toString());
                log.info("Start indexing of ncbitaxon.obo");
                ontology = OBOParser.parseOntology(urlConnection.getInputStream(), "NCBITaxon:");
                log.info("Finished indexing of ncbitaxon.obo");
            } else {
                ontology = new Ontology();
                Term root = new Term("1", "root");
                root.setChildren(
                        Arrays.stream(Taxon.values())
                                .map(Archetype::toTerm)
                                .collect(Collectors.toList())
                );
                ontology.setRoot(root);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}