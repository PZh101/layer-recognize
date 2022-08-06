package github.zhp.layer.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LayerDemoTest {
    LayerDemo layerDemo;

    @BeforeEach
    public void init() {
        layerDemo = new LayerDemo();
    }

    @ParameterizedTest
    @MethodSource(value = {"layerDemoParameters"})
    void recognize(List<Character> characters) {
        layerDemo.recognize(characters);
    }


    public Stream<Arguments> layerDemoParameters() {
        Arguments arg0 = Arguments.arguments(toList("ABBACD"));
        Arguments arg1 = Arguments.arguments(toList("BBACD"));
        return Stream.of(arg0, arg1);
    }

    @ParameterizedTest(name = "fragmentRecognize")
    @ArgumentsSource(ParamProvider.class)
    void fragmentRecognize(List<Character> characters) {
        for (Character abbacd : characters) {
            layerDemo.fragmentRecognize(abbacd);
        }
    }


    @AfterEach
    public void show() {
        layerDemo.show();
        System.out.println("循环总次数：" + layerDemo.getContext().getCount().get());
    }

    public static List<Character> toList(String str) {
        List<Character> characters = new ArrayList<>(str.length());
        for (char c : str.toCharArray()) {
            characters.add(c);
        }
        return characters;
    }

    static class ParamProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            Arguments abbacd = Arguments.arguments(toList("ABBACD"));
            return Stream.of(abbacd);
        }
    }
}