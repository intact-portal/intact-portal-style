package uk.ac.ebi.intact.style.ws.controller.model.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class InteractorProperties {
    @ApiModelProperty(example = "9606")
    @JsonProperty(value = "tax_id")
    private String taxId;
    @ApiModelProperty(example = "MI:0326")
    @JsonProperty(value = "type_id")
    private String typeMIId;
    private boolean mutated;

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getTypeMIId() {
        return typeMIId;
    }

    public void setTypeMIId(String typeMIId) {
        this.typeMIId = typeMIId;
    }

    public boolean isMutated() {
        return mutated;
    }

    public void setMutated(Boolean mutated) {
        this.mutated = mutated;
    }
}
