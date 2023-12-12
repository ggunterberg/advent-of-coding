package gunterbergg.advent_of_code.commons.positioned_input;

import java.util.Optional;

import static gunterbergg.advent_of_code.commons.positioned_input.AocPositionedInput.rect;
import static gunterbergg.advent_of_code.commons.positioned_input.AocPositionedInput.size;

public final class ComposedPositionedNumberParser extends LineComposedPositionedEntityParser<PositionedNumber> {

  @Override
  public Optional<PositionedNumber> parse(String chars, Position position) {
    try {
      final var parse = Integer.parseInt(chars);
      final var positionedResult = ImmutablePositionedNumber.of(
        rect(
          position,
          size(chars.length(), 1)
        ), parse);

      return Optional.<PositionedNumber>of(positionedResult);
    } catch (NumberFormatException e) {
      return Optional.empty();
    }
  }

}
