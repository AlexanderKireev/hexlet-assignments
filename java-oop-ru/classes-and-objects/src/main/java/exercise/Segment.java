package exercise;

// BEGIN
public class Segment {
    private Point beginPoint;
    private Point endPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Segment() {
    }

    public Point getMidPoint() {
        Integer midPointX = (beginPoint.getX() + endPoint.getX()) / 2;
        Integer midPointY = (beginPoint.getY() + endPoint.getY()) / 2;
        return new Point(midPointX, midPointY);
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public void setBeginPoint(Point beginPoint) {
        this.beginPoint = beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
// END
