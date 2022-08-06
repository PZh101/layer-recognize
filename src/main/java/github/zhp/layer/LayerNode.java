package github.zhp.layer;

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
     * 当前节点的类型
     */
    int type;
    /**
     * 当前节点在原集合中的索引
     */
    int index;
    /**
     * 儿子节点
     */
    List<LayerNode> children;

    public Map<Integer, List<LayerNode>> groupByType() {
        Objects.requireNonNull(children);
        return children.stream().collect(Collectors.groupingBy(lnode -> lnode.type));
    }
}
