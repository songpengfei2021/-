package algorithm;

import java.util.concurrent.ExecutionException;

public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high); // 分区，得到基准元素的位置
            quickSort(arr, low, pivot - 1); // 对左子序列进行递归排序
            quickSort(arr, pivot + 1, high); // 对右子序列进行递归排序
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low]; // 选择第一个元素为基准元素
        while (low < high) {
            while (low < high && arr[high] >= pivot) { // 从右往左找到第一个小于pivot的元素
                high--;
            }
            arr[low] = arr[high]; // 将该元素移到左侧空位中

            while (low < high && arr[low] <= pivot) { // 从左往右找到第一个大于pivot的元素
                low++;
            }
            arr[high] = arr[low]; // 将该元素移到右侧空位中
        }
        arr[low] = pivot; // 将基准元素填到最后一个空缺的位置中
        return low; // 返回基准元素的位置
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 4, 2, 6};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
