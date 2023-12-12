package gunterbergg.advent_of_code.y2023.day1;

import gunterbergg.advent_of_code.y2023.commons.DayStar;
import gunterbergg.advent_of_code.y2023.commons.DayStarResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Day1Star1 extends DayStar {

  public static void main(String[] args) {
    final var inst = Day1Star1.of(args);
    inst.answer();
  }

  private final CalibrationDocumentDecoder decoder;

  private Day1Star1(Collection<String> paths) {
    super(paths);

    final var parsers = List.<CalibrationDocumentParser>of(new CalibrationTokenParser());
    this.decoder = new CalibrationDocumentDecoderImpl(parsers);
  }

  public static Day1Star1 of(String[] args) {
    final var paths = new ArrayList<>(List.of(args));
    paths.add("/inputs/day1");
    return new Day1Star1(paths);
  }

  @Override
  public Integer process(DayStarResource resource) {
    return resource
      .stream()
      .map(in -> in
        .produce((index, line) -> decoder.decode(line))
        .mapToInt(Integer::valueOf)
        .sum())
      .orElseThrow(() -> new IllegalArgumentException("Invalid path"));
  }
}