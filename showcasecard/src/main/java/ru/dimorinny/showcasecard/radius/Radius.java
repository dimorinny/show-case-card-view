package ru.dimorinny.showcasecard.radius;

public class Radius implements ShowCaseRadius {

    private float radius = 0;

    public Radius(float radius) {
        this.radius = radius;
    }

    @Override
    public float getRadius() {
        return this.radius;
    }
}
