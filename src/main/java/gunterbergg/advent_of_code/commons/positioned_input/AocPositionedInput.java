package gunterbergg.advent_of_code.commons.positioned_input;

import gunterbergg.advent_of_code.commons.AdventOfCodeStream;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AocPositionedInput {

  private record StreamPositionEntry(Position pos, String character) {
  }

  public static LinkedHashMap<Position, String> from(AdventOfCodeStream stream) {
    return stream
      .produce((rowIndex, line) ->
        line
          .codePoints()
          .mapToObj(Character::toString)
          .map(new Function<String, StreamPositionEntry>() {
            int colIndex = 0;

            @Override
            public StreamPositionEntry apply(String character) {
              return new StreamPositionEntry(pos(colIndex++, rowIndex), character);
            }
          }).toList()
      )
      .flatMap(List::stream)
      .collect(Collectors.toMap(
        entry -> entry.pos(),
        entry -> entry.character(),
        (str1, str2) -> str1,
        LinkedHashMap::new
      ));
  }
}