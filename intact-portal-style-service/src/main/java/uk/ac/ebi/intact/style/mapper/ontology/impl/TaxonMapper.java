package uk.ac.ebi.intact.style.mapper.ontology.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.ac.ebi.intact.style.mapper.ontology.AbstractOntologyMapper;
import uk.ac.ebi.intact.style.mapper.ontology.archetypes.Taxon;
import uk.ac.ebi.intact.style.mapper.ontology.OBOParser;
import uk.ac.ebi.intact.style.model.ontology.Ontology;
import uk.ac.ebi.intact.style.model.ontology.OntologyFetcher;
import uk.ac.ebi.intact.style.model.ontology.Term;
import uk.ac.ebi.intact.style.model.legend.NetworkNodeLegend;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Map;

public class TaxonMapper extends AbstractOntologyMapper<Taxon, Color> implements OntologyFetcher {
    private static final Log log = LogFactory.getLog(TaxonMapper.class);
    private Ontology ontology;

    public TaxonMapper() {
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
    public void download() {
        try {
            URL url = new URL("http://purl.obolibrary.org/obo/ncbitaxon.obo"); // We cannot use the slim subset as it lacks 500 of our taxIds
            Path path = Paths.get(TaxonMapper.class.getResource("/").getPath(), "ncbitaxon.obo");
            log.info("Start downloading of ncbitaxon.obo to " + path.toAbsolutePath().toString());
            Files.copy(url.openStream(), path, StandardCopyOption.REPLACE_EXISTING);
            log.info("Finished downloading of ncbitaxon.obo to " + path.toAbsolutePath().toString());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void update() {
        download();
        index();
        indexTree();
    }

    @Override
    public void index() {
        try {
            InputStream inputStream = TaxonMapper.class.getResource("/ncbitaxon.obo").openStream();
            String path = TaxonMapper.class.getResource("/ncbitaxon.obo").getPath();
            log.info("Start indexing of ncbitaxon.obo from " + path);
            ontology = OBOParser.parseOntology(inputStream, "NCBITaxon:");
            log.info("Finished indexing of ncbitaxon.obo from " + path);
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (NullPointerException e) {
            update();
        }
    }
}
