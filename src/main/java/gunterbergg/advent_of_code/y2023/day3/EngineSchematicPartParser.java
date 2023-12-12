package gunterbergg.advent_of_code.y2023.day3;

import gunterbergg.advent_of_code.commons.AdventOfCodeStream;
import gunterbergg.advent_of_code.commons.positioned_input.AocPositionedInput;
import gunterbergg.advent_of_code.commons.positioned_input.ComposedPositionedNumberParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EngineSchematicPartParser implements EngineSchematicParser<List<EnginePart>> {

  private static final Logger logger = LoggerFactory.getLogger(EngineSchematicPartParser.class);

  @Override
  public EngineSchematic parse(AdventOfCodeStream stream) {
    final var map = AocPositionedInput.from(stream);
    final var numberBuffer = new ComposedPositionedNumberParser();

    final var parts = map.sequencedEntrySet()
      .stream()
      .map(numberBuffer::map)
      .flatMap(Optional::stream)
      .filter(posNum ->
        posNum
          .boundary()
          .adjecentPositions()
          .parallelStream()
          .map(map::get)
          .filter(Objects::nonNull)
          .filter(ch -> !(ch.codePoints().anyMatch(Character::isDigit)) && ch != ".")
          .findFirst().isPresent()
      )
      .map(posNum -> ImmutableEnginePart.of(posNum.num()))
      .toList();

    return ImmutableEngineSchematic.builder().parts(parts).build();
  }

}
