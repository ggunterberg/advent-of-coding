package gunterbergg.advent_of_code.y2023.day1;

import gunterbergg.advent_of_code.y2023.commons.DefaultDayStarImpl;

import java.nio.file.Path;
import java.util.List;

public class Day1Star2 extends DefaultDayStarImpl {

  public static void main(String[] args) {
    final var inst = new Day1Star2();
    run(args, inst::process);
  }

  public void process(Path path) {
    final var parsers = List.of(new CalibrationTokenParser(), new CalibrationTokenAliasParser());
    final var calibrationDocumentDecoder = new CalibrationDocumentDecoderImpl(parsers);
    final var result = readReturn(path, br -> br
      .lines()
      .mapToInt(calibrationDocumentDecoder::decode)
      .sum());
    logger.info(String.valueOf(result));
  }
}