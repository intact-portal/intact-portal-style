package uk.ac.ebi.intact.style.ws.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class NetworkStats {
    @JsonProperty("tax_ids")
    private Collection<String> taxIds;
    @JsonProperty("interactor_type_ids")
    private Collection<String> nodeTypes;
    @JsonProperty("has_interactor_mutated")
    private boolean nodeMutated;
    @JsonProperty("interaction_type_ids")
    private Collection<String> edgeTypes;
    @JsonProperty("has_edge_expanded")
    private boolean edgeExpanded;
    @JsonProperty("has_edge_affected_by_mutation")
    private boolean edgeAffectedByMutation;

    public Collection<String> getTaxIds() {
        return taxIds;
    }

    public void setTaxIds(Collection<String> taxIds) {
        this.taxIds = taxIds;
    }

    public Collection<String> getNodeTypes() {
        return nodeTypes;
    }

    public void setNodeTypes(Collection<String> nodeTypes) {
        this.nodeTypes = nodeTypes;
    }

    public boolean isNodeMutated() {
        return nodeMutated;
    }

    public void setNodeMutated(boolean nodeMutated) {
        this.nodeMutated = nodeMutated;
    }

    public Collection<String> getEdgeTypes() {
        return edgeTypes;
    }

    public void setEdgeTypes(Collection<String> edgeTypes) {
        this.edgeTypes = edgeTypes;
    }

    public boolean isEdgeExpanded() {
        return edgeExpanded;
    }

    public void setEdgeExpanded(boolean edgeExpanded) {
        this.edgeExpanded = edgeExpanded;
    }

    public boolean isEdgeAffectedByMutation() {
        return edgeAffectedByMutation;
    }

    public void setEdgeAffectedByMutation(boolean edgeAffectedByMutation) {
        this.edgeAffectedByMutation = edgeAffectedByMutation;
    }
}
