package github.zhp.layer;

/**
 * @author zhoup
 */
public interface ShowContent {
    /**
     * 显示具体的内容
     */
    void show();

    /**
     * 做字符的乘法
     * 例如：
     * [A,2]->AA
     *
     * @param str 字符串
     * @param len 长度
     * @return 做完乘法之后的字符串
     */
    default String multiply(String str, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 查找数组中最小的值
     *
     * @param arr 数组
     * @return 最小值
     */
    default int findMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int j : arr) {
            if (j < min) {
                min = j;
            }
        }
        return min;
    }
}
