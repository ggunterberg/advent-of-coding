package gunterbergg.advent_of_code.y2023.commons;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.apache.commons.io.IOUtils.buffer;

public class DayStarStream implements AutoCloseable {

  private final InputStream inputStream;

  private DayStarStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public static DayStarStream of(InputStream inputStream) {
    return new DayStarStream(inputStream);
  }

  public void consume(BiConsumer<Integer, String> consumer) {
    final var iterator = new LineIterator(buffer((new InputStreamReader(inputStream))));
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
    final var iterator = new LineIterator(buffer((new InputStreamReader(inputStream))));
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

  @Override
  public void close() {
    try {
      inputStream.close();
    } catch (IOException e) {}
  }
}
