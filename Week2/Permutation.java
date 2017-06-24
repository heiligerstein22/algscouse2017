/*************************************************************************
 * javac  -cp algs4.jar:.  Permutation.java
 * java -classpath algs4.jar:. Permutation 12 < queues_tests/distinct.txt
 *
 *************************************************************************/

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rQue = new RandomizedQueue<String>();
 
        while (!StdIn.isEmpty())
            rQue.enqueue(StdIn.readString());
 
        int i = 0;
        for (String str : rQue) {		// interator nao repete
            if (i++ >= k)           	// para no numero k passado
                break;      
            System.out.println(str);
        }
	}

}
