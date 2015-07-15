import java.io.*;
import java.net.*;
import java.lang.*;
import java.math.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class JMinusMinus {

    public static void main(String[] a){
        String file = a[0];
        StringBuilder jCode = new StringBuilder();
        jCode.append("import java.io.*;import java.net.*;import java.lang.*;import java.math.*;import java.util.*;");
        jCode.append("public class J{");
        String b = readFile(file);
        b = replaceAll(b, "fn", "public static");
        b = replaceAll(b, "str", "String");
        b = replaceAll(b, "dub", "double");
        b = replaceAll(b, "fpn", "float");
        b = replaceAll(b, "nop", "void");
        b = replaceAll(b, "dub", "double");
        b = replaceAll(b, "echo", "System.out.println");
        b = replaceAll(b, "^^", "!=");
        b = replaceAll(b, "nw", "new");
        jcode.append(b);
        jCode.append("}//Bracket for class");
        writeFile("J.java", jCode.toString());
    }

    public static String replaceAll(String source, String from, String to){
        return source.replaceAll(from + "(?![^"]*"(?:[^"]*"[^"]*")*[^"]*$)", " " + to + " ");
    }

    public static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public static void writeFile(String path, String output) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.print(output);
        writer.close();
    }

}
