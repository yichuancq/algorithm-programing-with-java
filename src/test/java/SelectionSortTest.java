import org.junit.Test;

public class SelectionSortTest {

    /**
     * @param array
     * @return
     */
    public Integer[] selectionSort(Integer[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) //找到最小的数
                    minIndex = j; //将最小数的索引保存
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
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
        SelectionSortTest sortTest = new SelectionSortTest();
        Integer[] arrays = {2, 5, 4, 3, 1, 6, 9, 8, 7};
        sortTest.showData(arrays);
        //排序
        System.out.println("开始排序");
        Integer result[] = sortTest.selectionSort(arrays);
        for (Integer integer : result) {
            System.out.print(" " + integer);
        }
    }

}
