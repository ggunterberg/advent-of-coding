package gunterbergg.advent_of_code.y2023.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class DayStar {

  private static final Logger logger = LoggerFactory.getLogger(DayStar.class);

  public abstract Integer process(DayStarResource resource);

  private final String defaultPath;

  protected DayStar(Collection<String> paths) {
    Objects.requireNonNull(paths, "Paths must not be null");
    this.defaultPath = paths
      .stream()
      .filter(Objects::nonNull)
      .filter(DayStarResource::verifyResouce)
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("No valid path present"));
  }

  public void answer() {
    Stream
      .of(defaultPath)
      .map(this::process)
      .map(String::valueOf)
      .forEachOrdered(logger::info);
  }

  private Integer process(String path) {
    try (final var res = DayStarResource.of(path)){
      return this.process(res);
    }
  }
}
