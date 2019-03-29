import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class WordCount {

    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(new File("song.txt"));
            HashMap<String, Integer> lyricCount = new HashMap<>();

            while (scanner.hasNext()) {
                String word = scanner.next();
                String strip = word.replaceAll("\\p{Punct}", "").toLowerCase();
                int count = lyricCount.getOrDefault(strip, 0) + 1;
                lyricCount.put(strip, count);
            }

            HashMap<String, Integer> sortedLyrics = sortByValue(lyricCount);
            PrintWriter print = new PrintWriter("output.txt");

            print.println("Total word count: " + sortedLyrics.size());
            print.println("-------------------------------");
            for (String word : sortedLyrics.keySet()) {
                print.printf("Word: %-10s Count: %s\n", word,  sortedLyrics.get(word));
            }
            print.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {

        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list = new LinkedList<>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}