package github.zhp.layer.impl;

import github.zhp.layer.LayerContext;
import github.zhp.layer.LayerRecognizer;

/**
 * layer识别的Demo
 *
 * @author zhoup
 */
public class LayerDemo extends LayerRecognizer<Character> {
    LayerContext<Character> context = new LayerContext<Character>();

    @Override
    public boolean brother(Character elder, Character younger) {
        return elder.equals(younger);
    }

    @Override
    public boolean fatherAndSon(Character suspectedFather, Character suspectedSon) {
        return suspectedFather < suspectedSon;
    }

    @Override
    public void ultimateEnd() {
        System.out.println("recognize successful.");
    }

    @Override
    public LayerContext<Character> getContext() {
        return context;
    }

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

    private int findMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int j : arr) {
            if (j < min) {
                min = j;
            }
        }
        return min;
    }

    private String multiply(String str, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}
