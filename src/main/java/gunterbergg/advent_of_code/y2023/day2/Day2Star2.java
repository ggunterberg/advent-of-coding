package gunterbergg.advent_of_code.y2023.day2;

import gunterbergg.advent_of_code.commons.AdventOfCodeTask;
import gunterbergg.advent_of_code.commons.AdventOfCodeStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static gunterbergg.advent_of_code.y2023.day2.GameData.CubeColor.getRoundColor;

public class Day2Star2 extends AdventOfCodeTask<Integer> {

  public static void main(String[] args) {
    final var inst = Day2Star2.of(args);
    inst.answer();
  }

  private final GameParser parser;

  private Day2Star2(Collection<String> paths) {
    super(paths);
    this.parser = new DefaultGameParser();
  }

  public static Day2Star2 of(String[] args) {
    final var paths = new ArrayList<>(List.of(args));
    paths.add("/inputs/day2");
    return new Day2Star2(paths);
  }

  @Override
  public Integer process(AdventOfCodeStream stream) {
    return
      stream
        .produce((index, line) -> parser.parse(line))
        .mapToInt(games ->
          getColorMax(games, GameData.CubeColor.RED) *
            getColorMax(games, GameData.CubeColor.GREEN) *
            getColorMax(games, GameData.CubeColor.BLUE)
        )
        .sum();
  }

  private static int getColorMax(GameData games, GameData.CubeColor color) {
    return games.rounds()
      .stream()
      .mapToInt(round -> getRoundColor(color, round))
      .max()
      .getAsInt();
  }
}