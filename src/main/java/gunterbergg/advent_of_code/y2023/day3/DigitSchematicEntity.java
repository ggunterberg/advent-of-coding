package gunterbergg.advent_of_code.y2023.day3;

import org.immutables.value.Value;

@Value.Immutable
public abstract class DigitSchematicEntity extends SchematicEntity {

  abstract Integer number();

  @Override
  public String string() {
    return String.valueOf(number());
  }
}
