public class Percolation {
        private int n;
        private int[] index;
        private int[] size;
        private boolean percolate = false;
        private int open = 0;
        // creates n-by-n grid, with all sites initially blocked
        public Percolation(int n) {
            if (n <= 0) {
                throw new IllegalArgumentException();
            }
            this.n = n;
            index = new int[n * n];
            for (int i = 0; i < index.length; i++) {
                index[i] = -1;
            }
            size = new int[n * n];
        }
    
        // opens the site (row, col) if it is not open already
        public void open(int row, int col) {
            if (!validate(row, col)) throw new IllegalArgumentException();
            if (isOpen(row, col)) return;
            int id = (row - 1) * n + col - 1;
            index[id] = id;
            size[id] = 1;
            open++;
            //Top
            if (row != 1 && isOpen(row - 1, col)) {
                connect(id, id - n);
            }
            //Left
            if (col != 1 && isOpen(row, col - 1)) {
                connect(id, id - 1);
            }
            //Right
            if (col != n && isOpen(row, col + 1)) {
                connect(id, id + 1);
            }
            //Bottom
            if (row != n && isOpen(row + 1, col)) {
                connect(id, id + n);
            }
        }

        private void connect(int id1, int id2) {
            int root1 = getRoot(id1);
            int root2 = getRoot(id2);
            if (root1 == root2) {
                return;
            }
            if (root1 < root2) {
                index[root2] = root1;
                size[root1] += size[root2];
            }
            else {
                index[root1] = root2;
                size[root2] += size[root1];
            }
        }
    
        // is the site (row, col) open?
        public boolean isOpen(int row, int col) {
            if (!validate(row, col)) throw new IllegalArgumentException();
            return (index[(row - 1) * n + col - 1] != -1);
        }
        
        //Get the root
        private int getRoot(int id) {
            if (index[id] == -1) return -1;
            while (index[id] != id){
                id = index[id];
            }
            return id;
        }

        private boolean validate (int row, int col){
            return (row > 0 && row <= n && col > 0 && col <=n );
        }
    
        // is the site (row, col) full?
        public boolean isFull(int row, int col) {
            if (!validate(row, col)) throw new IllegalArgumentException();
            if (!isOpen(row, col)) return false;
            int id = (row - 1) * n + col - 1;
            return (getRoot(id) <= (n - 1));
        }
    
        // returns the number of open sites
        public int numberOfOpenSites() {
            return open;
        }

        // does the system percolate?
        public boolean percolates() {
            if (percolate) return true;
            for (int i = index.length - n; i < index.length; i++) {
                int temp = getRoot(i);
                if (temp < n && temp >=0){
                    percolate = true;
                    return true;
                }
            }
            return false;
        }
    
        // test client (optional)
        public static void main(String[] args) {
        
        }
    }

