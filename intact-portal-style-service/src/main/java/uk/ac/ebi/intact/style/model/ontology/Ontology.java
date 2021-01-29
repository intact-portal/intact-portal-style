package uk.ac.ebi.intact.style.model.ontology;

import java.util.HashMap;
import java.util.Map;

public class Ontology {
    private Map<String, Term> terms = new HashMap<>();
    private Term root;

    public Map<String, Term> getTerms() {
        return terms;
    }

    public Term getTerm(String id) {
        return terms.get(id);
    }

    public Term getRoot() {
        return root;
    }

    public void setRoot(Term root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return terms.toString();
    }
}
