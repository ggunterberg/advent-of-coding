package gunterbergg.advent_of_code.y2023.day1;

enum Token {
  N1("1"), N2("2"), N3("3"), N4("4"), N5("5"), N6("6"), N7("7"), N8("8"), N9("9"), RESERVED(".");

  private String identifier;

  Token(String str) {
    this.identifier = str;
  }

  public String getIdentifier() {
    return identifier;
  }

  public static Token parse(String id) {
    for (Token token : Token.values()) {
      if (token.getIdentifier().equals(id)) return token;
    }
    return null;
  }
}
