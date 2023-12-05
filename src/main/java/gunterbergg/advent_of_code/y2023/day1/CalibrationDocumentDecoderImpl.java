package gunterbergg.advent_of_code.y2023.day1;

import java.util.List;

public class CalibrationDocumentDecoderImpl implements CalibrationDocumentDecoder {

  private final List<CalibrationDocumentParser> parsers;

  public CalibrationDocumentDecoderImpl(List<CalibrationDocumentParser> parsers) {
    this.parsers = parsers;
  }

  @Override
  public Integer decode(String line) {
    final var tokens = parsers
      .stream()
      .parallel()
      .map(p -> p.parseTokens(line))
      .flatMap(List::stream)
      .sorted()
      .toList();

    final var result = getDigit(tokens.getFirst()) + getDigit(tokens.getLast());
    return Integer.parseInt(result);
  }

  private static String getDigit(TokenPosition tk){
    return tk.token().getIdentifier();
  }
}
