package gunterbergg.advent_of_code.y2023.day2;

import java.util.List;
import java.util.Objects;

public record GameData(Integer id, List<GameRound> rounds) {

  public GameData {
    Objects.requireNonNull(id);
    Objects.requireNonNull(rounds);
  }

  public record GameRound(Integer red, Integer green, Integer blue) {
  }

  public enum CubeColor {
    RED,
    GREEN,
    BLUE,
    ;

    public static Integer getRoundColor(GameData.CubeColor color, GameData.GameRound round) {
      return switch (color){
        case RED -> round.red();
        case GREEN -> round.green();
        case BLUE -> round.blue();
      };
    }

    public static CubeColor parse(String input) {
      for (CubeColor cubeColor : CubeColor.values()) {
        if (cubeColor.name().equalsIgnoreCase(input))
          return cubeColor;
      }
      return null;
    }
  }
}
