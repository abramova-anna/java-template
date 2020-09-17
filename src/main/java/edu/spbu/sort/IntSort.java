package edu.spbu.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by artemaliev on 07/09/15.
 */

public class IntSort {
  // l-левый край, r-правый, m-центр
  static void sort(int[] array, int l, int r) {
    if (l < r) {
      int m = (l + r) / 2;
      // сортируем левую половину массива
      sort(array, l, m);
      // правую
      sort(array, m + 1, r);
      // применяем слияние к отсортированным половинкам
      merge(array, l, m, r);
    }
  }
  static void merge(int[] array, int l, int m, int r) {
    // вычисляем размеры половинок и создаём для них новые массивы
    int n1 = m - l + 1;
    int n2 = r - m;
    int[] arr1 = new int[n1];
    int[] arr2 = new int[n2];
    // заполняем временные масивы
    if (n1 >= 0) System.arraycopy(array, l, arr1, 0, n1);
    for (int j = 0; j < n2; ++j)
      arr2[j] = array[m + 1 + j];
    // выполняем слияние массивов
    int i = 0, j = 0;
    int k = l;
    while (i < n1 && j < n2) {
      if (arr1[i] <= arr2[j]) {
        array[k] = arr1[i];
        i++;
      }
      else {
        array[k] = arr2[j];
        j++;
      }
      k++;
    }
    // если в arr1 остались элементы, копируем их
    while (i < n1) {
      array[k] = arr1[i];
      i++;
      k++;
    }
    // для arr2
    while (j < n2) {
      array[k] = arr2[j];
      j++;
      k++;
    }
  }
  static void sort (int[] array) {
    sort(array, 0, array.length-1);
  }

  public static void sort (List<Integer> list) {
    Collections.sort(list);
  }
}
