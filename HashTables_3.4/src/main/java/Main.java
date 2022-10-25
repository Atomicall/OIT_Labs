import java.security.SecureRandom;
import java.util.Locale;
//Критерий Хи-квадрат.
// Проверить, что при  N > cM значение Xi2 находится от M - sqrt(M) до M + sqrt(M) с вероятностью
// 1 - 1/c

public class Main {
    private static final int COUNT = 200000;
    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        SeparateChainingHashST<Integer, Integer> st = new SeparateChainingHashST<>();
        for (int i = 0; i < COUNT; i++) {
            int randomValue = random.nextInt();
            st.put(randomValue, randomValue);
        }
        // Xi2 = (M/N) * sum ( (fi - N/M)^2 ) i = 0..M-1
        // M - Sqrt(M) < Xi2 < M + Sqrt(M) <=> lowerBound < Xi2 < upperBound
        double lowerBound = st.capacity() - Math.sqrt(st.capacity());
        double upperBound = st.capacity() + Math.sqrt(st.capacity());
        // c = N/M
        double c = (double) st.size() / st.capacity();
        // вероятность нахождение lowerBound < Xi2 < upperBound
        double chance = 1 - 1 / c;

        /*
        // Debug info
        System.out.println("M: " + st.capacity() + " Sqrt: " + Math.sqrt(st.capacity()));
        System.out.println("N: " + st.size());
        System.out.println("Lower "+ lowerBound + " Upper "+ upperBound);
        System.out.println("c " + c);
        System.out.println("chance "+ chance);
        System.out.println("sum " + sum);
        */
        System.out.println("Надо сделать как минимум 20 попыток");
        System.out.println("С примерной вероятностью (1 - M/N) = " + chance + "Выполнится:");
        System.out.println("M - Sqrt(M) < Xi2 < M + Sqrt(M) : ");
        double xi2 = st.getXi2();
        System.out.printf("%f <= %f <= %f \nСоотношение выполнилось ? %s", lowerBound, xi2, upperBound,
                Boolean.valueOf(xi2 >= lowerBound && xi2 <= upperBound).toString().toUpperCase(Locale.ROOT)
        );
    }
}

