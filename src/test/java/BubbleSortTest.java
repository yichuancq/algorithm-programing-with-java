import org.junit.Test;

/**
 * 冒泡排序
 */
public class BubbleSortTest {
    /**
     *
     */
    private void showData(Integer[] arrays) {
        for (Integer data : arrays) {
            System.out.print(" " + data);
        }
        System.out.println();
    }

    /**
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * <p>
     * 冒泡排序的最坏时间复杂度为O(n^2)
     *
     * @param arrays
     */
    private Integer[] sort(Integer[] arrays, boolean ascFlag) {
        //循环步数，从左到右
        for (int i = 0; i < arrays.length - 1; i++) {
            for (int j = i; j >= 0; j--) {
                //排序方式
                if (ascFlag ? (arrays[j + 1] < arrays[j]) : (arrays[j + 1] > arrays[j])) {
                    //交换元素
                    this.swap(arrays, j, j + 1);
                }
            }
//            for (int j = 0; j < arrays.length - 1 - i; j++) {
//                //排序方式
//                if (ascFlag ? (arrays[j + 1] < arrays[j]) : (arrays[j + 1] > arrays[j])) {
//                    //交换元素
//                    this.swap(arrays, j, j + 1);
//                }
//            }
        }
        return arrays;
    }

    /**
     * 交换元素
     *
     * @param data
     * @param i
     * @param j
     */
    private void swap(Integer[] data, int i, int j) {
        Integer temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @Test
    public void test() {
        BubbleSortTest bubbleSortTest = new BubbleSortTest();
        Integer[] arrays = {2, 5, 4, 3, 1, 6, 9, 8, 7};
        bubbleSortTest.showData(arrays);
        //排序
        System.out.println("开始排序");
        //升序
        Integer result[] = bubbleSortTest.sort(arrays, true);
        for (Integer integer : result) {
            System.out.print(" " + integer);
        }
        System.out.println();
        System.out.println("开始排序");
        //降序
        Integer result2[] = bubbleSortTest.sort(arrays, false);
        for (Integer integer : result2) {
            System.out.print(" " + integer);
        }
    }

}
