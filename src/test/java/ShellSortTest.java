import org.junit.Test;

/**
 * 希尔排序
 */
public class ShellSortTest {
    /**
     * 希尔排序
     * <p>
     * 1、选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
     * 2、按增量序列个数k，对序列进行k 趟排序；
     * 3、每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     * 仅增量因子为1时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * <p>
     *
     * @param array
     * @return
     */
    public Integer[] shellSort(Integer[] array) {
        Integer len = array.length;
        Integer temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                Integer preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }

    /**
     * 显示数据
     *
     * @param arrays
     */
    private void showData(Integer[] arrays) {
        for (Integer data : arrays) {
            System.out.print(" " + data);
        }
        System.out.println();
    }

    @Test
    public void test() {
        ShellSortTest shellSortTest = new ShellSortTest();
        Integer[] arrays = {2, 5, 4, 3, 1, 6, 9, 8, 7};
        shellSortTest.showData(arrays);
        //排序
        System.out.println("开始排序");
        Integer result[] = shellSortTest.shellSort(arrays);
        for (Integer integer : result) {
            System.out.print(" " + integer);
        }
    }
}
