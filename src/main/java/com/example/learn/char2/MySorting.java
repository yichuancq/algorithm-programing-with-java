package com.example.learn.char2;

/**
 * 排序
 *
 * @author yichuan
 */
public class MySorting {

    /**
     * 9.1.1 直接插入排序
     * 直接插入排序（升序）
     *
     * @param keys
     */
    public static void insertSort(int[] keys) {
        System.out.println("直接插入排序（升序）");
        //n-1趟扫描，依次向前插入n-1个数
        for (int i = 1; i < keys.length; i++) {
            //每趟将keys[i]插入到前面排序子序列中
            int temp = keys[i], j;
            //升序
            for (j = i - 1; j >= 0 && temp < keys[j]; j--)
//            for (j=i-1; j>=0 && temp>keys[j]; j--)         //降序
            {
                //将前面较大元素向后移动
                keys[j + 1] = keys[j];
            }
            //temp值到达插入位置
            keys[j + 1] = temp;
            System.out.print("第" + i + "趟 temp=" + temp + "\t");
            //输出排序中间结果，可省略
            Array1.print(keys);
        }
    }

    /**
     * 对象数组的直接插入排序，算法同上。教案
     *
     * @param value
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertSort(T[] value) {
        System.out.println("直接插入排序（升序）");
        //n-1趟扫描
        for (int i = 1; i < value.length; i++) {   //每趟将value[i]插入到前面排序子序列中
            T temp = value[i];
            int j;
            //将前面较大元素向后移动
            for (j = i - 1; j >= 0 && temp.compareTo(value[j]) < 0; j--) {
                value[j + 1] = value[j];
            }
            //temp值到达插入位置
            value[j + 1] = temp;
            System.out.print("第" + i + "趟: ");
            //调用print(Object)输出排序中间结果，可省略
            Array1.print(value);
        }
    }

    /**
     * 希尔排序
     * 希尔排序（升序，增量减半）
     *
     * @param keys
     */
    public static void shellSort(int[] keys) {
        System.out.println("希尔排序（升序）");
        //若干趟，控制增量每趟减半
        for (int delta = keys.length / 2; delta > 0; delta /= 2) {
            //一趟分若干组，每组直接插入排序
            for (int i = delta; i < keys.length; i++) {
                //keys[i]是当前待插入元素
                int temp = keys[i], j;
                //组内直接插入排序（升序），寻找插入位置
                for (j = i - delta; j >= 0 && temp < keys[j]; j -= delta)
//                for (j=i-delta; j>=0 && temp>keys[j]; j-=delta) //组内直接插入排序（降序），寻找插入位置
                {
                    //每组元素相距delta远
                    keys[j + delta] = keys[j];
                }
                //插入元素
                keys[j + delta] = temp;
            }
            System.out.print("delta=" + delta + "  ");
            Array1.print(keys);
        }
    }

    /**
     * 冒泡排序
     *
     * @param keys
     * @param i
     * @param j
     */
    //交换keys[i]与keys[j]元素，i、j范围由调用者控制
    private static void swap(int[] keys, int i, int j) {
        int temp = keys[j];
        keys[j] = keys[i];
        keys[i] = temp;
    }

    //冒泡排序（升序）
    public static void bubbleSort(int[] keys) {
        bubbleSort(keys, true);
    }

    /**
     * //冒泡排序，asc取值true（升序）、false（降序）
     *
     * @param keys
     * @param asc
     */
    public static void bubbleSort(int[] keys, boolean asc) {
        System.out.println("冒泡排序（" + (asc ? "升" : "降") + "序）");
        //是否交换的标记
        boolean exchange = true;
        //有交换时再进行下一趟，最多n-1趟
        for (int i = 1; i < keys.length && exchange; i++) { //假定元素未交换
            exchange = false;
            //一趟比较、交换
            for (int j = 0; j < keys.length - i; j++) {
                //相邻元素比较，若反序，则交换
                if (asc ? keys[j] > keys[j + 1] : keys[j] < keys[j + 1]) {
                    swap(keys, j, j + 1);
                    //有交换
                    exchange = true;
                }
            }
            System.out.print("第" + i + "趟，下标0～" + (keys.length - i) + "，");
            Array1.print(keys);
        }
    }

    /**
     * 快速排序
     * 快速排序（升序）
     *
     * @param keys
     */
    public static void quickSort(int[] keys) {
        System.out.println("快速排序（升序）");
        quickSort(keys, 0, keys.length - 1);
    }

    /**
     * 对存于keys数组begin～end之间的子序列进行一趟快速排序，递归算法
     *
     * @param keys
     * @param begin
     * @param end
     */
    private static void quickSort(int[] keys, int begin, int end) {
        //序列有效
        if (begin >= 0 && begin < keys.length && end >= 0 && end < keys.length && begin < end) {
            //i、j下标分别从子序列的前后两端开始
            int i = begin, j = end;
            //子序列第一个值作为基准值
            int vot = keys[i];
            while (i != j) {
                //（升序）从后向前寻找较小值，不移动与基准值相等元素
                while (i < j && keys[j] >= vot)
                //（降序）从后向前寻找较大值，不移动与基准值相等元素
//                while (i<j && vot>=keys[j])
                {
                    j--;
                }
                if (i < j) {
                    //子序列后端较小元素向前移动
                    keys[i++] = keys[j];
                }
                //（升序）从前向后寻找较大值，不移动与基准值相等元素
                while (i < j && keys[i] <= vot)
                //（降序）从前向后寻找较小值，不移动与基准值相等元素
//               while (i<j && keys[i]>=vot)
                {
                    i++;
                }
                if (i < j) {
                    //子序列前端较大元素向后移动
                    keys[j--] = keys[i];
                }
            }
            //基准值到达最终位置
            keys[i] = vot;
            System.out.print("下标" + begin + "～" + end + "， vot=" + vot + "，  ");
            Array1.print(keys);
            //前端子序列再排序，递归调用
            quickSort(keys, begin, j - 1);
            //后端子序列再排序，递归调用
            quickSort(keys, i + 1, end);
        }
    }

    /***
     * 9.3.1   直接选择排序
     * 直接选择排序（升序）
     * @param keys
     */
    public static void selectSort(int[] keys) {
        System.out.println("直接选择排序（升序）");
        //n-1趟排序
        for (int i = 0; i < keys.length - 1; i++) {
            int min = i;
            //每趟在从keys[i]开始的子序列中寻找最小元素
            for (int j = i + 1; j < keys.length; j++) {
                //（升序）
                if (keys[j] < keys[min]) {
                    //min记住本趟最小元素下标
                    min = j;
                }
            }
            System.out.print("第" + (i + 1) + "趟，下标" + i + "～" + (keys.length - 1) + "，min=" + min + "，");
            //将本趟最小元素交换到前边
            if (min != i) {
                swap(keys, i, min);
            }
            Array1.print(keys);
        }
    }

    /**
     * （第5版）9.3.2   堆排序
     * 堆排序（升序），最大堆
     *
     * @param keys
     */
    public static void heapSort(int[] keys) {
        heapSort(keys, true);
    }

    /**
     * 堆排序，若asc取值为true，升序排序，创建最大堆；否则降序，创建最小堆
     *
     * @param keys
     * @param asc
     */
    public static void heapSort(int[] keys, boolean asc) {
        //创建最小/大堆，根结点值最小/大
        for (int i = keys.length / 2 - 1; i >= 0; i--) {
            sift(keys, i, keys.length - 1, !asc);
        }
        System.out.print("最" + ((!asc) ? "小" : "大") + "堆：");
        Array1.print(keys);
        System.out.println("非递归算法，最小堆？ " + isHeap(keys, true) + "，最大堆？ " + isHeap(keys, false));
        System.out.print("堆排序（" + ((!asc) ? "降" : "升") + "序）：");
        //每趟将最小/大值交换到后面，再调整成最小/大堆
        for (int i = keys.length - 1; i > 0; i--) {
            //交换keys[0]与keys[i]
            swap(keys, 0, i);
            sift(keys, 0, i - 1, !asc);
        }
        Array1.print(keys);
    }

    /**
     * 将keys数组中以parent为根的子树调整成最小/大堆，子序列范围为parent～end。
     *
     * @param keys
     * @param parent
     * @param end
     * @param minheap
     */
    private static void sift(int[] keys, int parent, int end, boolean minheap) {
//        System.out.print("sift  "+parent+".."+end+"  ");
        //child是parent的左孩子
        int child = 2 * parent + 1;
        int value = keys[parent];
        //沿较小/大值孩子结点向下筛选
        while (child <= end) {
            if (child < end && (minheap ? keys[child] > keys[child + 1] : keys[child] < keys[child + 1])) {
                //child记住孩子值较小/大者
                child++;
            }
            //若父母结点值较小/大
            if (minheap ? value > keys[child] : value < keys[child]) {
                //将较小/大孩子结点值上移
                keys[parent] = keys[child];
                //parent、child两者都向下一层
                parent = child;
                child = 2 * parent + 1;
            } else {
                break;
            }
        }
        //当前子树的原根值调整后的位置
        keys[parent] = value;
//        Array1.print(keys);
    }

    /**
     * 思考题9-3】
     * 判断value指定数据序列是否为堆，若minheap取值为true，则最小堆；否则最大堆。非递归算法
     *
     * @param value
     * @param minheap
     * @return
     */
    public static boolean isHeap(int[] value, boolean minheap) {
        //空序列不是堆。若无此句，则空序列是堆，定义不同
        if (value.length == 0) {
            return false;
        }
        //i从最深一棵子树的根结点开始
        for (int i = value.length / 2 - 1; i >= 0; i--) {
            //left是i的左孩子，肯定存在
            int left = 2 * i + 1;
            if (minheap ? (value[i] > value[left] || left + 1 < value.length && value[i] > value[left + 1])
                    : (value[i] < value[left] || left + 1 < value.length && value[i] < value[left + 1])) {
                //根值较大/小时，肯定不是最小/大堆
                return false;
            }
        }
        return true;
    }

    /**
     * 归并排序（升序）
     *
     * @param X
     */
    public static void mergeSort(int[] X) {
        System.out.println("归并排序（升序）");
        //Y数组长度同X数组
        int[] Y = new int[X.length];
        //排序子序列长度，初值为1
        int n = 1;
        while (n < X.length) {
            //一趟归并，将X中若干相邻子序列归并到Y
            mergepass(X, Y, n);
            //子序列长度加倍
            n *= 2;
            if (n < X.length) {
                //一趟归并，将Y中若干相邻子序列再归并到X
                mergepass(Y, X, n);
                n *= 2;
            }
        }
    }

    /**
     * 一趟归并，将X中若干相邻子序列两两归并到Y中，子序列长度为n
     *
     * @param X
     * @param Y
     * @param n
     */
    private static void mergepass(int[] X, int[] Y, int n) {
        System.out.print("子序列长度n=" + n + "  ");
        //将X中若干相邻子序列归并到Y中
        for (int i = 0; i < X.length; i += 2 * n) {
            //一次归并
            merge(X, Y, i, i + n, n);
        }
        Array1.print(Y);
    }

    /**
     * 一次归并（升序）
     * 将X中分别以begin1、begin2开始的两个相邻子序列归并（升序）到Y中，子序列长度为n
     *
     * @param X
     * @param Y
     * @param begin1
     * @param begin2
     * @param n
     */
    private static void merge(int[] X, int[] Y, int begin1, int begin2, int n) {
        int i = begin1, j = begin2, k = begin1;
        //将X中两个相邻子序列归并到Y中
        // （升序）将较小值复制到Y中
        while (i < begin1 + n && j < begin2 + n && j < X.length) {
            if (X[i] < X[j]) {
                Y[k++] = X[i++];
            } else {
                Y[k++] = X[j++];
            }
        }
        //将前一个子序列剩余元素复制到Y中，子序列长度可能不足n
        while (i < begin1 + n && i < X.length) {
            Y[k++] = X[i++];
        }
        //将后一个子序列剩余元素复制到Y中
        while (j < begin2 + n && j < X.length) {
            Y[k++] = X[j++];
        }
    }
}
