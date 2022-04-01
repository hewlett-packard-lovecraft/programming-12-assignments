public class Main {
    public static int fib_helper(int n, int n1, int n2, int n3) {
        if (n == 0){
            return n3;
        } else {
            return fib_helper(n-1, n2, n1+n2, n1+n2);
        }
    }

    public static int fibbonachi(int n) {
        return fib_helper(n, 0, 1, 1);
    }

    public static int fib_sum(int n, int n1, int n2, int n3, int sum) {
        if (n == 0) {
            return sum;
        } else {
            return fib_sum(n-1, n2, n1+n2, n1+n2, sum + n3);
        }
    }

    public static int fibbonachi_sum(int n) {
        return fib_sum(n, 0, 1, 1, 0);
    }

    public static void main(String[] args) {
        System.out.println(fibbonachi(5));
        System.out.println(fibbonachi_sum(5));
    }
}
