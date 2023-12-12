package gunterbergg.advent_of_code.commons.positioned_input;

import org.immutables.value.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Value.Style(allParameters = true)
@Value.Immutable
public abstract class Rect {

  public abstract Position topLeft();
  public abstract Size size();

  @Value.Lazy
  public Position bottomRight() {
    return ImmutablePosition.of(
      this.topLeft().x() + this.size().width(),
      this.topLeft().y() + this.size().height()
    );
  }

  public static Rect rect(Position topLeft, Size size) {
    return ImmutableRect.of(topLeft, size);
  }
  public Set<Position> adjecentPositions() {
    final var topLeft = this.topLeft();
    final var bottomRight = this.bottomRight();

    final var topAndBottom = IntStream
      .of(topLeft.y() - 1, bottomRight.y() + 1)
      .mapToObj(topOrBottom -> IntStream
        .range(topLeft.x() - 1, bottomRight.x() + 1)
        .mapToObj(leftToRight -> AocPositionedInput.pos(leftToRight, topOrBottom))
        .toList()
      )
      .flatMap(List::stream)
      .toList();

    final var leftAndRight = IntStream
      .of(topLeft.x() - 1, bottomRight.x() + 1)
      .mapToObj(topOrBottom -> IntStream
        .range(topLeft.y() - 1, bottomRight.y() + 1)
        .mapToObj(leftToRight -> AocPositionedInput.pos(leftToRight, topOrBottom))
        .toList()
      )
      .flatMap(List::stream)
      .toList();

    final var adjecents = new HashSet<Position>();
    adjecents.addAll(topAndBottom);
    adjecents.addAll(leftAndRight);
    return adjecents;
  }
}
