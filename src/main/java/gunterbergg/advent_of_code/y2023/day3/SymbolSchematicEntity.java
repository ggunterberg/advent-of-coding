package gunterbergg.advent_of_code.y2023.day3;

import org.immutables.value.Value;

@Value.Immutable
public abstract class SymbolSchematicEntity extends SchematicEntity {

  abstract String symbol();

  @Override
  public String string() {
    return symbol();
  }
}
