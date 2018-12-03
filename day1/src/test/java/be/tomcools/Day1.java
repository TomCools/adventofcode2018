package be.tomcools;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day1 {

    @Test
    public void partOne() throws URISyntaxException, IOException {
        Path path = getInputPath();

        Files.lines(path)
                .map(Integer::parseInt)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);
    }

    @Test
    public void partTwo() throws URISyntaxException, IOException {
        Path path = getInputPath();

        Set<Integer> knownFrequencies = new HashSet<>();
        int freq = 0;
        int i = 0;
        List<Integer> list = Files.lines(path).map(Integer::parseInt).collect(Collectors.toList());
        do {
            knownFrequencies.add(freq);
            freq += list.get(i);
            i++;
            if(i >= list.size())
                i = 0;
        } while(!knownFrequencies.contains(freq));

        System.out.println("First duplicate Frequency:" + freq);
    }

    private Path getInputPath() {
        try {
            return Paths.get(Day1.class.getClassLoader()
                    .getResource("input.txt").toURI());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
