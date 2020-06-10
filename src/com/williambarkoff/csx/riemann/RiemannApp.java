package com.williambarkoff.csx.riemann;

import com.williambarkoff.csx.util.Function;
import com.williambarkoff.csx.util.MathFun;
import com.williambarkoff.csx.util.StringFun;
import org.dalton.polyfun.Polynomial;

/**
 * Riemann app is a runnable OpenSourcePhysics app that plots and computes multiple Riemann sums.
 *
 * @author William Barkoff
 */
public class RiemannApp {
    public static void main(String[] args) throws InterruptedException {
        double[] pdef = new double[]{0, 0, 1};

        Polynomial p = new Polynomial(pdef);
        double expected = -40;
        double left = 0;
        double right = 6;


//        Polynomial p = new Function(Math::sin, "sin(x)");
        Polynomial circle = new Function(x -> Math.sqrt(1 - Math.pow(x, 2)), "√1̅-̅X̅^̅2̅");
//        System.out.println(p.toString());
//        int subs = Integer.MAX_VALUE;

        int subs = 3;
        System.out.println("-------------------------------------------------------");
        System.out.println("|" + StringFun.center(p.toString(), 53) + "|");
        System.out.println("|" + StringFun.center("L: " + left + " R: " + right + " S: " + subs, 53) + "|");
        System.out.println("-------------------------------------------------------");


        AbstractRiemann[] rules = new AbstractRiemann[]{
                new LeftHandRule(),
                new RightHandRule(),
                new MidpointRule(),
                new MaximumRule(),
                new MinimumRule(),
                new RandomRule(),
                new TrapezoidRule()
        };

        Thread[] threads = new Thread[rules.length];
        RiemannWorker[] workers = new RiemannWorker[rules.length];

        for (int i = 0; i < rules.length; i++) {
            RiemannWorker worker = new RiemannWorker(rules[i], subs, new Polynomial(pdef), left, right, expected);
            Thread thread = new Thread(worker);
            thread.start();
            threads[i] = thread;
            workers[i] = worker;
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.printf("| %-20s | %-13s   | %-4s  |\n", "RULE", "RESULT", "%ERR     ");
        System.out.println("-------------------------------------------------------");
        for (RiemannWorker worker : workers) {
            double result = worker.getResult();
            String ruleName = worker.getRule().getClass().getSimpleName();
            double pctErr = MathFun.PercentError(expected, result) * 100;
            System.out.printf("| %-20s | %13.7f   | %8.2f%%  |\n", ruleName, result, pctErr);
        }
        System.out.println("-------------------------------------------------------");

        double sca = new LeftHandRule().rs(circle, -1, 1, Integer.MAX_VALUE);
        double pi = sca * 2;
        System.out.println("\n\n");
        System.out.println("-------------------------------------------------------");
        System.out.println("|" + StringFun.center("Pi Calculation", 53) + "|");
        System.out.println("-------------------------------------------------------");
        System.out.printf("| %-20s | %-13s   | %-4s | \n", "CALCULATION", "RESULT", "%ERR      ");
        System.out.println("-------------------------------------------------------");
        System.out.printf("| %-20s | %13.10f   | %8.2f%%  | \n", "π", pi, MathFun.PercentError(Math.PI, pi) * 100.0);
        System.out.println("-------------------------------------------------------");
    }
}
