package utils.models;

public class Interval {

    private final int min;
    private final int max;

    public Interval(int min, int max) {
        assert min <= max;
        this.min = min;
        this.max = max;
    }

    public boolean isWithinRange(int value){
        return min <= value && value <= max;
    }
}
