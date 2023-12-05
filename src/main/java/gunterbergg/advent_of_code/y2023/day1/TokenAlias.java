package gunterbergg.advent_of_code.y2023.day1;

enum TokenAlias {
  ONE("one", Token.N1),
  TWO("two", Token.N2),
  THREE("three", Token.N3),
  FOUR("four", Token.N4),
  FIVE("five", Token.N5),
  SIX("six", Token.N6),
  SEVEN("seven", Token.N7),
  EIGHT("eight", Token.N8),
  NINE("nine", Token.N9),
  ;

  private String alias;
  private Token token;

  TokenAlias(String alias, Token token) {
    this.alias = alias;
    this.token = token;
  }

  public String getAlias() {
    return alias;
  }

  public Token getToken() {
    return token;
  }
}
