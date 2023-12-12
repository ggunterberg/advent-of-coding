package gunterbergg.advent_of_code.y2023.day3;

import org.immutables.value.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Value.Immutable
public abstract class EngineSchematic {

  private static final Logger logger = LoggerFactory.getLogger(EngineSchematic.class);

  public abstract List<SchematicEntity> entities();

  public Integer calculateParts() {
    final var symbols = entities()
      .stream()
      .filter(entity -> entity instanceof SymbolSchematicEntity)
      .toList();

    final var adjecentDigits = symbols
      .stream()
      .map(symbol -> getAdjecent(symbol.boundary().position()))
      .flatMap(List::stream)
      .filter(DigitSchematicEntity.class::isInstance)
      .map(DigitSchematicEntity.class::cast)
      .toList();

    return adjecentDigits
      .stream()
      .mapToInt(DigitSchematicEntity::number)
      .sum();
  }

  protected List<SchematicEntity> getAdjecent(Position position){
    final var xboundary = ImmutableXBoundary.of(1, position);
    return
      entities()
        .stream()
        .filter(e -> e.boundary().isAdjecent(xboundary))
        .toList();
  }
}
