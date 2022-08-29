package github.zhp.layer;

import java.util.Comparator;

/**
 * 节点对比
 *
 * @author zhoup
 */
public abstract class BaseComparator<T> implements Comparator<T> {
    /**
     * 比较{@code added}和{@code needBeAdd}的关系
     * <ul>
     *     <li>返回0 <tt>added<tt/>和<tt>needBeAdd</tt>同级</li>
     *     <li>返回1 <tt>added<tt/>是<tt>needBeAdd</tt>上级</li>
     *     <li>返回-1 <tt>added<tt/>是<tt>needBeAdd</tt>下级</li>
     * </ul>
     *
     * @param added     the first object to be compared.
     * @param needBeAdd the second object to be compared.
     * @return [-1|0|1]
     */
    @Override
    public final int compare(T added, T needBeAdd) {
        if (brother(added, needBeAdd)) {
            return 0;
        }
        return boolToInt(fatherAndSon(added, needBeAdd));
    }

    private int boolToInt(boolean bool) {
        return bool ? 1 : -1;
    }

    /**
     * 判断传入的{@code elder}和{@code younger}是否为同级
     *
     * @param elder   先出现的
     * @param younger 后出现的
     * @return bool
     */
    public abstract boolean brother(T elder, T younger);

    /**
     * 判断传入的{@code suspectedFather}是否为{@code suspectedSon}的上一级节点
     *
     * @param suspectedFather father角色的嫌疑犯
     * @param suspectedSon    son角色的嫌疑犯
     * @return bool
     */
    public abstract boolean fatherAndSon(T suspectedFather, T suspectedSon);

    /**
     * 获取当前上下文
     *
     * @return {@code LayerContext}
     */
    public LayerContext<T> getLayerContext() {
        return null;
    }
}
