import java.math.BigInteger;
import java.util.Scanner;
public class KaratsubaCalculator {

    // Karatsuba递归算法（两个数位数相同，且是2^n位）
    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
        // 基本情况：个位数直接相乘
        if (x.compareTo(BigInteger.TEN) < 0 && y.compareTo(BigInteger.TEN) < 0) {
            return x.multiply(y);
        }

        // 获取当前位数（字符串长度）
        int n = x.toString().length(); 
        int m = n / 2; // 正好对半分

        BigInteger B = BigInteger.TEN.pow(m);

        // 拆分
        BigInteger x1 = x.divide(B);
        BigInteger x0 = x.mod(B);
        BigInteger y1 = y.divide(B);
        BigInteger y0 = y.mod(B);

        // 三个子乘法
        BigInteger z2 = karatsuba(x1, y1);
        BigInteger z0 = karatsuba(x0, y0);
        BigInteger z1 = karatsuba(x1.add(x0), y1.add(y0));

        // 合并结果
        return z2.multiply(B.pow(2))
                 .add(z1.subtract(z2).subtract(z0).multiply(B))
                 .add(z0);
    }

    public static void main(String[] args) {
        System.out.println("请输入两位乘数:（乘数之间用空格间隔） ");
        Scanner scanner = new Scanner(System.in);
        BigInteger A = scanner.nextBigInteger(); 
        BigInteger B = scanner.nextBigInteger(); 
        BigInteger result = karatsuba(A, B);
        System.out.println("Karatsuba: " + result);
    }
}
