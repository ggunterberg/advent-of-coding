package gunterbergg.advent_of_code.y2023.day3;

import gunterbergg.advent_of_code.y2023.commons.DefaultDayStarImpl;

import java.nio.file.Path;

public class Day3Star1 extends DefaultDayStarImpl {

  public static void main(String[] args) {
    final var inst = new Day3Star1();
    run(args, inst::process);
  }

  public void process(Path path) {
    final var parser = new DefaultEngineSchematicParser();
    read(path, br -> {
      final var engineSchematic = parser.parse(br);
      final var formattedResult = String.valueOf(engineSchematic.calculatePartNumbers());
      logger.info(formattedResult);
    });
  }
}