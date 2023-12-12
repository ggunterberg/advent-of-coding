package gunterbergg.advent_of_code.y2023.day2;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;

public class DefaultGameParser implements GameParser {

  private Integer getColor(GameData.CubeColor targetColor, List<String> infos) {
    if (targetColor == null || infos == null) return null;
    return infos
      .stream()
      .filter(info -> {
        final var splittedInfo = split(info, SPACE);
        GameData.CubeColor color = GameData.CubeColor.parse(splittedInfo[1]);
        if (color == null) return false;
        return targetColor.equals(color);

      })
      .findFirst()
      .map(info -> {
        final var splittedInfo = split(info, SPACE);
        return Integer.parseInt(splittedInfo[0]);
      }).orElse(0);
  }

  private List<GameData.GameRound> parseRounds(String unparsedGame) {
    final var gameRoundsUnparsed = split(unparsedGame, ";");
    return Arrays
      .stream(gameRoundsUnparsed)
      .map(unparsedRound -> {
        final var unparsedInfoList = Arrays.stream(split(unparsedRound, ",")).toList();
        return new GameData.GameRound(
          getColor(GameData.CubeColor.RED, unparsedInfoList),
          getColor(GameData.CubeColor.GREEN, unparsedInfoList),
          getColor(GameData.CubeColor.BLUE, unparsedInfoList)
        );
      })
      .toList();
  }

  private Integer parseGameId(String unparsedGame) {
    final var unparsedId = substringAfter(unparsedGame, "Game ");
    try {
      return Integer.parseInt(unparsedId);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public GameData parse(String input) {
    final var gameId = parseGameId(substringBefore(input, ":"));
    final var gameRounds = parseRounds(substringAfter(input, ":"));
    return new GameData(gameId, gameRounds);
  }
}
