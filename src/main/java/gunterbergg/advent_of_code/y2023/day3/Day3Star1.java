package gunterbergg.advent_of_code.y2023.day3;

import gunterbergg.advent_of_code.commons.AdventOfCodeTask;
import gunterbergg.advent_of_code.commons.AdventOfCodeStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Day3Star1 extends AdventOfCodeTask<Integer> {

  public static void main(String[] args) {
    final var inst = Day3Star1.of(args);
    inst.answer();
  }

  private final EngineSchematicParser parser;

  private Day3Star1(Collection<String> paths) {
    super(paths);
    parser = new EngineSchematicPartParser();
  }

  public static Day3Star1 of(String[] args) {
    final var paths = new ArrayList<>(List.of(args));
    paths.add("/inputs/day3");
    return new Day3Star1(paths);
  }

  @Override
  public Integer process(AdventOfCodeStream stream) {
    parser.parse(stream);
    return 1;
  }
}