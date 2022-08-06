package github.zhp.layer;

/**
 * @author zhoup
 */
public class NodeComparator<T> extends BaseComparator<T>{
    @Override
    public boolean brother(T elder, T younger) {
        return false;
    }

    @Override
    public boolean fatherAndSon(T suspectedFather, T suspectedSon) {
        return false;
    }
}
