package exercise;

// BEGIN
public final class Segment {
    private Point beginPoint;
    private Point endPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Segment() {
    }

    public Point getBeginPoint() {
        return this.beginPoint;
    }

    public Point getEndPoint() {
        return this.endPoint;
    }

    public Point getMidPoint() {
        int x = (Math.abs(endPoint.getX()) - Math.abs(beginPoint.getX())) / 2;
        int y = (Math.abs(endPoint.getY()) - Math.abs(beginPoint.getY())) / 2;
        return new Point(x, y);
    }
}
// END
