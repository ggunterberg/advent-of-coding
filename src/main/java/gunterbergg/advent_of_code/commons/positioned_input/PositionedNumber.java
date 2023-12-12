package gunterbergg.advent_of_code.commons.positioned_input;

import org.immutables.value.Value;

@Value.Style(allParameters = true)
@Value.Immutable
public abstract class PositionedNumber {
  abstract Rect boundary();
  abstract Integer num();
}
