package gunterbergg.advent_of_code.commons;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AdventOfCodeStream implements AutoCloseable {

  private static final Logger logger = LoggerFactory.getLogger(AdventOfCodeStream.class);

  private final InputStream inputStream;

  private AdventOfCodeStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public static AdventOfCodeStream of(InputStream inputStream) {
    return new AdventOfCodeStream(inputStream);
  }

  public void consume(BiConsumer<Integer, String> consumer) {
    final var iterator = new LineIterator(buffer());
    IntStream
      .iterate(0, i -> i + 1)
      .takeWhile(i -> iterator.hasNext())
      .onClose(() -> IOUtils.closeQuietly(iterator))
      .forEach(index -> {
        final var line = iterator.nextLine();
        consumer.accept(index, line);
      });
  }

  public <T> Stream<T> produce(BiFunction<Integer, String, T> function) {
    final var iterator = new LineIterator(buffer());
    return
      IntStream
        .iterate(0, i -> i + 1)
        .takeWhile(i -> iterator.hasNext())
        .onClose(() -> IOUtils.closeQuietly(iterator))
        .mapToObj(index -> {
          final var line = iterator.nextLine();
          return function.apply(index, line);
        });
  }

  private BufferedReader buffer() {
    return IOUtils.buffer(new InputStreamReader(inputStream));
  }

  @Override
  public void close() {
    try {
      inputStream.close();
    } catch (IOException e) {
      // NOOP, simply ignore an not closed stream
      logger.warn("Error when closing DayStarStream", e);
    }
  }
}
