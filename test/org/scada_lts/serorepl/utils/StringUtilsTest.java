package org.scada_lts.serorepl.utils;

import com.serotonin.util.StringUtils;
import org.junit.Test;

import java.util.regex.PatternSyntaxException;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void capitalize() throws Exception {

        assertEquals(StringUtils.capitalize("tekst"),
                org.scada_lts.serorepl.utils.StringUtils.capitalize("tekst"));

        assertEquals(StringUtils.capitalize("TEKST DUZY ZE SPACJAMI"),
                org.scada_lts.serorepl.utils.StringUtils.capitalize("TEKST DUZY ZE SPACJAMI"));

        assertEquals(StringUtils.capitalize("jJjKlju UJJ8kmi  ikkiu IK i,koi "),
                org.scada_lts.serorepl.utils.StringUtils.capitalize("jJjKlju UJJ8kmi  ikkiu IK i,koi "));

        assertEquals(StringUtils.capitalize(""),
                org.scada_lts.serorepl.utils.StringUtils.capitalize(""));

        assertEquals(StringUtils.capitalize(null),
                org.scada_lts.serorepl.utils.StringUtils.capitalize(null));
    }

    @Test
    public void escapeLT() throws Exception {
        String string1 = " <<<<  << >> <>< jJjKlju UJJ8kmi  ikkiu IK i,koi ";
        String string2 = "<<<<<<<<>";
        String string3 = "";
        assertEquals(StringUtils.escapeLT(string1),
                org.scada_lts.serorepl.utils.StringUtils.escapeLT(string1));

        assertEquals(StringUtils.escapeLT(string2),
                org.scada_lts.serorepl.utils.StringUtils.escapeLT(string2));



    }

    @Test(expected = NullPointerException.class)
    public void escapeLTShouldFailAtNulls() throws Exception {
        assertEquals(StringUtils.escapeLT(null),
                org.scada_lts.serorepl.utils.StringUtils.escapeLT(null));
    }


    @Test
    public void generateRandomString() throws Exception {

        String charSet1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

        assertFalse(org.scada_lts.serorepl.utils.StringUtils.generateRandomString(15, charSet1).contains("!"));
        assertFalse(org.scada_lts.serorepl.utils.StringUtils.generateRandomString(15, charSet1).contains("@"));
        assertFalse(org.scada_lts.serorepl.utils.StringUtils.generateRandomString(15, charSet1).contains("#"));
        assertFalse(org.scada_lts.serorepl.utils.StringUtils.generateRandomString(15, charSet1).contains("$"));
        assertFalse(org.scada_lts.serorepl.utils.StringUtils.generateRandomString(15, charSet1).contains("%"));
        assertFalse(org.scada_lts.serorepl.utils.StringUtils.generateRandomString(15, charSet1).contains("*"));
        assertFalse(org.scada_lts.serorepl.utils.StringUtils.generateRandomString(15, charSet1).contains("<"));
        assertTrue(org.scada_lts.serorepl.utils.StringUtils.generateRandomString(15, charSet1).length() == 15);
        assertFalse(org.scada_lts.serorepl.utils.StringUtils.generateRandomString(15, charSet1).length() != 15);

    }


    @Test
    public void globWhiteListMatchIgnoreCase() throws Exception {
        // TODO
    }


    @Test
    public void isBetweenInc() throws Exception {
        assertTrue(org.scada_lts.serorepl.utils.StringUtils.isBetweenInc(5,2,6));
        assertTrue(org.scada_lts.serorepl.utils.StringUtils.isBetweenInc(5,2,5));
        assertTrue(org.scada_lts.serorepl.utils.StringUtils.isBetweenInc(5,5,5));
        assertTrue(org.scada_lts.serorepl.utils.StringUtils.isBetweenInc(10,-5,15));
        assertFalse(org.scada_lts.serorepl.utils.StringUtils.isBetweenInc(21,5,5));
    }

    @Test
    public void isEmpty() throws Exception {
        int[] table = new int[5];
        int[] emptyArray = new int[0];

        assertFalse(org.scada_lts.serorepl.utils.StringUtils.isEmpty(table));
        assertTrue(org.scada_lts.serorepl.utils.StringUtils.isEmpty(emptyArray));
    }


    @Test
    public void isEmptyString() throws Exception {
        String s1 = "String";
        String s2 = "";
        assertFalse(org.scada_lts.serorepl.utils.StringUtils.isEmpty(s1));
        assertTrue(org.scada_lts.serorepl.utils.StringUtils.isEmpty(s2));
    }


    @Test
    public void isEqual() throws Exception {
        assertTrue(StringUtils.isEqual(null,null));
        assertFalse(StringUtils.isEqual(null,new Object()));
        assertFalse(StringUtils.isEqual(new Object(),null));
        Object object = new Object();
        assertTrue(StringUtils.isEqual(object,object));
        assertFalse(StringUtils.isEqual(object,21));
        assertFalse(StringUtils.isEqual("String",object));
    }

    @Test
    public void isLengthGreaterThan() throws Exception {
        assertTrue(org.scada_lts.serorepl.utils.StringUtils.isLengthGreaterThan("Abcdews",3));
        assertTrue(org.scada_lts.serorepl.utils.StringUtils.isLengthGreaterThan("Abcdews",2));
        assertTrue(org.scada_lts.serorepl.utils.StringUtils.isLengthGreaterThan("Abcdews",-1));
        assertTrue(org.scada_lts.serorepl.utils.StringUtils.isLengthGreaterThan("Abcdews",0));
        assertFalse(org.scada_lts.serorepl.utils.StringUtils.isLengthGreaterThan("As",5));
        assertFalse(org.scada_lts.serorepl.utils.StringUtils.isLengthGreaterThan(null,5));
    }


    @Test
    public void pad() throws Exception {
        StringUtils.pad("RANDOM", 'X', 7);
        org.scada_lts.serorepl.utils.StringUtils.pad("RANDOM", 'X', 7);

        assertEquals(StringUtils.pad("RANDOM", 'X', 7)  ,
        org.scada_lts.serorepl.utils.StringUtils.pad("RANDOM", 'X', 7));

    }

    @Test
    public void parseInt() throws Exception {
        assertEquals(org.scada_lts.serorepl.utils.StringUtils.parseInt("21", 1), 21);
        assertEquals(org.scada_lts.serorepl.utils.StringUtils.parseInt("12", 1), 12);
        assertEquals(org.scada_lts.serorepl.utils.StringUtils.parseInt("30", 1), 30);
        assertEquals(org.scada_lts.serorepl.utils.StringUtils.parseInt("1", 1), 1);
        assertEquals(org.scada_lts.serorepl.utils.StringUtils.parseInt("siemanko", 99), 99);
    }



    @Test
    public void trimWhitespace() throws Exception {

        String s1 = "RANDOM";
        String s2 = "ASDASDASD ASDASD QWEQWE ZXCZXC";
        String s3 = "    asdxc  qwe  qwe qw " +
                "wqeqwe qw qw ss                ";
        String s4 = "                           RANDOM";
        String s5 = "RANDOM            ";
        String s6 = "RA   ND   OM";

        assertEquals(StringUtils.trimWhitespace(s1),
                org.scada_lts.serorepl.utils.StringUtils.trimWhitespace(s1));

        assertEquals(StringUtils.trimWhitespace(s2),
                org.scada_lts.serorepl.utils.StringUtils.trimWhitespace(s2));

        assertEquals(StringUtils.trimWhitespace(s3),
                org.scada_lts.serorepl.utils.StringUtils.trimWhitespace(s3));

        assertEquals(StringUtils.trimWhitespace(s4),
                org.scada_lts.serorepl.utils.StringUtils.trimWhitespace(s4));

        assertEquals(StringUtils.trimWhitespace(s5),
                org.scada_lts.serorepl.utils.StringUtils.trimWhitespace(s5));

        assertEquals(StringUtils.trimWhitespace(s6),
                org.scada_lts.serorepl.utils.StringUtils.trimWhitespace(s6));
    }


    @Test
    public void replaceMacro() throws Exception {
        assertEquals( org.scada_lts.serorepl.utils.StringUtils.replaceMacro("randomowy string z jakims ${parametr}","parametr","PARAM"), "randomowy string z jakims PARAM" );

    }

    @Test//(expected = PatternSyntaxException.class)
    public void replaceMacroFailAtWrongArguments() throws Exception{
            // TODO sprawdzic ze poleci exception jak jest zly 2 argument (nie znajdzie w stringu s patternu)
          //  org.scada_lts.serorepl.utils.StringUtils.replaceMacro("randomowy string z jakims ${parametr}","INVALID_PARAM","PARAM");

    }


    @Test
    public void replaceMacros() throws Exception {
       System.out.println(StringUtils.replaceMacros("TEST", System.getProperties()));
       System.out.println(StringUtils.replaceMacros(" SD Sd" +
               "asd ", System.getProperties()));
       System.out.println(StringUtils.replaceMacros("  d    ", System.getProperties()));
       System.out.println(StringUtils.replaceMacros(" SD Sdasd ", System.getProperties()));
       System.out.println(StringUtils.replaceMacros(" siemano kolano" +
               "mariano italiano" +
               "enter to byl czy nie enter? ", System.getProperties()));

        System.out.println(org.scada_lts.serorepl.utils.StringUtils.replaceMacros("TEST", System.getProperties()));
        System.out.println(org.scada_lts.serorepl.utils.StringUtils.replaceMacros(" SD Sd" +
                "asd ", System.getProperties()));
        System.out.println(org.scada_lts.serorepl.utils.StringUtils.replaceMacros("  d    ", System.getProperties()));
        System.out.println(org.scada_lts.serorepl.utils.StringUtils.replaceMacros(" SD Sdasd ", System.getProperties()));
        System.out.println(org.scada_lts.serorepl.utils.StringUtils.replaceMacros(" si            emano \n kolano mariano \nitaliano enter to byl \nczy nie enter? ", System.getProperties()));

    }

    @Test
    public void truncate1Parameter() throws Exception {

    }

    @Test
    public void truncate2Parameters() throws Exception {
        assertEquals(StringUtils.truncate("abcd", 3, "XXX"),
                org.scada_lts.serorepl.utils.StringUtils.truncate("abcd", 3, "XXX")
        );

        assertEquals(StringUtils.truncate("abcd", 7, "XXX"),
                org.scada_lts.serorepl.utils.StringUtils.truncate("abcd", 7, "XXX")
        );

        assertEquals(StringUtils.truncate("", 3, "XXX"),
                org.scada_lts.serorepl.utils.StringUtils.truncate("", 3, "XXX")
        );

        assertEquals(StringUtils.truncate(null, 3, "XXX"),
                org.scada_lts.serorepl.utils.StringUtils.truncate(null, 3, "XXX")
        );
    }


/*
    @Test
    public void isEqualIgnoreCase() throws Exception {
    }







    @Test
    public void isEmpty2() throws Exception {
    }

    @Test
    public void isLengthBetween() throws Exception {
    }

    @Test
    public void isLengthLessThan() throws Exception {
    }



    @Test
    public void containsUppercase() throws Exception {
    }

    @Test
    public void containsLowercase() throws Exception {
    }

    @Test
    public void containsDigit() throws Exception {
    }


    @Test
    public void mask() throws Exception {
    }

    @Test
    public void generatePassword() throws Exception {
    }

    @Test
    public void generatePassword1() throws Exception {
    }


    @Test
    public void isOneOf() throws Exception {
    }

    @Test
    public void isOneOf1() throws Exception {
    }





    @Test
    public void replaceMacro1() throws Exception {
    }

    @Test
    public void getMacroContent() throws Exception {
    }



    @Test
    public void findGroup() throws Exception {
    }

    @Test
    public void findGroup1() throws Exception {
    }

    @Test
    public void findAllGroup() throws Exception {
    }

    @Test
    public void findAllGroup1() throws Exception {
    }

    @Test
    public void in() throws Exception {
    }


    @Test
    public void durationToString() throws Exception {
    }



    @Test
    public void startsWith() throws Exception {
    }

    @Test
    public void compareStrings() throws Exception {
    }
    */
}