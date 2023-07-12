package uk.ac.ebi.intact.style.mapper.ontology.archetypes;

import uk.ac.ebi.intact.style.model.ontology.Term;

public interface Archetype<P> {
    String getId();
    String getName();
    P getVisualProperty();

    default Term toTerm() {
        return new Term(getId(), getName());
    }
}
