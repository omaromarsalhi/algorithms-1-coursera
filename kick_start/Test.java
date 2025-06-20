package kick_start;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Test {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(42);
        StdOut.println("Popped: " + s.pop());
    }
}