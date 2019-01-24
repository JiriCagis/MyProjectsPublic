package util.stemmer;

/**
 * Stemming is the term used in linguistic morphology and information retrieval 
 * to describe the process for reducing inflected (or sometimes derived) words 
 * to their word stem, base or root form.
 */
public interface Stemmer {
    /**
     * The Stemmer transforms a word into its root form.
     * Example: words fishing, fished transform to fish.
     * @param word for transform
     * @return transformed word 
     */
    String stem(String word);
}
