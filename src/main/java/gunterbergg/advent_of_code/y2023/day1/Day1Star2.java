package gunterbergg.advent_of_code.y2023.day1;

import gunterbergg.advent_of_code.y2023.commons.DayStar;
import gunterbergg.advent_of_code.y2023.commons.DayStarResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Day1Star2 extends DayStar {

  public static void main(String[] args) {
    final var inst = Day1Star2.of(args);
    inst.answer();
  }

  private final CalibrationDocumentDecoder decoder;

  private Day1Star2(Collection<String> paths) {
    super(paths);

    final var parsers = List.of(new CalibrationTokenParser(), new CalibrationTokenAliasParser());
    this.decoder = new CalibrationDocumentDecoderImpl(parsers);
  }

  public static Day1Star2 of(String[] args) {
    final var paths = new ArrayList<>(List.of(args));
    paths.add("/inputs/day1");
    return new Day1Star2(paths);
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