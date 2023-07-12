package uk.ac.ebi.intact.style.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor
public class StyleService {

    private final TaxonMapper taxonMapper;
    private final MIOntology miOntology;
    private final InteractorTypeMapper interactorTypeMapper;
    private final InteractorMutationMapper interactorMutationMapper;
    private final InteractorMutationBorderWidthMapper interactorMutationBorderWidthMapper;
    private final MIScoreMapper miScoreMapper;
    private final InteractionTypeMapper interactionTypeMapper;
    private final InteractionMutationColorMapper interactionMutationColorMapper;
    private final InteractionMutationWidthMapper interactionMutationWidthMapper;
    private final SummaryEdgeWidthMapper summaryEdgeWidthMapper;
    private final InteractionExpansionMapper interactionExpansionMapper;
    private final InteractionNegativeMapper interactionNegativeMapper;
    private final LegendBuilder legendBuilder;

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

    public Color getNegativeInteractionSymbolColor(boolean isNegative) {
        return interactionNegativeMapper.getStyleOf(isNegative);
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
                                      boolean edgeAffectedByMutation,
                                      boolean edgeNegative) {
        return legendBuilder.createLegend(taxIds, nodeTypes, nodeMutated, edgeTypes, edgeExpanded, edgeAffectedByMutation, edgeNegative);
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