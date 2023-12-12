package gunterbergg.advent_of_code.commons.positioned_input;

import org.immutables.value.Value;

@Value.Style(allParameters = true)
@Value.Immutable
public abstract class Position implements Comparable<Position> {

  public abstract Integer x();
  public abstract Integer y();

  public static Position pos(Integer x, Integer y) {
    return ImmutablePosition.of(x, y);
  }

  @Override
  public int compareTo(Position other) {
    final var y = Integer.compare(other.y(), this.y());
    if (y != 0) return y;
    final var x = Integer.compare(other.x(), this.x());
    return x;
  }
}
