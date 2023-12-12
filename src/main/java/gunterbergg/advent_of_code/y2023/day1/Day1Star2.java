package gunterbergg.advent_of_code.y2023.day1;

import gunterbergg.advent_of_code.commons.AdventOfCodeTask;
import gunterbergg.advent_of_code.commons.AdventOfCodeStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Day1Star2 extends AdventOfCodeTask<Integer> {

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
  public Integer process(AdventOfCodeStream stream) {
    return stream
      .produce((index, line) -> decoder.decode(line))
      .mapToInt(Integer::valueOf)
      .sum();
  }
}