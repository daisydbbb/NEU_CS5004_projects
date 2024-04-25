package project01;
import java.lang.Math;

/**
 * CS5004 Project01: Writing and testing Vector3D class
 * This file is the class of Vector3D
 * Name: Xin Ding
 * Updated: 1/31/2024
 */

/**
 * This is the Vector3D class that has 3 components x, y, z and applies a couple of methods such
 * as getX(), getMagnitude(), multiply() etc.
 */
public class Vector3D {
  private double x;
  private double y;
  private double z;

  /**
   * Vector3D class constructor, takes in x, y, z components
   * @param x component x of the vector
   * @param y component y of the vector
   * @param z component z of the vector
   */
  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  /**
   * Method getX() get the value of component x of the vector
   * @return double type of the this.x value
   */
  public double getX() {
    return this.x;
  }

  /**
   * Method getY() get the value of component y of the vector
   * @return double type of the this.y value
   */
  public double getY() {
    return this.y;
  }

  /**
   * Method getZ() get the value of component z of the vector
   * @return double type of the this.z value
   */
  public double getZ() {
    return this.z;
  }

  /**
   * overwrite the default toString() method
   * @return a new string that is formatted as "(x, y, z)" with 2 decimals of x, y, z values
   */
  @Override
  public String toString() {
    return String.format("(%.2f, %.2f, %.2f)", getX(), getY(), getZ());
  }

  /**
   * @return the magnitude of the vector
   * calculated by adding the square of x, y, z and then take the square root of the result
   */
  public double getMagnitude() {
    return Math.sqrt(Math.pow(getX(), 2)+Math.pow(getY(), 2)+Math.pow(getZ(), 2));
  }


  /**
   * @return a new vector with normalized x, y, z components
   * calculated by dividing each component by the magnitude
   * throw IllegalStateException if magnitude is 0
   */
  public Vector3D normalize() {
    if (getMagnitude() == 0) {
      throw new IllegalStateException("Cannot perform the operation if magnitude is 0.");
    }
    double normalizedX = getX()/getMagnitude();
    double normalizedY = getY()/getMagnitude();
    double normalizedZ = getZ()/getMagnitude();
    return new Vector3D(normalizedX, normalizedY, normalizedZ);
  }

  /**
   * @param vector2, another vector that will have its x,y,z components added to target vector
   * @return a new vector with added x, y, z components
   * calculated by adding each component respectively
   */
  public Vector3D add(Vector3D vector2) {
    return new Vector3D(getX()+vector2.getX(),
            getY()+vector2.getY(), getZ()+vector2.getZ());
  }


  /**
   * @param number, a constant that will be used for multiplication
   * @return a new vector with multiplied x, y, z components
   * calculated by multiplying current component with number
   */
  public Vector3D multiply(double number) {
    return new Vector3D(getX() * number, getY() * number, getZ() * number);
  }

  /**
   * @param vector2, another vector with x,y,z components that will be used for calculation
   * @return the dot product of two vectors
   * calculated by multiplying x, y, z components of 2 vectors respectively, then add them together
   */
  public double dotProduct(Vector3D vector2) {
    //return getX() * vector2.getX() + getY() * vector2.getY() + getZ() * vector2.getZ();
    return this.x * vector2.x + this.y * vector2.y + this.z * vector2.z;
  }


  /**
   * Asked ChatGPT to write the method that returns the angle between 2 vectors
   * @param vector2, another vector with x,y,z components that will be used for calculation
   * @return the angle between 2 vectors in degrees
   * throw error if magnitude of either vector is 0
   */
  public double angleBetween(Vector3D vector2) {
    double dotProduct = dotProduct(vector2);
    double magnitude1 = getMagnitude();
    double magnitude2 = vector2.getMagnitude();
    if (magnitude1 == 0 || magnitude2 == 0) {
      throw new IllegalStateException("Cannot perform the operation.");
    }

    double value = dotProduct / (magnitude1 * magnitude2);

    // Clamping the value to the range [-1, 1] to avoid NaN due to floating point inaccuracies
    value = Math.max(-1.0, Math.min(1.0, value));

    double angleInRadians = Math.acos(value);
    return Math.toDegrees(angleInRadians);
  }

  /**
   * Asked ChatGPT to write a Java method that returns the cross product of 2 vectors
   * @param vector2, another vector with x,y,z components that will be used for calculation
   * @return a new vector with cross product of x, y, z components
   */
  public Vector3D crossProduct(Vector3D vector2) {
    double newX = getY()*vector2.getZ() - getZ()*vector2.getY();
    double newY = getZ()*vector2.getX() - getX()*vector2.getZ();
    double newZ = getX()*vector2.getY() - getY()*vector2.getX();
    return new Vector3D(newX, newY, newZ);
  }
}
