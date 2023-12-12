package gunterbergg.advent_of_code.y2023.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

public class DayStarResource implements AutoCloseable {

  protected final static Logger logger = LoggerFactory.getLogger(DayStarResource.class);

  private final URI target;
  private Optional<InputStream> inputStream;

  private DayStarResource(URI target) {
    this.target = target;
    this.inputStream = Optional.empty();
  }

  public static DayStarResource of(String path) {
    Objects.requireNonNull(path, "path must not be null");
    final var targetURI = URI.create(path);
    return new DayStarResource(targetURI);
  }

  public Optional<DayStarStream> stream() {
    inputStream = Optional.ofNullable(getClass().getResourceAsStream(target.getPath()));
    return inputStream.isPresent() ? Optional.of(DayStarStream.of(inputStream.get())) : Optional.empty();
  }

  public static boolean verifyResouce(String path) {
    if (path == null) return false;
    try {
      final var targetURI = URI.create(path);
      return DayStarResource.class.getResource(targetURI.getPath()) != null;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public void close() {
    inputStream.ifPresent(in -> {
      try {
        in.close();
      } catch (IOException e) {
        logger.warn("error when closing star resource handle", e);
      }
    });
  }
}
