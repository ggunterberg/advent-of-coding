package gunterbergg.advent_of_code.y2023.day3;

import gunterbergg.advent_of_code.commons.AdventOfCodeStream;

public interface EngineSchematicParser<T> {

  EngineSchematic parse(AdventOfCodeStream stream);
}
