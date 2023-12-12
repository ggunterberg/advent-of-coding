package gunterbergg.advent_of_code.y2023.day3;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public abstract class EngineSchematic {

  abstract List<EnginePart> parts();

}
