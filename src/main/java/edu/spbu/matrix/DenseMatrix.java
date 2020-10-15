package edu.spbu.matrix;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Плотная матрица
 */
public class DenseMatrix implements Matrix {
  public double[][] data;

  /**
   * загружает матрицу из файла
   *
   * @param fileName
   */
  public DenseMatrix(String fileName) {
    try {
      File f = new File(fileName);
      Scanner input = new Scanner(f);
      String[] line;//line - массив в виде строки
      ArrayList<Double[]> a = new ArrayList<>();//создаём расширяемый списочный массив
      Double[] tmp = {};//создаём пустой массив tmp, tmp - список
      while (input.hasNextLine()) {
        line = input.nextLine().split(" ");// считываем из файла строку с пробелом в line
        tmp = new Double[line.length];// tmp - массив размера длины line
        for (int i = 0; i < tmp.length; i++) {// в цикле от 0 до размера tmp записываем итый символ line в tmp
          tmp[i] = Double.parseDouble(line[i]); // преобразует тип String к Double
        }
        a.add(tmp);//добавляем tmp к расширяемому списочному массиву a
      }
      double[][] result = new double[a.size()][tmp.length];
      for (int i = 0; i < result.length; i++) {
        for (int j = 0; j < result[0].length; j++) {
          result[i][j] = a.get(i)[j];// в цикле записываем в матрицу результат элемент из списочно-расш массива
        }
      }
      data = result;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public DenseMatrix(double[][] r) {
    data = r;
  }

  /**
   * однопоточное умножение матриц
   * должно поддерживаться для всех 4-х вариантов
   *
   * @param o
   * @return
   */
  @Override
  public DenseMatrix mul(Matrix o) {
    if (o instanceof DenseMatrix)
      return mul((DenseMatrix) o);
    else return null;
  }
  public double[][] transpose () { //транспонирование матриц
    double[][] dT = new double[data[0].length][data.length];
    for (int i = 0; i < data[0].length; i++) {
      for (int j = 0; j < data.length; j++) {
        dT[i][j] = data[j][i];
      }
    }
    return dT;
  }
  private DenseMatrix mul (DenseMatrix d){ //умножение плотной на плотную
    double[][] dT = d.transpose();
    double[][] result = new double[data.length][dT.length];
    for (int i = 0; i < data.length; i++) {
      result[i] = new double[dT.length];
    }

    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < dT.length; j++) {
        for (int k = 0; k < dT[0].length; k++) {
          result[i][j] += data[i][k] * dT[j][k];
        }
      }
    }
    return new DenseMatrix(result);
  }


  /**
   * многопоточное умножение матриц
   *
   * @param o
   * @return
   */
  @Override public Matrix dmul(Matrix o)
  {
    return null;
  }

  /**
   * сравнивает с обоими вариантами
   *
   * @param o
   * @return
   */
  @Override
  public boolean equals(Object o) {
    boolean y = true;
    DenseMatrix tmp = (DenseMatrix) o;
    if (data.length == tmp.data.length && data[0].length == tmp.data[0].length) {
      for (int i = 0; i < data.length; i++) {
        for (int j = 0; j < data[0].length; j++) {
          if (data[i][j] != tmp.data[i][j]) {
            y = false;
          }
        }
      }
    }
    return y;
  }

  public String ToString(double[][] result) {//представляет изменяемую строку(последовательность) символов
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < data[0].length; i++) {
      for (int j = 0; j < data.length; j++) {
        builder.append(result[i][j]).append(" ");
      }
      builder.append("\n");
    }
    System.out.println(builder.toString());
    return builder.toString();
  }
}