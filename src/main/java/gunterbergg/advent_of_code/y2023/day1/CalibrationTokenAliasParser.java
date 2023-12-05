package gunterbergg.advent_of_code.y2023.day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalibrationTokenAliasParser implements CalibrationDocumentParser {

  @Override
  public List<TokenPosition> parseTokens(String input) {
    return Arrays
      .stream(TokenAlias.values())
      .map(tka -> tokenAliasLookup(input, tka))
      .flatMap(List::stream)
      .toList();
  }

  private static List<TokenPosition> tokenAliasLookup(String str, TokenAlias tokenAlias) {
    final var tokenList = new ArrayList<TokenPosition>();
    var index = 0;
    do {
      final var tokenPos = str.indexOf(tokenAlias.getAlias());
      if (tokenPos == -1) break;
      tokenList.add(new TokenPosition(tokenAlias.getToken(), tokenPos));
      index = tokenPos + 1;
    } while (index <= str.length());
    return tokenList;
  }

}
