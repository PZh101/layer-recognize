package github.zhp.layer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 打印LayerTree生成的结构
 *
 * @param <T> LayerTree中的类型
 * @author zhoup
 */
public class DumpLayerTree<T> {
    public static <T> DumpLayerTree<T> getInstance() {
        return new DumpLayerTree<>();
    }

    public void show(LayerTree<T> tree) {
        dumpList(tree);
        dumpTree(tree);
    }

    public void dumpList(LayerTree<T> tree) {
        System.out.println("==================列表形状====================");
        for (LayerNode root : tree.getRoots()) {
            showList(tree, root, 1);
        }
    }

    /**
     * 获得树的形状，即宽度和高度
     */
    private LinkedList<Integer> computeSize(LayerTree<T> tree) {
        LinkedList<Integer> deepAndWidth = new LinkedList<>();
        //高度
        deepAndWidth.add(0);
        //宽度
        deepAndWidth.add(0);
        List<LayerNode> roots = tree.getRoots();
        updateWidth(deepAndWidth, getWidth(roots));
        for (LayerNode root : roots) {
            countDeepAndWidth(root, deepAndWidth, 1);
        }
        return deepAndWidth;
    }

    private void countDeepAndWidth(LayerNode node, LinkedList<Integer> count, int deep) {
        count.set(0, deep);
        if (node.children != null) {
            updateWidth(count, getWidth(node.children));
            for (LayerNode child : node.children) {
                countDeepAndWidth(child, count, deep + 1);
            }
        }
    }

    private void updateWidth(LinkedList<Integer> count, int newValue) {
        if (count.getLast() < newValue) {
            count.set(1, newValue);
        }
    }


    private int getWidth(List<LayerNode> nodes) {
        if (nodes != null) {
            return nodes.stream().map(n -> n.children).filter(Objects::nonNull).mapToInt(List::size).sum();
        }
        return 0;
    }

    public void dumpTree(LayerTree<T> treeBuilder) {
        System.out.println("=================树状结构图===================");
        LinkedList<Integer> computeSize = computeSize(treeBuilder);
        Integer deep = computeSize.getFirst();
        Integer width = computeSize.getLast();
        int sum = width * 5;
        List<LayerNode> tempNodes = treeBuilder.getRoots();
        while (deep > 0) {
            LayerNode father = tempNodes.get(0).parent;
            int fatherChildrenSize = 1;
            if (father != null) {
                fatherChildrenSize = father.children != null ? father.children.size() : 0;
            }
            for (int i1 = 0; i1 < tempNodes.size(); i1++) {
                if (fatherChildrenSize <= 0) {
                    father = tempNodes.get(i1).parent;
                    //更新大小
                    fatherChildrenSize = father.children != null ? father.children.size() : 0;
                    System.out.print("|");
                }
                int index = sum / (tempNodes.size() - i1);
                for (int j = 0; j < index; j++) {
                    System.out.print(" ");
                }
                System.out.print(treeBuilder.getElements().get(tempNodes.get(i1).index));
                fatherChildrenSize--;
            }
            System.out.println();
            tempNodes = tempNodes.stream().map(n -> n.children).filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());
            deep--;
        }
    }

    public void showList(LayerTree<T> treeBuilder, LayerNode root, int c) {
        for (int i = 0; i < c; i++) {
            System.out.print(" ");
        }
        Map<Integer, List<LayerNode>> integerListMap = root.groupByType();
        if (root.index != -1 && integerListMap.get(0) != null && integerListMap.get(0).size() > 0) {
            System.out.print(treeBuilder.getElements().get(root.index) + ":");
            for (int i = 0; i < integerListMap.get(0).size(); i++) {
                System.out.print(treeBuilder.getElements().get(integerListMap.get(0).get(i).index));
                if (i != integerListMap.get(0).size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        } else {
            System.out.println(treeBuilder.getElements().get(root.index));
        }
        if (integerListMap.get(1) != null && integerListMap.get(1).size() > 0) {
            for (LayerNode child : integerListMap.get(1)) {
                showList(treeBuilder, child, c + 1);
            }
        }
    }
}
