package com.williambarkoff.csx.projectile.cannonbattle;

import com.williambarkoff.csx.projectile.Particle;

public class Cannon {
    private double muzzleVelocity;
    private double xBeta;
    private double yBeta;
    private double projectileMass;
    private double gravity;

    public Cannon(double muzzleVelocity, double xBeta, double yBeta, double projectileMass, double gravity) {
        this.muzzleVelocity = muzzleVelocity;
        this.xBeta = xBeta;
        this.yBeta = yBeta;
        this.projectileMass = projectileMass;
        this.gravity = gravity;
    }

    public double getMuzzleVelocity() {
        return muzzleVelocity;
    }

    public void setMuzzleVelocity(double muzzleVelocity) {
        this.muzzleVelocity = muzzleVelocity;
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

    public double getProjectileMass() {
        return projectileMass;
    }

    public void setProjectileMass(double projectileMass) {
        this.projectileMass = projectileMass;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
}
