package github.zhp.layer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LayerTreeTest {

    @Test
    void buildTree() {
        LayerTree<Character> layerTree = new LayerTree<>();
        String str = "2A1BACD";
        char[] chars = str.toCharArray();
        List<Character> characters = new ArrayList<>();
        for (char aChar : chars) {
            characters.add(aChar);
        }
        layerTree.buildTree(characters, new int[]{0, 1, 0, 2, 1, 2, 2});
        LayerNode root = layerTree.getRoot();
        System.out.println("Over...");
        assertEquals(characters.size(), layerTree.getLayerNodes().length);
        assertEquals(3, root.children.size());
        assertEquals(0, layerTree.getLayerNodes()[0].type);
        assertEquals(1, layerTree.getLayerNodes()[0].currentLevel);
    }
}