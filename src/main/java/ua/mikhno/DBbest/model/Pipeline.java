package ua.mikhno.DBbest.model;

public class Pipeline {
    private int start;
    private int endPoint;
    private int length;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Pipeline{" +
                "start=" + start +
                ", endPoint=" + endPoint +
                ", length=" + length +
                '}';
    }
}
