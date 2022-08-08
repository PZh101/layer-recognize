package github.zhp.layer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 节点
 *
 * @author zhoup
 */
public class LayerNode {
    /**
     * 父节点
     */
    LayerNode parent;
    /**
     * 当前节点的类型
     * 0-value
     * 1-node
     */
    int type;
    /**
     * 当前节点在原集合中的索引
     */
    int index = -1;
    /**
     * 儿子节点
     */
    List<LayerNode> children = new ArrayList<>();

    int currentLevel;

    public Map<Integer, List<LayerNode>> groupByType() {
        Objects.requireNonNull(children);
        return children.stream().collect(Collectors.groupingBy(lnode -> lnode.type));
    }

    public LayerNode getParent() {
        return parent;
    }

    public int getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public List<LayerNode> getChildren() {
        return children;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}
