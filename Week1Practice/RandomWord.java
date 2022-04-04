package Week1Practice;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int count = 0;
        String champion = "";
        while (!StdIn.isEmpty()){
            String temp = StdIn.readString();
            count++;
            if (StdRandom.bernoulli((1.00 / count))){
                champion = temp;
            }
        }
        System.out.println(champion);
    }
}
