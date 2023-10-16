package uk.ac.ebi.intact.style.mapper.ontology.impl;


import org.springframework.stereotype.Component;
import uk.ac.ebi.intact.style.mapper.ontology.AbstractOntologyMapper;
import uk.ac.ebi.intact.style.mapper.ontology.archetypes.InteractorType;
import uk.ac.ebi.intact.style.model.ontology.Term;
import uk.ac.ebi.intact.style.model.shapes.NodeShape;

@Component
public class InteractorTypeMapper extends AbstractOntologyMapper<InteractorType, NodeShape> {

    private final MIOntology ontology;

    public InteractorTypeMapper(MIOntology ontology) {
        this.ontology = ontology;
        indexTree();
    }

    @Override
    protected Term getRootOfOntology() {
        return ontology.get().getTerm(InteractorType.getRootId());
    }

    @Override
    protected InteractorType matchArchetype(String id) {
        return InteractorType.getInteractorType(id);
    }

    @Override
    public InteractorType getDefaultArchetype() {
        return InteractorType.PROTEIN;
    }
}