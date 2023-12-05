package gunterbergg.advent_of_code.y2023.day1;

import java.util.List;

public interface CalibrationDocumentParser {

  List<TokenPosition> parseTokens(String input);
}
