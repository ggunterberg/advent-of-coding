package gunterbergg.advent_of_code.y2023.day2;

import gunterbergg.advent_of_code.y2023.commons.DefaultDayStarImpl;

import java.nio.file.Path;
import java.util.Set;

import static gunterbergg.advent_of_code.y2023.day2.GameData.CubeColor.getRoundColor;

public class Day2Star2 extends DefaultDayStarImpl {

  public static void main(String[] args) {
    final var inst = new Day2Star2();
    run(args, inst::process);
  }

  public void process(Path path) {
    final var parser = new DefaultGameParser();
    final var result = readReturn(
      path, br -> br
        .lines()
        .map(parser::parse)
        .mapToInt(games ->
          getColorMax(games, GameData.CubeColor.RED) *
          getColorMax(games, GameData.CubeColor.GREEN) *
          getColorMax(games, GameData.CubeColor.BLUE)
        )
        .sum());

    final var formattedResult = String.valueOf(result);
    logger.info(formattedResult);
  }

  private static int getColorMax(GameData games, GameData.CubeColor color) {
    return games.rounds().stream().mapToInt(round -> getRoundColor(color, round)).max().getAsInt();
  }
}