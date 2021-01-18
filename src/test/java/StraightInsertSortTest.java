import org.junit.Test;

/**
 * 直接插入排序
 */
public class StraightInsertSortTest {

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
     * 直接插入排序
     * 1、从第一个元素开始，该元素可以认为已经被排序；
     * 2、取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 3、如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 4、重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 5、将新元素插入到该位置后；
     * 重复步骤2~5。
     *
     * @param arrays
     * @return
     */
    public Integer[] sort(Integer[] arrays) {
        if (arrays.length == 0) {
            return arrays;
        }
        //
        for (int i = 1; i < arrays.length; i++) {
            Integer temp = arrays[i], j;
            for (j = i - 1; j >= 0 && temp < arrays[j]; j--) {
                //将前面较大的元素后移
                arrays[j + 1] = arrays[j];
            }
            //temp 值达到插入的位置
            arrays[j + 1] = temp;
            System.out.println("第" + i + "趟 ,temp=" + temp);
            this.showData(arrays);
        }
        return arrays;
    }

    @Test
    public void test() {
        //直接出入排序是最稳定的
        StraightInsertSortTest insertSort = new StraightInsertSortTest();
        System.out.println();
        Integer[] arrays = {2, 5, 4, 3, 1, 6, 9, 8, 7};
        insertSort.showData(arrays);
        insertSort.sort(arrays);
    }

}
