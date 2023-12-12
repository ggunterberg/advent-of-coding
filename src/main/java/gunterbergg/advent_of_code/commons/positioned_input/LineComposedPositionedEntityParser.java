package gunterbergg.advent_of_code.commons.positioned_input;

import java.util.Map;
import java.util.Optional;

public abstract class LineComposedPositionedEntityParser<T> {

  private StringBuilder sb = new StringBuilder();
  private Position currPosition = null;
  private T currentResult = null;

  public abstract Optional<T> parse(String chars, Position position);

  public Optional<T> map(Map.Entry<Position, String> entry) {
    final var character = entry.getValue();
    final var position = entry.getKey();
    return this.next(character, position);
  }

  protected Optional<T> next(String chars, Position position) {
    final var parseResult = parse(chars, position);

    if (parseResult.isEmpty())
      return close();

    currentResult = parseResult.get();
    sb.append(chars);
    return Optional.empty();
  }

  protected Optional<T> close() {
    try {
      if (sb.isEmpty())
        return Optional.empty();

      final var result = currentResult;
      return Optional.ofNullable(result);

    } catch (NumberFormatException e) {
      return Optional.empty();
    } finally {
      sb = new StringBuilder();
    }
  }
}
