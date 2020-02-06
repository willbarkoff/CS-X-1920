package com.williambarkoff.csx.projectile;

public class Particle {
    private double x;
    private double y;

    private double vx;
    private double vy;

    private double ax0;
    private double ay0;

    private double ax;
    private double ay;

    private double xBeta;
    private double yBeta;

    private double mass;

    /**
     * Creates a new particle
     *
     * @param x0    the initial x position of the particle
     * @param y0    the initial y position of the particle
     * @param vx0   the initial x velocity of the particle
     * @param vy0   the initial y velocity of the particle
     * @param ax0   the initial x acceleration of the particle
     * @param ay0   the initial
     * @param xBeta the β value of the particle in the x direction.
     * @param yBeta the β value of the particle in the y direction.
     * @param mass  the mass of the particle
     */
    public Particle(double x0, double y0, double vx0, double vy0, double ax0, double ay0, double xBeta, double yBeta, double mass) {
        this.x = x0;
        this.y = y0;
        this.vx = vx0;
        this.vy = vy0;
        this.ax0 = ax0;
        this.ay0 = ay0;
        this.ax = ax0;
        this.ay = ay0;
        this.xBeta = xBeta;
        this.yBeta = yBeta;
        this.mass = mass;
    }

    /**
     * Creates a new particle. The initial accelerations are assumed to be 0.
     *
     * @param x0   the initial x position of the particle
     * @param y0   the initial y position of the particle
     * @param vx0  the initial x velocity of the particle
     * @param vy0  the initial y velocity of the particle
     * @param mass the mass of the particle
     */
    public Particle(double x0, double y0, double vx0, double vy0, double mass) {
        this.x = x0;
        this.y = y0;
        this.vx = vx0;
        this.vy = vy0;
        this.ax = 0;
        this.ay = 0;
        this.ax0 = 0;
        this.ay0 = 0;
        this.xBeta = 0;
        this.yBeta = 0;
        this.mass = mass;
    }

    /**
     * Creates a new particle. The initial accelerations and velocities are assumed to be 0.
     *
     * @param x0   the initial x position of the particle
     * @param y0   the initial y position of the particle
     * @param mass the mass of the particle
     */
    public Particle(double x0, double y0, double mass) {
        this.x = x0;
        this.y = y0;
        this.vx = 0;
        this.vy = 0;
        this.ax = 0;
        this.ay = 0;
        this.ax0 = 0;
        this.ay0 = 0;
        this.xBeta = 0;
        this.yBeta = 0;
        this.mass = mass;
    }

    /**
     * Creates a new particle at (0, 0). The initial accelerations and velocities are assumed to be 0.
     *
     * @param mass The mass of the particle
     */
    public Particle(double mass) {
        this.x = 0;
        this.y = 0;
        this.vx = 0;
        this.vy = 0;
        this.ax = 0;
        this.ay = 0;
        this.ax0 = 0;
        this.ay0 = 0;
        this.xBeta = 0;
        this.yBeta = 0;
        this.mass = mass;
    }

    /**
     * Updates the positions, accelerations, and velocities of the particle
     *
     * @param deltaT time since the last step
     */
    public void step(double deltaT) {
        double aXOld = this.ax;
        double vXOld = this.vx;

        ax = (mass * ax0 - xBeta * vx) / mass;
        vx = vXOld + ((aXOld + ax) / 2) * deltaT;
        x = x + (vXOld + vx) / 2 * deltaT;

        double aYOld = this.ay;
        double vYOld = this.vy;

        ay = (mass * ay0 - yBeta * vy) / mass;
        vy = vYOld + ((aYOld + ay) / 2) * deltaT;
        y = y + (vYOld + vy) / 2 * deltaT;
    }

    /**
     * Gets the x position of the particle
     *
     * @return x position of the particle
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y position of the particle
     *
     * @return y position of the particle
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the x velocity of the particle
     *
     * @return x velocity of the particle
     */
    public double getVx() {
        return vx;
    }

    /**
     * Gets the y velocity of the particle
     *
     * @return y velocity of the particle
     */
    public double getVy() {
        return vy;
    }

    /**
     * Gets the x acceleration of the particle
     *
     * @return x acceleration of the particle
     */
    public double getAx() {
        return ax;
    }

    /**
     * Gets the y acceleration of the particle
     *
     * @return y acceleration of the particle
     */
    public double getAy() {
        return ay;
    }

    public double getxBeta() {
        return xBeta;
    }

    public void setxBeta(double xBeta) {
        this.xBeta = xBeta;
    }

    public double getyBeta() {
        return yBeta;
    }

    public void setyBeta(double yBeta) {
        this.yBeta = yBeta;
    }
}
