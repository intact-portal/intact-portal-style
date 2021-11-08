package uk.ac.ebi.intact.style.ws.controller.model.edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

public class InteractionProperties {
    @JsonProperty("nb_summarised_interactions")
    private int nbSummarisedInteractions;
    @JsonProperty("mi_score")
    private double miScore;
    @ApiModelProperty(example = "MI:0403")
    @JsonProperty(value = "type_id")
    private String typeMIId;
    private boolean expanded;
    private boolean mutated;
    private boolean negative;

    public int getNbSummarisedInteractions() {
        return nbSummarisedInteractions;
    }

    public void setNbSummarisedInteractions(int nbSummarisedInteractions) {
        this.nbSummarisedInteractions = nbSummarisedInteractions;
    }

    public double getMiScore() {
        return miScore;
    }

    public void setMiScore(double miScore) {
        this.miScore = miScore;
    }

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

    public boolean isMutated() {
        return mutated;
    }

    public void setMutated(boolean mutated) {
        this.mutated = mutated;
    }

    public boolean isNegative() {
        return negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }
}
