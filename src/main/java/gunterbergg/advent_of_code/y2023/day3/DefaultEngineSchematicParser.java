package gunterbergg.advent_of_code.y2023.day3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class DefaultEngineSchematicParser {

  private static Logger logger = LoggerFactory.getLogger(DefaultEngineSchematicParser.class);

  public EngineSchematic parse(BufferedReader reader){
    int index = 0;
    String line;
    final var entities = new ArrayList<SchematicEntity>();
    try {
      while ((line = reader.readLine()) != null) {
        final var lineEntities = parseEntities(line, index);
        entities.addAll(lineEntities);
        index++;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return ImmutableEngineSchematic.builder().entities(entities).build();
  }

  private List<SchematicEntity> parseEntities(String line, Integer rowIndex) {
    final var entities = new ArrayList<SchematicEntity>();
    final var chars = line.toCharArray();

    for (int colIdx = 0; colIdx < chars.length; colIdx++) {
      final var aChar = chars[colIdx];
      if (aChar == '.')
        continue;

      final var position = ImmutablePosition.of(colIdx, rowIndex);

      if (Character.isDigit(aChar)) {
        final var next = IntStream
          .range(colIdx + 1, line.length())
            .filter(idx -> !Character.isDigit(chars[idx]))
            .findFirst().orElse(chars.length);
        final var num = Integer.parseInt(line.substring(colIdx, next));
        final var boundary = ImmutableXBoundary.builder()
          .size(next - colIdx)
          .position(position)
          .build();
        final var digitEntity = ImmutableDigitSchematicEntity.builder()
          .number(num)
          .boundary(boundary)
          .build();

        entities.add(digitEntity);
        colIdx = next - 1;
        continue;
      }

      final var boundary = ImmutableXBoundary.builder()
        .size(1)
        .position(position)
        .build();
      final var symbolEntity = ImmutableSymbolSchematicEntity.builder()
        .symbol(Character.toString(aChar))
        .boundary(boundary)
        .build();
      entities.add(symbolEntity);
    }

    return entities;
  }

  public static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch(NumberFormatException e){
      return false;
    }
  }

}
