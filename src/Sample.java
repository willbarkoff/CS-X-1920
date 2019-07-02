import org.dalton.polyfun.Polynomial;

import org.opensourcephysics.frames.DisplayFrame;

/**
 * Sample class to show off polyfun, OSP and testing.
 */
public class Sample {
    public static void main(String[] args) {
        // Polyfun
        Polynomial polynomial = new Polynomial(2);
        System.out.print(polynomial.times(3));

        // OSP
        DisplayFrame displayFrame = new DisplayFrame("my frame");
        displayFrame.setVisible(true);
    }

    /**
     * Sample method to show off how to write tests and how to comment (like this).
     * Go to test/ folder to see how to test this method.
     *
     * @param a first num to add
     * @param b second num to add
     * @return the sum
     */
    public static int add(int a, int b) {
        return a + b;
    }
}
