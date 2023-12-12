package gunterbergg.advent_of_code.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

public class AdventOfCodeResource implements AutoCloseable {

  protected static final Logger logger = LoggerFactory.getLogger(AdventOfCodeResource.class);

  private final URI target;
  private Optional<InputStream> inputStream;

  private AdventOfCodeResource(URI target) {
    this.target = target;
    this.inputStream = Optional.empty();
  }

  public static AdventOfCodeResource of(String path) {
    Objects.requireNonNull(path, "path must not be null");
    final var targetURI = URI.create(path);
    return new AdventOfCodeResource(targetURI);
  }

  public Optional<AdventOfCodeStream> stream() {
    inputStream = Optional.ofNullable(getClass().getResourceAsStream(target.getPath()));
    return inputStream.isPresent() ? Optional.of(AdventOfCodeStream.of(inputStream.get())) : Optional.empty();
  }

  public static boolean verifyResouce(String path) {
    if (path == null) return false;
    try {
      final var targetURI = URI.create(path);
      return AdventOfCodeResource.class.getResource(targetURI.getPath()) != null;
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
