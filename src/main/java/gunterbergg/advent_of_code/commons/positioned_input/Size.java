package gunterbergg.advent_of_code.commons.positioned_input;

import org.immutables.value.Value;

@Value.Style(allParameters = true)
@Value.Immutable
public abstract class Size implements Comparable<Size> {

  public abstract Integer width();
  public abstract Integer height();

  public static Size size(Integer width, Integer height) {
    return ImmutableSize.of(width, height);
  }

  @Value.Lazy
  public Double area() {
    final var cast = Integer.valueOf(this.width() * this.height());
    return cast.doubleValue();
  }

  @Override
  public int compareTo(Size other) {
    return Double.compare(other.area(), this.area());
  }
}
