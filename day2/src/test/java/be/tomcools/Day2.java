package be.tomcools;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day2 {

    @Test
    public void day2PartOne() throws IOException {
        Counter twos = new Counter();
        Counter threes = new Counter();

        Files.lines(getInputPath())
                .map(this::countOccurences)
                .map(Map::values)
                .forEach(v -> {
                    if(v.contains(2)) {
                        twos.add();
                    }
                    if(v.contains(3)) {
                        threes.add();
                    }
                });

        System.out.println(twos.getCount() * threes.getCount());
    }

    @Test
    public void day2PartTwo() throws IOException {
        List<String> strings = Files.lines(getInputPath()).collect(Collectors.toList());
        for(int i = 0; i < strings.size(); i++) {
            String testString = strings.get(i);
            for(int y = i+1; y < strings.size(); y++) {
                String compareString = strings.get(y);
                String stringWithOnlyMatches = removeNonMatching(testString,compareString );
                if(stringWithOnlyMatches.length() == testString.length() - 1) {
                    System.out.println(stringWithOnlyMatches);
                }
            }
        }
    }

    private String removeNonMatching(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars1.length; i++) {
            if(chars1[i] == chars2[i]) {
                builder.append(chars1[i]);
            }
        }
        return builder.toString();
    }

    class Counter {
        int counter = 0;

        public void add() {
            counter++;
        }

        public int getCount() {
            return counter;
        }
    }

    private Map<Character, Integer> countOccurences(String string) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : string.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        return map;
    }

    private Path getInputPath() {
        try {
            return Paths.get(Day2.class.getClassLoader()
                    .getResource("input.txt").toURI());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
