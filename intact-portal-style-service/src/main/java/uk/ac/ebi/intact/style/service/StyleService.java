package uk.ac.ebi.intact.style.service;

import org.springframework.stereotype.Service;
import uk.ac.ebi.intact.style.mapper.LegendBuilder;
import uk.ac.ebi.intact.style.mapper.booleans.*;
import uk.ac.ebi.intact.style.mapper.continuous.MIScoreMapper;
import uk.ac.ebi.intact.style.mapper.continuous.SummaryEdgeWidthMapper;
import uk.ac.ebi.intact.style.mapper.ontology.impl.InteractionTypeMapper;
import uk.ac.ebi.intact.style.mapper.ontology.impl.InteractorTypeMapper;
import uk.ac.ebi.intact.style.mapper.ontology.impl.MIOntology;
import uk.ac.ebi.intact.style.mapper.ontology.impl.TaxonMapper;
import uk.ac.ebi.intact.style.model.legend.NetworkLegend;
import uk.ac.ebi.intact.style.model.shapes.EdgeShape;
import uk.ac.ebi.intact.style.model.shapes.NodeShape;

import java.awt.*;
import java.util.Collection;

@Service
public class StyleService {
    private final TaxonMapper taxonMapper = new TaxonMapper();
    private final MIOntology miOntology = new MIOntology();
    private final InteractorTypeMapper interactorTypeMapper = new InteractorTypeMapper(miOntology);
    private final InteractorMutationMapper interactorMutationMapper = new InteractorMutationMapper();
    private final InteractorMutationBorderWidthMapper interactorMutationBorderWidthMapper = new InteractorMutationBorderWidthMapper();

    private final MIScoreMapper miScoreMapper = new MIScoreMapper();
    private final InteractionTypeMapper interactionTypeMapper = new InteractionTypeMapper(miOntology);
    private final InteractionMutationColorMapper interactionMutationColorMapper = new InteractionMutationColorMapper();
    private final InteractionMutationWidthMapper interactionMutationWidthMapper = new InteractionMutationWidthMapper();
    private final SummaryEdgeWidthMapper summaryEdgeWidthMapper = new SummaryEdgeWidthMapper();
    private final InteractionExpansionMapper interactionExpansionMapper = new InteractionExpansionMapper();

    private final LegendBuilder legendBuilder = new LegendBuilder(taxonMapper, interactorTypeMapper,
            interactionTypeMapper, miScoreMapper, interactionMutationColorMapper,
            interactionMutationWidthMapper, interactorMutationMapper, interactorMutationBorderWidthMapper,
            interactionExpansionMapper, summaryEdgeWidthMapper);

    /* Interactor styles */

    public Color getInteractorColor(String taxon) {
        return taxonMapper.getStyleOf(taxon);
    }

    public NodeShape getInteractorShape(String typeMIId) {
        return interactorTypeMapper.getStyleOf(typeMIId);
    }

    public Color getInteractorBorderColor(boolean mutated) {
        return interactorMutationMapper.getStyleOf(mutated);
    }

    public Integer getInteractorBorderWidth(boolean mutated) {
        return interactorMutationBorderWidthMapper.getStyleOf(mutated);
    }

    /* Interaction styles */

    public Color getSummaryInteractionColor(double miScore) {
        return miScoreMapper.getStyleOf(miScore);
    }

    public Color getInteractionColor(String typeMIId) {
        return interactionTypeMapper.getStyleOf(typeMIId);
    }

    public Color getMutationInteractionColor(boolean mutation) {
        return interactionMutationColorMapper.getStyleOf(mutation);
    }

    public Integer getMutationInteractionWidth(boolean mutation) {
        return interactionMutationWidthMapper.getStyleOf(mutation);
    }

    public Integer getSummaryInteractionWidth(int nbInteractions) {
        return summaryEdgeWidthMapper.getStyleOf(nbInteractions);
    }

    public EdgeShape getInteractionShape(boolean isExpanded) {
        return interactionExpansionMapper.getStyleOf(isExpanded);
    }

    /* Legend */

    public NetworkLegend createLegend(Collection<String> taxIds,
                                      Collection<String> nodeTypes,
                                      boolean nodeMutated,
                                      Collection<String> edgeTypes,
                                      boolean edgeExpanded,
                                      boolean edgeAffectedByMutation) {
        return legendBuilder.createLegend(taxIds, nodeTypes, nodeMutated, edgeTypes, edgeExpanded, edgeAffectedByMutation);
    }

    /* Updates */

    public void updateMIOntology() {
        miOntology.update();
        interactorTypeMapper.indexTree();
        interactionTypeMapper.indexTree();
    }

    public void updateTaxonomy() {
        taxonMapper.update();
    }
}
