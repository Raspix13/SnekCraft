import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneticsParser {

    /**Map<String, Integer> replacementMap = new HashMap<>() {{
     put("normal", 0);
     put("palmetto", 1);
     put("motley", 2);
     }};*/

    Map<String, Integer> replacementMap = new HashMap<>() {{
        put("normal", 0);
        put("amel", 1);
        put("anery", 2);
        put("lavender", 3);
        put("caramel", 4);
        put("snow", 5);
        put("moonstone", 6);
        put("opal", 7);
        put("anerycaramel", 8);
        put("butter", 9);
        put("almond", 10);
        put("lavenderbutter", 11);
        put("xanthicsnow", 12);
        put("glacier", 13);
        put("moonstonecaramel", 14);
        put("xanthicsnowlavender", 15);
    }};

    public static void main(String[] args) {
        GeneticsParser gen = new GeneticsParser();

        String fileName = "src/data.txt";
        gen.parseGenePoolsFromFile(fileName);

    }

    public String parseGenePoolsFromFile(String fileName) {

        //String output = "    public static GenePool[][] patternGenetics = new GenePool[][]{\n";
        String output = "    public static GenePool[][] colorGenetics = new GenePool[][]{\n";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                output += parseGeneRow(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        output += "\t};";
        System.out.println(output);
        return output;
    }

    public String parseGeneRow(String input){
        List<String> pools = splitByCurlyBraces(input);
        String output = "{";
        for (String pool: pools){
            List<String> genetics = splitByParen(pool);
            String[] parts = genetics.get(0).split(",");
            String traits = getTrait(parts[0].trim());
            String chances = getChance(parts[1].trim());
            for (int i = 1; i < genetics.size(); i++){
                parts = genetics.get(i).split(",");
                traits += ", " + getTrait(parts[0].trim());
                chances += ", " + getChance(parts[1].trim());
            }
            output += "\n" + "new GenePool(new int[]{" + traits + "}, new int[]{" + chances + "}),";
        }
        output += "},\n";
        return output;
    }

    public String getTrait(String input){
        String result = replacementMap.containsKey(input)? replacementMap.get(input).toString(): "NULL";
        return result;
    }

    public String getChance(String input){
        String result = input.replace("%", "");
        return result;
    }


    public static List<String> splitByCurlyBraces(String input) {
        List<String> substrings = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            substrings.add(matcher.group(1));
        }

        return substrings;
    }

    public static List<String> splitByParen(String input) {
        List<String> substrings = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            substrings.add(matcher.group(1));
        }

        return substrings;
    }

}}