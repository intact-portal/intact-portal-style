package uk.ac.ebi.intact.style.mapper.ontology.archetypes;

public interface Archetype<P> {
    String getId();
    String getName();
    P getVisualProperty();
}
