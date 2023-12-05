package gunterbergg.advent_of_code.y2023.day3;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Position {

  abstract Integer x();
  abstract Integer y();
}
