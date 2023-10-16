package uk.ac.ebi.intact.style.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.ac.ebi.intact.style.mapper.booleans.*;
import uk.ac.ebi.intact.style.mapper.continuous.MIScoreMapper;
import uk.ac.ebi.intact.style.mapper.continuous.SummaryEdgeWidthMapper;
import uk.ac.ebi.intact.style.mapper.ontology.impl.InteractionTypeMapper;
import uk.ac.ebi.intact.style.mapper.ontology.impl.InteractorTypeMapper;
import uk.ac.ebi.intact.style.mapper.ontology.impl.TaxonMapper;
import uk.ac.ebi.intact.style.model.legend.NetworkEdgeLegend;
import uk.ac.ebi.intact.style.model.legend.NetworkLegend;
import uk.ac.ebi.intact.style.model.legend.NetworkNodeLegend;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class LegendBuilder {
    private final TaxonMapper taxonMapper;
    private final InteractorTypeMapper interactorTypeMapper;
    private final InteractionTypeMapper interactionTypeMapper;

    private final MIScoreMapper miScoreMapper;
    private final InteractionMutationColorMapper interactionMutationColorMapper;
    private final InteractionMutationWidthMapper interactionMutationWidthMapper;
    private final InteractorMutationMapper interactorMutationMapper;
    private final InteractorMutationBorderWidthMapper interactorMutationBorderWidthMapper;
    private final InteractionExpansionMapper interactionExpansionMapper;
    private final InteractionNegativeMapper interactionNegativeMapper;
    private final SummaryEdgeWidthMapper summaryEdgeWidthMapper;

    public NetworkLegend createLegend(Collection<String> taxIds, Collection<String> nodeTypes, boolean nodeMutated, Collection<String> edgeTypes, boolean edgeExpanded, boolean edgeAffectedByMutation, boolean negativeEdge) {
        NetworkLegend legend = new NetworkLegend();
        setupNodeLegend(legend.getNodeLegend(), taxIds, nodeTypes, nodeMutated);
        setupEdgeLegend(legend.getEdgeLegend(), edgeTypes, edgeExpanded, edgeAffectedByMutation, negativeEdge);
        return legend;
    }

    private void setupNodeLegend(NetworkNodeLegend legend, Collection<String> taxIds, Collection<String> nodeTypes, boolean nodeMutated) {
        taxonMapper.setupNodeLegend(legend, taxIds);
        legend.setShape(interactorTypeMapper.createLegend(nodeTypes));
        legend.setBorderColor(interactorMutationMapper.createLegend(nodeMutated));
        legend.setBorderWidth(interactorMutationBorderWidthMapper.createLegend(nodeMutated));
    }

    private void setupEdgeLegend(NetworkEdgeLegend legend, Collection<String> edgeTypes, boolean edgeExpanded, boolean edgeAffectedByMutation, boolean negativeEdge) {
        legend.setSummaryColors(miScoreMapper.createLegend());
        legend.setEvidenceColors(interactionTypeMapper.createLegend(edgeTypes));
        legend.setExpansion(interactionExpansionMapper.createLegend(edgeExpanded));
        legend.setNegative(interactionNegativeMapper.createLegend(negativeEdge));
        legend.setMutationColor(interactionMutationColorMapper.createLegend(edgeAffectedByMutation));
        legend.setMutationWidth(interactionMutationWidthMapper.createLegend(edgeAffectedByMutation));
        legend.setSummaryWidth(summaryEdgeWidthMapper.createLegend());
    }
}
