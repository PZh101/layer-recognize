package github.zhp.layer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LayerTreeTest {

    @Test
    void buildTree() {
        String str = "2A1BACD";
        char[] chars = str.toCharArray();
        List<Character> characters = new ArrayList<>();
        for (char aChar : chars) {
            characters.add(aChar);
        }
        LayerTree<Character> layerTree=LayerTree.buildTree(characters, new int[]{0, 1, 0, 2, 1, 2, 2});
        LayerNode root = layerTree.getRoot();
        System.out.println("Over...");
        DumpLayerTree<Character> show = new DumpLayerTree<>();
        show.show(layerTree);
        assertEquals(characters.size(), layerTree.getLayerNodes().length);
        assertEquals(3, root.children.size());
        assertEquals(0, layerTree.getLayerNodes()[0].type);
        assertEquals(1, layerTree.getLayerNodes()[0].currentLevel);
    }
}