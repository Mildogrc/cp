import java.math.BigInteger;

class Medium1 {
    public int numSteps(String s) {
        BigInteger num = new BigInteger(s, 2);
        int count = 0;
        while (num.compareTo(BigInteger.ONE) != 0) {
            if (num.mod(BigInteger.TWO).compareTo(BigInteger.ONE) == 0) {
                num = num.add(BigInteger.ONE);
            } else {
                num = num.divide(BigInteger.TWO);
            }
            count++;
        }
        return count;
    }
}
