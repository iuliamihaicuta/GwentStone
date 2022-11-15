package fileio;

import java.util.Objects;

/**
 * The type Coordinates.
 */
public final class Coordinates {
   private int x, y;

    /**
     * Instantiates a new Coordinates.
     */
    public Coordinates() {

   }

    /**
     * Instantiates a new Coordinates.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinates(final int x, final int y) {
      this.x = x;
      this.y = y;
   }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
      return x;
   }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(final int x) {
      this.x = x;
   }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
      return y;
   }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(final int y) {
      this.y = y;
   }

   @Override
   public String toString() {
      return "Coordinates{"
              + "x="
              + x
              + ", y="
              + y
              + '}';
   }

   @Override
   public boolean equals(final Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Coordinates that = (Coordinates) o;
      return x == that.x && y == that.y;
   }

   @Override
   public int hashCode() {
      return Objects.hash(x, y);
   }
}
