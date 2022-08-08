package github.zhp.layer.impl;

import github.zhp.layer.BaseComparator;
import github.zhp.layer.LayerContext;
import github.zhp.layer.LayerRecognizer;

/**
 * layer识别的Demo
 *
 * @author zhoup
 */
public class CharLayerRecognizer extends LayerRecognizer<Character> {
    LayerContext<Character> context = new LayerContext<>(new CustomCharacterComparator());

    private static class CustomCharacterComparator extends BaseComparator<Character> {
        @Override
        public boolean brother(Character elder, Character younger) {
            return elder.equals(younger);
        }

        @Override
        public boolean fatherAndSon(Character suspectedFather, Character suspectedSon) {
            return suspectedFather < suspectedSon;
        }
    }

    @Override
    public void ultimateEnd() {
        System.out.println("recognize successful.");
    }

    @Override
    public LayerContext<Character> getContext() {
        return context;
    }
}
