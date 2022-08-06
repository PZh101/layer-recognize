package github.zhp.layer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 上下文
 *
 * @author zhp
 */
public class LayerContext<E> {
    List<E> elements = new ArrayList<>();
    int[] levels;

    int currentIndex;
    /**
     * 用于计数
     */
    AtomicInteger count = new AtomicInteger(0);
    /**
     * 用于等级识别的比较器
     */
    Comparator<E> comparator;

    public LayerContext(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 将{@code start}到{@code end}区间非零的element的级别都加上{@code level}
     * [start,end)
     *
     * @param start 开始坐标
     * @param end   结束坐标
     * @param level 要加上的级别
     */
    public void upgrade(int start, int end, int level) {
        if (end < start || start < 0) {
            return;
        }
        int i = start;
        while (i < end) {
            if (levels[i] != 0) {
                levels[i] += level;
            }
            i++;
        }
    }

    /**
     * 将{@code start}的等级和{@code start}所有子element的等级都升{@code level}级
     *
     * @param start 开始坐标
     * @param level 要加上的级别
     */
    public void upgrade(int start, int level) {
        if (start < 0 || levels[start] == 0) {
            return;
        }
        int endLevel = levels[start];
        for (int i = start; i < levels.length; i++) {
            if (i != start && levels[i] == endLevel) {
                break;
            }
            if (levels[i] != 0) {
                levels[i] += level;
            }
        }
    }

    /**
     * 将{@code start}到{@code end}区间非零的element的级别都减去{@code level}
     * [start,end)
     *
     * @param start 开始坐标
     * @param end   结束坐标
     * @param level 要加上的级别
     */
    public void demotion(int start, int end, int level) {
        if (end < start || start < 0) {
            return;
        }
        int i = start;
        while (i < end) {
            if (levels[i] != 0) {
                levels[i] -= level;
            }
            i++;
        }
    }

    /**
     * 将{@code start}的等级和{@code start}所有子element的等级都降{@code level}级
     *
     * @param start 开始坐标
     * @param level 要加上的级别
     */
    public void demotion(int start, int level) {
        if (start < 0 || levels[start] == 0) {
            return;
        }
        int endLevel = levels[start];
        for (int i = start; i < levels.length; i++) {
            if (i != start && levels[i] == endLevel) {
                break;
            }
            if (levels[i] != 0) {
                levels[i] -= level;
            }
        }
    }

    public List<E> getLevelAndSub(int start, int level) {
        List<E> res = new ArrayList<>();
        if (start < 0 || levels[start] == 0) {
            return res;
        }
        int endLevel = levels[start];
        for (int i = start; i < levels.length; i++) {
            if (i != start && levels[i] == endLevel) {
                break;
            }
            if (levels[i] != 0) {
                res.add(elements.get(i));
            }
        }
        return res;
    }

    public E getFirstByLevel(int level) {
        if (levels != null && levels.length != 0) {
            for (int i = 0; i < levels.length; i++) {
                if (levels[i] == level) {
                    return elements.get(i);
                }
            }
        }
        return null;
    }

    public int getLastNodeIndex() {
        if (levels != null && levels.length != 0) {
            for (int i = levels.length - 1; i >= 0; i--) {
                if (levels[i] != 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    public E getLastNode() {
        int lastNodeIndex = getLastNodeIndex();
        if (lastNodeIndex != -1) {
            return elements.get(lastNodeIndex);
        }
        return null;
    }

    public E getElement(int i) {
        return elements.get(i);
    }

    public E getCurrentValue() {
        return elements.get(currentIndex);
    }

    public List<E> getElements() {
        return elements;
    }

    public void setElements(List<E> elements) {
        this.elements = elements;
    }

    public int[] getLevels() {
        return levels;
    }

    public void setLevels(int[] levels) {
        this.levels = levels;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public Comparator<E> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }
}
