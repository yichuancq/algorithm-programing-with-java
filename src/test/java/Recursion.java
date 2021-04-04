import org.junit.Test;

public class Recursion {
    /**
     * 阶乘
     *
     * @param n
     * @return
     */
    public double factorial(int n) {
        if (n < 1) {
            return 1;
        } else {
            return n * this.factorial(n - 1);
        }
    }

    /**
     * 累加
     *
     * @param n
     * @return
     */
    public int add(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n + this.add(n - 1);
        }
    }

    /**
     * 递归方法1
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else
            return this.fib(n - 1) + fib(n - 2);
    }

    /**
     * 递归方法2
     */
    int fib2(int n) {
        int mod = (int) (1e9 + 7), f0 = 0, f1 = 1;
        if (n == 0) return f0;
        else if (n == 1) return f1;
        int ans = 0;
        for (int i = 2; i <= n; ++i) {
            ans = (f0 + f1) % mod;
            f0 = f1;
            f1 = ans;
        }
        return ans;
    }


    @Test
    public void test() {
        Recursion mathFunction = new Recursion();
        System.out.println(mathFunction.fib2(2));
        System.out.println(mathFunction.fib2(5));
    }
}
