import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String word = "";
        String champion = "";
        int i = 0;
        while(!StdIn.isEmpty()) {
            String words = StdIn.readString();
            for (int j = 0; j < words.length(); j++) {
                word += words.charAt(j);
                if (j == words.length() - 1) {
                    i++;
                    if (StdRandom.bernoulli(1 / i)) {
                        champion = word;
                    }
                    word = "";
                }
            }
        }

        StdOut.println(champion);
    }
}
