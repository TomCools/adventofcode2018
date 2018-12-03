package be.tomcools;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day3 {

    @Test
    public void day3PartOne() {
        Playfield field = new Playfield(1000,1000);

        getClaims().forEach(field::claimField);

        System.out.println("Amount of overlaps: " + field.overlaps);
        //field.print();
    }

    class Playfield {
        private int[][] field;
        private int overlaps = 0;

        public Playfield(int x, int y) {
            this.field = new int[x][y];
        }

        public void claimField(Claim claim) {
            for(int x = claim.x; x < claim.x + claim.width; x++) {
                for(int y = claim.y; y < claim.y + claim.height; y++) {
                    if(field[x][y] == -1) {
                        // Already duplicate
                    } else if(field[x][y] != 0) {
                        field[x][y] = -1;
                        overlaps++;
                    }
                    else {
                        field[x][y] = claim.number;
                    }
                }
            }
        }

        public void print() {
            for (int y = 0; y < field[0].length; y++) {
                for(int x = 0; x < field.length; x++) {
                    System.out.print(field[x][y]);
                }
                System.out.println();
            }

        }


    }

    static class Claim {
        private int number;
        private int x;
        private int y;
        private int width;
        private int height;

        public Claim(int number, int x, int y, int width, int height) {
            this.number = number;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public static Claim mapFrom(String s) {
            String[] split = s.split(" ");
            //example: #1 @ 35,93: 11x13
            int number = Integer.parseInt(split[0].replace("#", ""));
            String[] xy = split[2].split(",");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1].replace(":", ""));
            String[] wh = split[3].split("x");
            int w = Integer.parseInt(wh[0]);
            int h = Integer.parseInt(wh[1]);
            return new Claim(number,x,y,w,h);
        }
    }


    private List<Claim> getClaims() {
        try {
            return Files.lines(getInputPath())
                    .map(Claim::mapFrom)
                    .collect(toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getInputPath() {
        try {
            return Paths.get(Day3.class.getClassLoader()
                    .getResource("input.txt").toURI());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
