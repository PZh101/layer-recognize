package github.zhp.layer;

/**
 * 程序识别整个上下文
 *
 * @author zhoup
 */
public interface LifeCycleContext<E> {
    /**
     * 最开始，所有之前
     */
    void ultimateStart();

    /**
     * 查找之前
     */
    void beforeFind();

    /**
     * 找到之后
     */
    void afterFind();

    /**
     * 整个方法最后
     */
    void ultimateEnd();

    /**
     * 获取LayerContext上下文
     *
     * @return 上下文
     */
    LayerContext<E> getContext();
}
