package gunterbergg.advent_of_code.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AdventOfCodeTask<T> {

  private static final Logger logger = LoggerFactory.getLogger(AdventOfCodeTask.class);

  public abstract T process(AdventOfCodeStream resourceStream);

  private final String defaultPath;

  protected AdventOfCodeTask(Collection<String> paths) {
    Objects.requireNonNull(paths, "Paths must not be null");
    this.defaultPath = paths
      .stream()
      .filter(Objects::nonNull)
      .filter(AdventOfCodeResource::verifyResouce)
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

  private T process(String path) {
    try (final var res = AdventOfCodeResource.of(path)) {
      final var stream =
        res
          .stream()
          .orElseThrow(() -> new IllegalArgumentException("Invalid DayStar Resource"));
      return this.process(stream);
    }
  }
}
