import java.security.SecureRandom;
//Критерий Хи-квадрат.
// Проверить, что при  N > cM значение Xi2 находится от M - sqrt(M) до M + sqrt(M) с вероятностью
// 1 - 1/c

public class Main {
    private static final int valuesCount = 200000;
    private static final int runsCount = 50;
    private static final SecureRandom random = new SecureRandom();
    private static int successCounter;
    private static double calculatedChance;

    public static void generateRandomValues(int valCount, SeparateChainingHashST<Integer, Integer> st) {
        LehmerRandomNumberGenerator rng = new LehmerRandomNumberGenerator(7);
        for (int i = 0; i < valCount; i++) {
//            int randomValue = random.nextInt();
//            st.put(randomValue, randomValue);
            int randomValue = rng.next();
            st.put(randomValue, randomValue);
        }
    }

    public static void main(String[] args) {
        for (int runsCounter = 0; runsCounter < runsCount; runsCounter++) {
            SeparateChainingHashST<Integer, Integer> st = new SeparateChainingHashST<>();
            generateRandomValues(valuesCount, st);
            // Xi2 = (M/N) * sum ( (fi - N/M)^2 ) i = 0..M-1
            // M - Sqrt(M) < Xi2 < M + Sqrt(M) <=> lowerBound < Xi2 < upperBound
            double lowerBound = st.capacity() - Math.sqrt(st.capacity());
            double upperBound = st.capacity() + Math.sqrt(st.capacity());
            // c = N/M
            double c = (double) st.size() / st.capacity();
            // вероятность нахождение lowerBound < Xi2 < upperBound
            calculatedChance = 1 - 1 / c;
            double xi2 = st.getXi2();
            if (xi2 >= lowerBound && xi2 <= upperBound) {
                successCounter++;
            }
        }
        System.out.printf("Соотношение выполнилось %d раз из %d\n", successCounter, runsCount);
        System.out.printf("Рассчетная вероятность: %f, практическая вероятность: %f", calculatedChance, (double) successCounter / runsCount);
    }

    public static class LehmerRandomNumberGenerator {
        private int prevLehmerNumber;
        private int M = 31471;
        private int A = 11817;

        public LehmerRandomNumberGenerator() {
        }

        public LehmerRandomNumberGenerator(int initNumber) {
            this.prevLehmerNumber = initNumber;
        }

        public int next() {
            int z1 = ((A * prevLehmerNumber) % M);
            prevLehmerNumber = z1;
            return z1;
        }
    }
}

