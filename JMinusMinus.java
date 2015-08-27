import java.io.*;
import java.net.*;
import java.lang.*;
import java.math.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class JMinusMinus {

    static HashMap<String, String> replacements = new HashMap<String, String>(){{
        put("@f", "for");
        put("@w", "while");
        put("@i", "if");
        put("main", "public static void main(String[]a)");
        put("fn", "public static");
        put("str", "String");
        put("dub", "double");
        put("fpn", "float");
        put("vd", "void");
        put("echo", "System.out.println");
        put("\\^\\^", "!=");
        put("nw", "new");
        put("map", "HashMap");
        put("lg", "long");
        put("subs", "substring");
        put("cut", "split");
    }};

    static ArrayList<String> functions = new ArrayList<String>(){{
        add("public static int fac(int n){int f=1;for(int i=1;i<=n;i++){f*=i;}return f;}"); //factorial function
        add("public static final double GR = (1+sqrt(5))/2;"); //golden ratio used for other functions
        add("public static int fibo(int n){" +
            "return (int)((pow(GR,n) - pow(-GR, -n))/sqrt(5));}"); //get nth fibonacci number
        add("public static String input(){Scanner s=new Scanner(System.in);" +
            "String a=s.nextLine();s.close();return a;}"); //get string input from stdin
    }};

    public static void main(String[] a) throws IOException{
        String file = a[0];
        StringBuilder jCode = new StringBuilder();
        jCode.append("import java.io.*;import java.net.*;import java.lang.*;import java.math.*;import static java.lang.Math.*;import java.util.*;");
        jCode.append("public class J{");
        for(String s : functions){
            jCode.append(s);
        }
        String b = readFile(file);
        for(String s : replacements.keySet()){
            b = replaceAll(b, s, replacements.get(s));
        }
        jCode.append(b);
        jCode.append("}//");
        writeFile("J.java", jCode.toString());
    }

    public static String replaceAll(String source, String from, String to){
        return source.replaceAll("(" + from + ")(?![^\"]*\"(?:[^\"]*\"[^\"]*\")*[^\"]*$)", " " + to + " ");
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
