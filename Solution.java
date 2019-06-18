import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;

public class Solution {
    public Collection collectAnagrams(String[] dictionary, String searchAnagramsFor) {
        Map<Integer, Integer> sample = new HashMap<>();
        searchAnagramsFor.chars().forEach(letter -> {
            int letterCount = sample.getOrDefault(letter, 0);
            letterCount++;
            sample.put(letter, letterCount);
        });
        int expectedSize = searchAnagramsFor.length();
        return Arrays
                .stream(dictionary)
                .filter(el -> el.length() == expectedSize)
                .filter(el -> isAnagram(el,sample))
                .collect(Collectors.toList());
    }

    private boolean isAnagram(String toCheck, Map<Integer, Integer> sample) {
        PrimitiveIterator.OfInt letterIterator = toCheck.chars().iterator();
        Map<Integer, Integer> current = new HashMap<>();
        while (letterIterator.hasNext()) {
            Integer letter = letterIterator.next();
            if(!sample.containsKey(letter)) {
                return false;
            }
            int letterCount = current.getOrDefault(letter, 0);
            letterCount++;
            current.put(letter, letterCount);
        }
        return sample.equals(current);
    }
}
