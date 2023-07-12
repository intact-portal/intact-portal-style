package uk.ac.ebi.intact.style.mapper.ontology.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.ac.ebi.intact.style.mapper.ontology.AbstractOntologyMapper;
import uk.ac.ebi.intact.style.mapper.ontology.archetypes.InteractionType;
import uk.ac.ebi.intact.style.model.ontology.Term;

import java.awt.*;
@Component
public class InteractionTypeMapper extends AbstractOntologyMapper<InteractionType, Color> {

    private final MIOntology ontology;

    public InteractionTypeMapper(MIOntology ontology) {
        this.ontology = ontology;
        indexTree();
    }

    @Override
    protected Term getRootOfOntology() {
        return ontology.get().getTerm(InteractionType.getRootId());
    }

    @Override
    protected InteractionType matchArchetype(String id) {
        return InteractionType.getInteractionType(id);
    }

    @Override
    public InteractionType getDefaultArchetype() {
        return InteractionType.ASSOCIATION;
    }
}