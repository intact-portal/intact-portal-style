package uk.ac.ebi.intact.style.ws.controller.model.edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class InteractionEvidenceProperties {
    @ApiModelProperty(example = "MI:0403")
    @JsonProperty(value = "type_id")
    private String typeMIId;
    private boolean expanded;
    private boolean negative;

    public String getTypeMIId() {
        return typeMIId;
    }

    public void setTypeMIId(String typeMIId) {
        this.typeMIId = typeMIId;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isNegative() {
        return negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }
}
