package github.zhp.layer;

import java.util.Collections;
import java.util.List;

/**
 * 主调用程序，这里暂时继续使用数组存放树形结构，
 * 如有需要可以调用{@code TreeBuilder}将数组变换为树形数据结构
 *
 * @author zhoup
 */
public abstract class LayerRecognizer<T> implements LifeCycleContext<T>, ShowContent {
    protected LayerContext<T> layerContext;

    /**
     * 一次传一个元素进来
     *
     * @param candidate 候选元素
     */
    public void fragmentRecognize(T candidate) {
        interiorInitializeContext(Collections.singletonList(candidate), true);
        beforeFind();
        lookupAndSetLevel(candidate);
        afterFind();
    }

    /**
     * 一次传入全部需要识别的候选集
     *
     * @param candidate 需要被识别的集合
     */
    public void recognize(List<T> candidate) {
        interiorInitializeContext(candidate, false);
        ultimateStart();
        for (T elem : candidate) {
            beforeFind();
            lookupAndSetLevel(elem);
            afterFind();
        }
        ultimateEnd();
    }

    /**
     * 内部初始化函数
     */
    private void interiorInitializeContext(List<T> candidate, boolean fragment) {
        if (layerContext == null) {
            layerContext = getContext();
        }
        if (fragment) {
            layerContext.getElements().add(candidate.get(0));
            layerContext.levels = ArrayUtil.resize(layerContext.levels, layerContext.getElements().size());
        } else {
            layerContext.elements = candidate;
            layerContext.levels = new int[candidate.size()];
        }
    }

    /**
     * 查找并设置level
     *
     * @param elem elem
     */
    public void lookupAndSetLevel(T elem) {
        for (int i = layerContext.currentIndex - 1; i >= 0; i--) {
            if (findAndSetLevel(i)) {
                break;
            }
        }
    }

    /**
     * 查找并设置level
     */
    private boolean findAndSetLevel(int i) {
        layerContext.count.incrementAndGet();
        if (i < 0) {
            return false;
        }
        T added = layerContext.elements.get(i);
        int ret = layerContext.getComparator().compare(added, layerContext.getCurrentValue());
        int level = layerContext.levels[i];
        switch (ret) {
            case -1:
                setLevel(layerContext.currentIndex, level - 1);
                return findAndSetLevel(i - 1);
            case 0:
                setLevel(layerContext.currentIndex, level);
                return true;
            case 1:
                setLevel(layerContext.currentIndex, level + 1);
                return true;
            //这里的处理应该比较复杂
            default:
                break;
        }
        return false;
    }


    @Override
    public void ultimateStart() {

    }

    @Override
    public void beforeFind() {
        if (layerContext.currentIndex == 0) {
            setLevel(layerContext.currentIndex, 1);
        }
    }

    @Override
    public void afterFind() {
        layerContext.currentIndex++;
    }

    @Override
    public void ultimateEnd() {

    }

    private void setLevel(int index, int level) {
        layerContext.levels[index] = level;
    }

    /**
     * 将[index1]=level1,[index2]=level2变为
     * [index1]=level2,[index2]=level1
     */
    protected void swapLevel(int index1, int index2, int level1, int level2) {
        setLevel(index1, level2);
        setLevel(index2, level1);
    }

    @Override
    public void show() {
        int[] levels = layerContext.getLevels();
        int min = findMin(levels);
        int addNum = 0;
        if (min < 0) {
            addNum = -min;
        }
        for (int i = 0; i < levels.length; i++) {
            int level = levels[i];
            System.out.println("[" + (i + 1) + "]" + multiply(" ", level + addNum) + layerContext.getElement(i));
        }
    }
}
