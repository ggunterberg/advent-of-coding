package gunterbergg.advent_of_code.y2023.day1;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class CalibrationTokenParser implements CalibrationDocumentParser {

  @Override
  public List<TokenPosition> parseTokens(String input) {
    return IntStream
      .range(0, input.length())
      .mapToObj(index -> {
        final var currChar = Character.toString(input.codePointAt(index));
        final var currToken = Token.parse(currChar);
        return Optional.ofNullable(currToken != null ? new TokenPosition(currToken, index) : null);
      })
      .flatMap(Optional::stream)
      .toList();
  }
}
