package gunterbergg.advent_of_code.y2023.day3;

import org.immutables.value.Value;

@Value.Style(allParameters = true)
@Value.Immutable
public abstract class XBoundary {

  abstract Integer size();
  abstract Position position();

  public boolean isAdjecent(XBoundary other) {
    final var yOffset = Math.abs(other.position().y() - position().y());
    if (yOffset > 1) return false;

    final var startXRange = position().x() - 1; // left - 1
    final var endXRange = position().x() + size() + 1; // right + 1

    final var otherStartX = other.position().x(); // other left
    final var otherEndX = other.position().x() + other.size(); // other right

    // if completly inside consider adjecent
    return (otherStartX >= startXRange && otherEndX < endXRange) || // thisStart <= otherStart > thisEnd
      (otherEndX > startXRange && otherEndX <= endXRange); // thisStart <= otherEnd > thisEnd
  }
}
