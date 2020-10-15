package edu.spbu.matrix;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;

public class MatrixTest {
  /**
   * ожидается 4 таких теста
   */
  @Test
  public void mulDD() throws Exception {
    try {
      Matrix m1 = new DenseMatrix("./src/main/m1.txt");
      Matrix m2 = new DenseMatrix("./src/main/m2.txt");
      Matrix expected = new DenseMatrix("./src/main/result.txt");
      assertEquals(expected, m1.mul(m2));
      if (expected.equals(m1.mul(m2))) {
        System.out.println("equals is working");
        System.out.println(expected);
      } else {
        System.out.println("equals isn't working");
        System.out.println("answer:");
        System.out.println(m1.mul(m2));
        System.out.println("but expected:");
        System.out.println(expected);
        throw new Exception();
      }
    } catch (FileNotFoundException e) {
      System.out.println("the file wasn't found");
    }
  }
}