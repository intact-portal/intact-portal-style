package uk.ac.ebi.intact.style.mapper;


public interface Mapper<K, P> {
    P getStyleOf(K key);
}
