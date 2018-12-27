package sample;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class StringParserTest {

    public StringParserTest(String input, String ExpectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    private String input;
    private String expectedOutput;



    @Parameters
    public static Collection<String[]> testConditions(){

        ArrayList<String[]> ret = new ArrayList<>();

        ret.add(new String[]{
            "Explore results with the Tools below. Replace & List output custom results. Details lists capture groups. Explain describes your expression in plain English."
            , "F***o"});
        ret.add(new String[]{
            "[ERROR] ..\\..\\checkstyle\\61b_checks.xml:0: File does not end with a newline. [NewlineAtEndOfFile]"
            , "F***o"});
        ret.add(new String[]{
            "[ERROR] ..\\..\\checkstyle\\61b_checks.xml:97:1: File contains tab characters (this is the first instance). [FileTabCharacter]"
            , "F***o"});
        ret.add(new String[]{
            "[ERROR] ..\\..\\checkstyle\\checkhere\\enigma\\Makefile:4:2: File contains tab characters (this is the first instance). [FileTabCharacter]"
            , "F***o"});
        ret.add(new String[]{
            "[ERROR] ..\\..\\checkstyle\\checkhere\\enigma\\Makefile:4:2: File contains tab characters (this is the first instance). [FileTabCharacter]"
            , "F***o"});
        ret.add(new String[]{
            "[ERROR] ..\\..\\checkstyle\\checkstyle-6.15-all.jar:0: File does not end with a newline. [NewlineAtEndOfFile]"
            , "F***o"});
        ret.add(new String[]{
            "[ERROR] ..\\..\\checkstyle\\checkstyle-6.15-all.jar:1: File length is 38,573 lines (max allowed is 2,000). [FileLength]"
            , "F***o"});
        ret.add(new String[]{
            "[ERROR] ..\\..\\checkstyle\\checkstyle-6.15-all.jar:3:139: File contains tab characters (this is the first instance). [FileTabCharacter]"
            , "F***o"});
        ret.add(new String[]{
            "[ERROR] ..\\..\\checkstyle\\checkstyle.bat:0: File does not end with a newline. [NewlineAtEndOfFile]"
            , "F***o"});
        ret.add(new String[]{
            "[ERROR] ..\\..\\checkstyle\\style61b.py:0: File does not end with a newline. [NewlineAtEndOfFile]"
            , "F***o"});
        ret.add(new String[]{
            "[ERROR] ..\\..\\checkstyle\\style61b.py:9:2: File contains tab characters (this is the first instance). [FileTabCharacter]"
            , "F***o"});
        return ret;
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testParse() {

        StringParser sp = new StringParser();
        boolean result = sp.parse(input);

        assertTrue(result);



    }
}