package uk.ac.ebi.intact.style.ws.controller.model.edge;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InteractionSummaryProperties {
    @JsonProperty("nb_summarised_interactions")
    private int nbSummarisedInteractions;
    @JsonProperty("mi_score")
    private double miScore;

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
}
