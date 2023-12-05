package gunterbergg.advent_of_code.y2023.day2;

import java.util.Objects;

import static gunterbergg.advent_of_code.y2023.day2.GameData.CubeColor.getRoundColor;

public class MaxColorCubesGameRule extends GameRule {

  private final GameData.CubeColor color;
  private final Integer max;

  public MaxColorCubesGameRule(GameData.CubeColor color, Integer max) {
    Objects.requireNonNull(color, "color must not be null");
    Objects.requireNonNull(max, "max must not be null");
    this.color = color;
    this.max = max;
  }

  @Override
  boolean isValid(GameData gameData) {
    final var anyAboveMax = gameData
      .rounds()
      .stream()
      .anyMatch(r -> getRoundColor(color, r) > max);

    return !anyAboveMax;
  }
}
