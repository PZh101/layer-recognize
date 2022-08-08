package github.zhp.layer;

import java.util.List;

/**
 * 层级树，用于配合LayerContext生成一个棵树
 *
 * @author zhoup
 */
public class LayerTree<T> {
    /**
     * 根节点
     */
    private LayerNode root;
    /**
     * 所有的节点
     */
    private LayerNode[] layerNodes;
    private List<T> elements;
    private int[] levels;

    /**
     * 构建树
     *
     * @param context 上下文
     */
    public void buildTree(LayerContext<T> context) {
        buildTree(context.getElements(), context.getLevels());
    }

    /**
     * 构建树
     *
     * @param elements 要素列表
     * @param levels   要素等级数组
     */
    public void buildTree(List<T> elements, int[] levels) {
        this.elements = elements;
        this.levels = levels;
        root = new LayerNode();
        //假设所有的树都是从一个level顶点开始的
        int unZeroIndex = 0;
        for (int j = 0; j < levels.length; ++j) {
            if (levels[j] != 0) {
                unZeroIndex = j;
                break;
            }
        }
        //用于方便查找的，又是树，但每个树又对应数组中的元素
        LayerNode[] rank = ArrayUtil.createArray(new LayerNode[0], levels.length, LayerNode.class);
        if (unZeroIndex != 0) {
            for (int i = 0; i < unZeroIndex; i++) {
                LayerNode node = new LayerNode();
                node.index = i;
//                node.currentLevel = levels[i];
                node.currentLevel = levels[unZeroIndex];
                node.parent = root;
                rank[i] = node;
                root.children.add(node);
            }
        }
        for (int k = 0; k < levels.length; k++) {
            if (levels[k] == levels[unZeroIndex]) {
                buildNode(k, root, rank);
            }
        }
        this.layerNodes = rank;
    }

    /**
     * 此方法可以优化
     *
     * @param headIndex  当前节点的索引
     * @param fatherNode 父节点
     * @param res        存放结果的地方
     * @return layerNode
     */
    private LayerNode buildNode(int headIndex, LayerNode fatherNode, LayerNode[] res) {
        LayerNode node = new LayerNode();
        node.index = headIndex;
        node.currentLevel = levels[headIndex];
        node.type = 1;
        int p = headIndex;
        //找value节点
        while (++p < elements.size() - 1) {
            if (levels[p] != 0) {
                break;
            }
            LayerNode value = new LayerNode();
            value.index = p;
            value.currentLevel = levels[headIndex] + 1;
            value.parent = fatherNode;
            node.children.add(value);
        }
        node.parent = fatherNode;
        //自动添加的虚拟根节点的children需要包含真正的根节点
        if (fatherNode.parent == null) {
            fatherNode.children.add(node);
        }
        res[headIndex] = node;
        //找子节点，待优化的地儿，可以跳过部分循环
        while (p < elements.size()) {
            if (levels[p] <= levels[headIndex]) {
                break;
            }
            if (levels[p] == levels[headIndex] + 1) {
                node.children.add(buildNode(p, node, res));
            }
            p++;
        }
        return node;
    }

    public LayerNode getRoot() {
        return root;
    }

    public LayerNode[] getLayerNodes() {
        return layerNodes;
    }

    public List<T> getElements() {
        return elements;
    }

    public int[] getLevels() {
        return levels;
    }
}
