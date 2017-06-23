/*
*
*   javac -cp algs4.jar Percolation.java
*   java -classpath algs4.jar:. Percolation
*
*
*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF wquf = null;
    private WeightedQuickUnionUF wquf_full = null;
    private boolean[] vector = null;
    private int n = 0;
    private int openSites = 0;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {

		if (n <= 0)
			throw new java.lang.IllegalArgumentException();

        // creating array
        vector = new boolean[n*n+2];
        wquf = new WeightedQuickUnionUF((n*n)+2);
        wquf_full = new WeightedQuickUnionUF((n*n)+1);
        this.n = n;

        // +2 to virutal, last 2
        for (int i = 1; i < n*n+1; i++) {

            // fill fields
            vector[i] = false;

            /* makes unions */
            if (i <= n) {                           // if in first row
                wquf.union(i, 0);
                wquf_full.union(i, 0);
            } else if (i > n*(n-1)) {                // if in last row
                wquf.union(i, n*n+1);
            }

        }

    }

    // converte para unidimensional
    private int conv(int i, int j) {
        return n*(i-1)+j;
    }

    // open site (row, col) if it is not open already
    public void open(int i, int j) {

        if ((i < 1 || i > n) || (j < 1 || j > n))   // checks correct range
            throw new java.lang.IndexOutOfBoundsException();

        if (!isOpen(i, j)) {

            int conv = conv(i, j);

            // open actual position
            vector[conv] = true;
            openSites++;

            if (n == 1) {                           // n = 1 case
                wquf.union(1, 0);
                wquf_full.union(1, 0);
                wquf.union(1, n*n+1);
            }

            if (j >= 1 && j < n)                    // has right neighbour
                if (isOpen(i, j+1)) {
                    wquf.union(conv(i, j+1), conv);
                    wquf_full.union(conv(i, j+1), conv);
                }
            if (j <= n && j > 1)                    // has left neighbour
                if (isOpen(i, j-1)) {
                    wquf.union(conv(i, j-1), conv);
                    wquf_full.union(conv(i, j-1), conv);
                }
            if (i >= 1 && i < n)                    // has down neighbour
                if (isOpen(i+1, j)) {
                    wquf.union(conv(i+1, j), conv);
                    wquf_full.union(conv(i+1, j), conv);
                }
            if (i <= n && i > 1)                    // has up neighbour
                if (isOpen(i-1, j)) {
                    wquf.union(conv(i-1, j), conv);
                    wquf_full.union(conv(i-1, j), conv);
                }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int i, int j) {

        if ((i < 1 || i > n) || (j < 1 || j > n))
            throw new java.lang.IndexOutOfBoundsException();

        return vector[conv(i, j)];
    }

    // is site (row, col) full?
    public boolean isFull(int i, int j) {

        if ((i < 1 || i > n) || (j < 1 || j > n))
            throw new java.lang.IndexOutOfBoundsException();

		return wquf_full.connected(conv(i, j), 0) && isOpen(i, j);
    }
/*
    public boolean isFull(int i, int j) {

        if ((i < 1 || i > n) || (j < 1 || j > n))
            throw new java.lang.IndexOutOfBoundsException();

		// se na ultima linha, verifica se vizinhos sao full recursivamente
        // para evitar que caminhos vao por baixo no virtual
   	    int conv = conv(i, j);

		if (isOpen(i, j)) {

			if (conv > n*(n-1)) {		// last row

				System.out.printf("entrei " + i + "-" + j + " " + "\n");

				if (isOpen(i-1,j))
					if (isFull(i-1, j)) {						// check if top is Full
						System.out.printf("top\n");
						return true;
					}
				if (isOpen(i, j-1))
					if (isFull(i, j-1)) {						// left
						System.out.printf("left\n");
						return true;
					}
				if (isOpen(i, j+1))
					if (isFull(i, j+1)) {						// right
						System.out.printf("right\n");
						return true;
					}
				return false;

			} else {
				System.out.printf("sai "+i+"-"+j+" "+ wquf.connected(conv(i, j), 0) +"\n");
				return wquf.connected(conv(i, j), 0);
			}

		}
		return false;
    }
*/

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquf.connected(0, n*n+1);
    }

	/*
    public void print() {
        for (int i = 1; i <= n*n; i++) {
              System.out.print((vector[i] ? i + "(O)" : i + "(X)") + "\t");
            if ((i % n) == 0) {
                  System.out.printf("\n");
            }
        }
        // System.out.printf("\n" + (wquf.connected(0, 17) ? "Percolates" : "Do not percolates yet") + "\n");
        System.out.printf("\n" + (percolates() ? "Percolates" : "Do not percolates yet") + "\n");
    }

    // test client (optional)
    public static void main(String[] args) {
        // System.out.print((isOpen(i, j) ? "0" : "X") + " ");
        Percolation p = new Percolation(Integer.parseInt(args[0]));
        p.open(1,1);
        p.open(2,1);
        p.open(3,1);
        p.open(4,1);
        p.open(4,2);
        p.open(4,3);
        p.print();
        // System.out.printf("OpenSites: " + p.numberOfOpenSites() + "\n");
        System.out.printf("FullSite: " + p.isFull(4,3) + "\n");
    }
	*/

}
