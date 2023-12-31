package parser.cal.lexur;

import org.junit.jupiter.api.Test;
import parser.cal.token.Token;
import parser.cal.token.TokenType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LexerTest {

    @Test
    void singleChar(){
        String input = "+-* / ()=\n";
        List<Token> expectedTokens = List.of(
                new Token(TokenType.PLUS, "+"),
                new Token(TokenType.MINUS, "-"),
                new Token(TokenType.MULTIPLY, "*"),
                new Token(TokenType.DIVIDE, "/"),
                new Token(TokenType.LPAREN, "("),
                new Token(TokenType.RPAREN, ")"),
                new Token(TokenType.ASSIGN, "="),
                new Token(TokenType.NEWLINE, "\n"),
                new Token(TokenType.EOF, null)
        );

        assertTokens(input, expectedTokens);
    }

    @Test
    void id() {
        assertTokens("abc def a1",
                List.of(new Token(TokenType.ID, "abc"),
                        new Token(TokenType.ID, "def"),
                        new Token(TokenType.ID, "a1"),
                        new Token(TokenType.EOF, null))
        );
    }

    @Test
    void letToken() {
        assertTokens("let a1",
                List.of(
                        new Token(TokenType.LET, "let"),
                        new Token(TokenType.ID, "a1"),
                        new Token(TokenType.EOF, null))
        );
    }

    @Test
    void numberToken() {
        assertTokens("10 1.234",
                List.of(
                        new Token(TokenType.NUMBER, "10"),
                        new Token(TokenType.NUMBER, "1.234"),
                        new Token(TokenType.EOF, null))
        );
    }

    @Test
    void pow() {
        assertTokens("**",
                List.of(
                        new Token(TokenType.POW, "**"),
                        new Token(TokenType.EOF, null))
        );
    }

    @Test
    void illegal() {
        assertTokens("1@",
                List.of(
                        new Token(TokenType.NUMBER, "1"),
                        new Token(TokenType.ILLEGAL, "@"),
                        new Token(TokenType.EOF, null))
        );
    }

    @Test
    void composite() {
        assertTokens("let a1 = 10\n1 * 2 * a3 ** 2",
                List.of(
                        new Token(TokenType.LET, "let"),
                        new Token(TokenType.ID, "a1"),
                        new Token(TokenType.ASSIGN, "="),
                        new Token(TokenType.NUMBER, "10"),
                        new Token(TokenType.NEWLINE, "\n"),
                        new Token(TokenType.NUMBER, "1"),
                        new Token(TokenType.MULTIPLY, "*"),
                        new Token(TokenType.NUMBER, "2"),
                        new Token(TokenType.MULTIPLY, "*"),
                        new Token(TokenType.ID, "a3"),
                        new Token(TokenType.POW, "**"),
                        new Token(TokenType.NUMBER, "2"),
                        new Token(TokenType.EOF, null))
        );
    }

    private void assertTokens(String input, List<Token> expectedTokens) {
        Lexer lexer = new Lexer(input);
        for (Token expectedToken : expectedTokens) {
            assertThat(lexer.nextToken()).isEqualTo(expectedToken);
        }
    }
}
