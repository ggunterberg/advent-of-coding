package gunterbergg.advent_of_code.y2023.day2;

import gunterbergg.advent_of_code.commons.AdventOfCodeTask;
import gunterbergg.advent_of_code.commons.AdventOfCodeStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Day2Star1 extends AdventOfCodeTask<Integer> {

  public static void main(String[] args) {
    final var inst = Day2Star1.of(args);
    inst.answer();
  }

  private final GameParser parser;
  private final GameValidator validator;

  private Day2Star1(Collection<String> paths) {
    super(paths);
    this.parser = new DefaultGameParser();
    this.validator = new GameRulesValidator(
      Set.of(
        new MaxColorCubesGameRule(GameData.CubeColor.RED, 12),
        new MaxColorCubesGameRule(GameData.CubeColor.GREEN, 13),
        new MaxColorCubesGameRule(GameData.CubeColor.BLUE, 14)
      )
    );
  }

  public static Day2Star1 of(String[] args) {
    final var paths = new ArrayList<>(List.of(args));
    paths.add("/inputs/day2");
    return new Day2Star1(paths);
  }

  @Override
  public Integer process(AdventOfCodeStream stream) {
    return
      stream
        .produce((index, line) -> parser.parse(line))
        .filter(validator::validate)
        .mapToInt(GameData::id)
        .sum();
  }
}