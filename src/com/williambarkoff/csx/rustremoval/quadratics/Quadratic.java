package com.williambarkoff.csx.rustremoval.quadratics;

class Quadratic {

        private final double a;
        private final double b;
        private final double c;

        private final double discriminant;

        public Quadratic(double a, double b, double c) {
                this.a = a;
                this.b = b;
                this.c = c;

                this.discriminant = Math.pow(b, 2.0) - (4 * a * c);
        }

        public double getA() {
                return a;
        }

        public double getB() {
                return b;
        }

        public double getC() {
                return c;
        }

        public boolean hasRealRoots() {
                return discriminant >= 0;
        }

        public int getNumberOfRoots() {
                if (discriminant == 0.0) {
                        return 1;
                } else if (discriminant < 0.0) {
                        return 0;
                } else {
                        return 2;
                }
        }

        public double[] getRoots() {
                double root1 = (-b + Math.sqrt(Math.pow(b, 2.0) - 4 * a * c)) / (2 * a);
                double root2 = (-b - Math.sqrt(Math.pow(b, 2.0) - 4 * a * c)) / (2 * a);

                if (root1 != root2) {
                        return new double[]{root1, root2};
                } else return new double[]{root1};
        }

        public double getAxisOfSymmetry() {
                return (-b) / (2 * a);
        }

        public double getExtremeValue() {
                return this.evaluateAt(this.getAxisOfSymmetry());
        }

        public boolean opensUpward() {
                return a > 0;
        }

        public double evaluateAt(Double x) {
                return a * Math.pow(x, 2) + b * x + c;
        }
}