public class SortTest {

    /**
     * 二分查找
     * 又叫折半查找，要求待查找的序列有序。每次取中间位置的值与待查关键字比较，
     * 如果中间位置 的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小，
     * 则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字。
     *
     * @param array
     * @param a
     * @return
     */
    public static int biSearch(int[] array, int a) {
        int lo = 0;
        int hi = array.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) / 2;//中间位置
            if (array[mid] == a) {
                return mid + 1;
            } else if (array[mid] < a) { //向右查找 lo=mid+1;
            } else { //向左查找 hi=mid-1;
            }
        }
        return -1;
    }

    /**
     * 冒泡排序算法
     * (1)比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
     * (2)这样对数组的第 0 个数据到 N-1 个数据进行一次遍历后，最大的一个数据就“沉”到数组第 N-1 个位置。
     * (3)N=N-1，如果 N 不为 0 就重复前面二步，否则排序完成。
     *
     * @param a
     * @param n
     */
    public static void bubbleSort(int[] a, int n) {
        int i, j = 0;
        for (i = 0; i < n; i++) {//表示 n 次排序过程。 for(j=1; j<n-i; j++){
            if (a[j - 1] > a[j]) {//前面的数字大于后面的数字就交换 //交换 a[j-1]和 a[j]
                int temp;
                temp = a[j - 1];
                a[j - 1] = a[j];
                a[j] = temp;
            }
        }
    }


    /**
     * 插入排序算法
     * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应的位置并插入。 插入排序非常类似于整扑克牌。在开始摸牌时，左手是空的，牌面朝下放在桌上。
     * 接着，一次从 桌上摸起一张牌，并将它插入到左手一把牌中的正确位置上。为了找到这张牌的正确位置，
     * 要将 它与手中已有的牌从右到左地进行比较。无论什么时候，左手中的牌都是排好序的。
     * 如果输入数组已经是排好序的话，插入排序出现最佳情况，其运行时间是输入规模的一个线性函数。
     * 如果输入数组是逆序排列的，将出现最坏情况。平均情况与最坏情况一样，其时间代价是(n2)。
     *
     * @param arr
     */
    public static void insetSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            //插入的数
            int insertVal = arr[i];
            //被插入的位置(准备和前一个数比较)
            int index = i - 1;
            //如果插入的数比被插入的数小
            while (index >= 0 && insertVal < arr[index]) {
                //将把 arr[index] 向后移动
                arr[index + 1] = arr[index];
                //让 index 向前移动
                index--;
            }
            //把插入的数放入合适位置
            arr[index + 1] = insertVal;
        }
    }

    /**
     * 快速排序算法 快速排序的原理:选择一个关键值作为基准值。比基准值小的都在左边序列(一般是无序的)，
     * 比基准值大的都在右边(一般是无序的)。一般选择序列的第一个元素。
     * 一次循环:从后往前比较，用基准值和最后一个值比较，如果比基准值小的交换位置，如果没有 继续比较下一个，直到找到第一个比基准值小的值才交换。
     * 找到这个值之后，又从前往后开始比 较，如果有比基准值大的，交换位置，如果没有继续比较下一个，直到找到第一个比基准值大的 值才交换。直到从前往后的比较索引>从后往前比较的索引，结束第一次循环，此时，对于基准值 来说，左右两边就是有序的了。
     *
     * @param a
     * @param low
     * @param high
     */
    public void sort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];
        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key)
                //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key)
                //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的 值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) sort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        if (end < high) sort(a, end + 1, high);//右边序列。从关键值索引+1 到最后一个 }
    }


    /**
     * 希尔排序算法
     * 基本思想:先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列
     * 中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
     * 1. 操作方法:
     * 选择一个增量序列 t1，t2，...，tk，其中 ti>tj，tk=1;
     * 2. 按增量序列个数 k，对序列进行 k 趟排序;
     * 3. 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进
     * 行直接插入排序。仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长 度。
     */
    private static void shellSort(int[] a) {
        int dk = a.length / 2;
        while (dk >= 1) {
            ShellInsertSort(a, dk);
            dk = dk / 2;
        }
    }

    private static void ShellInsertSort(int[] a, int dk) {
        //类似插入排序，只是插入排序增量是 1，这里增量是 dk,把 1 换成 dk 就可以了
        for (int i = dk; i < a.length; i++) {
            if (a[i] < a[i - dk]) {
                int j;
                int x = a[i];//x 为待插入元素 a[i]=a[i-dk];
                for (j = i - dk; j >= 0 && x < a[j]; j = j - dk) {
                    //通过循环，逐个后移一位找到要插入的位置。
                    a[j + dk] = a[j];
                }
                a[j + dk] = x;//插入
            }
        }
    }

    int[] array = new int[]{73, 22, 93, 43, 55, 14, 28, 65, 39, 81};

    public static void main(String[] args) {
        SortTest sortTest = new SortTest();
        // shellSort(sortTest.array);
        insetSort(sortTest.array);
        for (int i = 0; i < sortTest.array.length; i++) {
            System.out.println(sortTest.array[i]);
        }
    }
}
