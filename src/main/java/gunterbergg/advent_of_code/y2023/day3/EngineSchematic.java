package gunterbergg.advent_of_code.y2023.day3;

import org.immutables.value.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Value.Immutable
public abstract class EngineSchematic {

  private final List<SchematicEntity> entityList;

  public EngineSchematic(List<SchematicEntity> entityList) {
    this.entityList = entityList;
  }

  public Integer calculateParts() {
    final var verifyAdjecentsList = entityList
      .stream()
      .filter(entity -> entity instanceof SymbolSchematicEntity)
      .toList();

    final var adjecentDigits = new ArrayList<DigitSchematicEntity>();
    do {
      verifyAdjecentsList
        .stream()
        .map()

    } while (!verifyAdjecentsList.isEmpty());
  }

  protected List<SchematicEntity> getAdjecent(){
    final var positions = getPositionsMap();
    positions.get()
  }

  @Value.Lazy
  protected Map<Position, List<SchematicEntity>> getPositionsMap() {
    return entityList
      .stream()
      .collect(Collectors
        .groupingBy(schematicEntity -> schematicEntity.boundary().position())
      );
  }
}
