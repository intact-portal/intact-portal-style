package uk.ac.ebi.intact.style.ws.controller.model.edge;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InteractionMutationProperties {
    private boolean expanded;
    @JsonProperty("has_mutation")
    private boolean hasMutation;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean hasMutation() {
        return hasMutation;
    }

    public void setHasMutation(boolean hasMutation) {
        this.hasMutation = hasMutation;
    }
}
