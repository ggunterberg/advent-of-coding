package gunterbergg.advent_of_code.y2023.day1;

public record TokenPosition(Token token, Integer pos) implements Comparable<TokenPosition> {
  @Override
  public int compareTo(TokenPosition o) {
    return this.pos().compareTo(o.pos());
  }
}
