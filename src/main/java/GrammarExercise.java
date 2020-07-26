import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";

        List<String> result = findCommonWordsWithSpace(firstWordList, secondWordList);
        //按要求输出到命令行
        System.out.println(result);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        if (!(isValidInputString(firstWordList) && isValidInputString(secondWordList)))
            throw new RuntimeException("input not valid");

        String[] firstWords = firstWordList.split(",");
        String[] secondWords = secondWordList.split(",");

        return Arrays.stream(firstWords)
                .distinct()
                .filter(word -> Arrays.stream(secondWords)
                        .anyMatch(i -> i.equalsIgnoreCase(word)))
                .sorted()
                .map(word -> Arrays.stream(word.split(""))
                        .reduce((a, b) -> a + " " + b)
                        .get())
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    private static boolean isValidInputString(String str) {
        Stream<String> words = Arrays.stream(str.split(","));

        return words.allMatch(word ->
                word.length() > 0
                        && word.chars().allMatch(i -> Character.isLetter(i)));
    }
}
