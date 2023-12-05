package gunterbergg.advent_of_code.y2023.day2;

import java.util.Collection;
import java.util.function.Predicate;

public class GameRulesValidator implements GameValidator {

  private final Collection<GameRule> rules;

  public GameRulesValidator(Collection<GameRule> rules) {
    this.rules = rules;
  }

  @Override
  public boolean validate(GameData gameData) {
    final var anyMatch = rules
      .stream()
      .parallel()
      .anyMatch(
        Predicate.not(rule -> rule.isValid(gameData))
      );
    return !anyMatch;
  }
}
