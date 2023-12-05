package gunterbergg.advent_of_code.y2023.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class DefaultDayStarImpl {

  protected final static Logger logger = LoggerFactory.getLogger(DefaultDayStarImpl.class);

  public static void run(String[] args, Consumer<Path> fileConsumer) {
    Arrays.stream(args)
      .findFirst()
      .ifPresentOrElse(
        path -> fileConsumer.accept(Path.of(path)),
        () -> exitWithError("Input path not specified")
      );
  }

  public abstract void process(Path inputFile);

  protected void read(Path inputFile, Consumer<BufferedReader> consumer){
    try {
      final var fr = Files.newBufferedReader(inputFile);
      consumer.accept(fr);
      fr.close();
    } catch (IOException e) {
      exitWithError("Error reading file: " + e);
    }
  }

  protected <T> T readReturn(Path inputFile, Function<BufferedReader, T> fn){
    T result = null;
    try {
      final var fr = Files.newBufferedReader(inputFile);
      result = fn.apply(fr);
      fr.close();
    } catch (IOException e) {
      exitWithError("Error reading file: " + e);
    }
    return result;
  }

  public static void exitWithError(){
    logger.error("An unespecified error ocourred during execution.");
    System.exit(1);
  }

  public static void exitWithError(String error){
    if (error == null) {
      exitWithError();
    } else {
      logger.error(error);
      System.exit(1);
    }
  }

}
