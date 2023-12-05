package gunterbergg.advent_of_code.y2023.day2;

import gunterbergg.advent_of_code.y2023.commons.DefaultDayStarImpl;

import java.nio.file.Path;
import java.util.Set;

public class Day2Star1 extends DefaultDayStarImpl {

  public static void main(String[] args) {
    final var inst = new Day2Star1();
    run(args, inst::process);
  }

  public void process(Path path) {
    final var parser = new DefaultGameParser();
    final var validator = new GameRulesValidator(
      Set.of(
        new MaxColorCubesGameRule(GameData.CubeColor.RED, 12),
        new MaxColorCubesGameRule(GameData.CubeColor.GREEN, 13),
        new MaxColorCubesGameRule(GameData.CubeColor.BLUE, 14)
      )
    );

    final var result = readReturn(
      path, br -> br
        .lines()
        .map(parser::parse)
        .filter(validator::validate)
        .mapToInt(GameData::id)
        .sum());

    final var formattedResult = String.valueOf(result);
    logger.info(formattedResult);
  }
}