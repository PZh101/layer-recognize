package github.zhp.layer;

import java.lang.reflect.Array;

/**
 * 数组容量变化工具，数组元素删除工具，提供泛型支持
 *
 * @author zhoup
 */
public class ArrayUtil {


    public static <T> T[] resize(T[] original, int newSize, Class<T> type) {
        if (newSize < 0) {
            throw new IllegalArgumentException("newSize can not less than 0. the size is " + newSize);
        }
        T[] dest = createArray(original, newSize, type);
        int size = original.length;
        System.arraycopy(original, 0, dest, 0, Math.min(newSize, size));
        return dest;
    }


    public static <T> T[] remove(T[] original, int deleteIndex, Class<T> type) {
        if (original.length == 0) {
            throw new UnsupportedOperationException("array size can't be zero,while delete the array element.");
        }
        if (original.length - 1 <= deleteIndex) {
            throw new UnsupportedOperationException("deleteIndex: " + deleteIndex + " greater than " + (original.length - 1));
        }
        T[] newArray = createArray(original, original.length - 1, type);
        System.arraycopy(original, 0, newArray, 0, deleteIndex);
        System.arraycopy(original, deleteIndex + 1, newArray, deleteIndex, original.length - 1 - deleteIndex);
        return newArray;
    }

    /**
     * 推荐使用的remove方法
     */
    public static <T> T[] remove(T[] original, int deleteIndex) {
        if (original.length == 0) {
            throw new UnsupportedOperationException("array size can't be zero,while delete the array element.");
        }
        if (original.length - 1 < deleteIndex) {
            throw new UnsupportedOperationException("deleteIndex: " + deleteIndex + " greater than " + (original.length - 1));
        }
        T[] newArray = createArray(original, original.length - 1);
        System.arraycopy(original, 0, newArray, 0, deleteIndex);
        System.arraycopy(original, deleteIndex + 1, newArray, deleteIndex, original.length - 1 - deleteIndex);
        //help gc to work
        original = null;
        return newArray;
    }

    /**
     * 推荐使用的resize方法
     */
    public static <T> T[] resize(T[] original, int newSize) {
        if (newSize < 1) {
            throw new IllegalArgumentException("newSize can not less than 1. the size is " + newSize);
        }
        T[] dest = createArray(original, newSize);
        int size = original.length;
        System.arraycopy(original, 0, dest, 0, Math.min(newSize, size));
        return dest;
    }

    /**
     * 推荐使用的remove方法
     */
    public static boolean[] remove(boolean[] original, int deleteIndex) {
        if (original.length == 0) {
            throw new UnsupportedOperationException("array size can't be zero,while delete the array element.");
        }
        if (original.length - 1 < deleteIndex) {
            throw new UnsupportedOperationException("deleteIndex: " + deleteIndex + " greater than " + (original.length - 1));
        }
        boolean[] newArray = new boolean[original.length - 1];
        System.arraycopy(original, 0, newArray, 0, deleteIndex);
        System.arraycopy(original, deleteIndex + 1, newArray, deleteIndex, original.length - 1 - deleteIndex);
        //help gc to work
        original = null;
        return newArray;
    }

    /**
     * 推荐使用的resize方法
     */
    public static boolean[] resize(boolean[] original, int newSize) {
        if (newSize < 1) {
            throw new IllegalArgumentException("newSize can not less than 1. the size is " + newSize);
        }
        boolean[] dest = new boolean[newSize];
        if (original == null) {
            return dest;
        }
        int size = original.length;
        System.arraycopy(original, 0, dest, 0, Math.min(newSize, size));
        return dest;
    }

    /**
     * 推荐使用的remove方法
     */
    public static byte[] remove(byte[] original, int deleteIndex) {
        if (original.length == 0) {
            throw new UnsupportedOperationException("array size can't be zero,while delete the array element.");
        }
        if (original.length - 1 < deleteIndex) {
            throw new UnsupportedOperationException("deleteIndex: " + deleteIndex + " greater than " + (original.length - 1));
        }
        byte[] newArray = new byte[original.length - 1];
        System.arraycopy(original, 0, newArray, 0, deleteIndex);
        System.arraycopy(original, deleteIndex + 1, newArray, deleteIndex, original.length - 1 - deleteIndex);
        //help gc to work
        original = null;
        return newArray;
    }

    /**
     * 推荐使用的resize方法
     */
    public static byte[] resize(byte[] original, int newSize) {
        if (newSize < 1) {
            throw new IllegalArgumentException("newSize can not less than 1. the size is " + newSize);
        }
        byte[] dest = new byte[newSize];
        if (original == null) {
            return dest;
        }
        int size = original.length;
        System.arraycopy(original, 0, dest, 0, Math.min(newSize, size));
        return dest;
    }

    /**
     * 推荐使用的remove方法
     */
    public static char[] remove(char[] original, int deleteIndex) {
        if (original.length == 0) {
            throw new UnsupportedOperationException("array size can't be zero,while delete the array element.");
        }
        if (original.length - 1 < deleteIndex) {
            throw new UnsupportedOperationException("deleteIndex: " + deleteIndex + " greater than " + (original.length - 1));
        }
        char[] newArray = new char[original.length - 1];
        System.arraycopy(original, 0, newArray, 0, deleteIndex);
        System.arraycopy(original, deleteIndex + 1, newArray, deleteIndex, original.length - 1 - deleteIndex);
        //help gc to work
        original = null;
        return newArray;
    }

    /**
     * 推荐使用的resize方法
     */
    public static char[] resize(char[] original, int newSize) {
        if (newSize < 1) {
            throw new IllegalArgumentException("newSize can not less than 1. the size is " + newSize);
        }
        char[] dest = new char[newSize];
        if (original == null) {
            return dest;
        }
        int size = original.length;
        System.arraycopy(original, 0, dest, 0, Math.min(newSize, size));
        return dest;
    }

    /**
     * 推荐使用的remove方法
     */
    public static short[] remove(short[] original, int deleteIndex) {
        if (original.length == 0) {
            throw new UnsupportedOperationException("array size can't be zero,while delete the array element.");
        }
        if (original.length - 1 < deleteIndex) {
            throw new UnsupportedOperationException("deleteIndex: " + deleteIndex + " greater than " + (original.length - 1));
        }
        short[] newArray = new short[original.length - 1];
        System.arraycopy(original, 0, newArray, 0, deleteIndex);
        System.arraycopy(original, deleteIndex + 1, newArray, deleteIndex, original.length - 1 - deleteIndex);
        //help gc to work
        original = null;
        return newArray;
    }

    /**
     * 推荐使用的resize方法
     */
    public static short[] resize(short[] original, int newSize) {
        if (newSize < 1) {
            throw new IllegalArgumentException("newSize can not less than 1. the size is " + newSize);
        }
        short[] dest = new short[newSize];
        if (original == null) {
            return dest;
        }
        int size = original.length;
        System.arraycopy(original, 0, dest, 0, Math.min(newSize, size));
        return dest;
    }

    /**
     * 推荐使用的remove方法
     */
    public static int[] remove(int[] original, int deleteIndex) {
        if (original.length == 0) {
            throw new UnsupportedOperationException("array size can't be zero,while delete the array element.");
        }
        if (original.length - 1 < deleteIndex) {
            throw new UnsupportedOperationException("deleteIndex: " + deleteIndex + " greater than " + (original.length - 1));
        }
        int[] newArray = new int[original.length - 1];
        System.arraycopy(original, 0, newArray, 0, deleteIndex);
        System.arraycopy(original, deleteIndex + 1, newArray, deleteIndex, original.length - 1 - deleteIndex);
        //help gc to work
        original = null;
        return newArray;
    }

    /**
     * 推荐使用的resize方法
     */
    public static int[] resize(int[] original, int newSize) {
        if (newSize < 1) {
            throw new IllegalArgumentException("newSize can not less than 1. the size is " + newSize);
        }
        int[] dest = new int[newSize];
        if (original == null) {
            return dest;
        }
        int size = original.length;
        System.arraycopy(original, 0, dest, 0, Math.min(newSize, size));
        return dest;
    }

    /**
     * 推荐使用的remove方法
     */
    public static float[] remove(float[] original, int deleteIndex) {
        if (original.length == 0) {
            throw new UnsupportedOperationException("array size can't be zero,while delete the array element.");
        }
        if (original.length - 1 < deleteIndex) {
            throw new UnsupportedOperationException("deleteIndex: " + deleteIndex + " greater than " + (original.length - 1));
        }
        float[] newArray = new float[original.length - 1];
        System.arraycopy(original, 0, newArray, 0, deleteIndex);
        System.arraycopy(original, deleteIndex + 1, newArray, deleteIndex, original.length - 1 - deleteIndex);
        //help gc to work
        original = null;
        return newArray;
    }

    /**
     * 推荐使用的resize方法
     */
    public static float[] resize(float[] original, int newSize) {
        if (newSize < 1) {
            throw new IllegalArgumentException("newSize can not less than 1. the size is " + newSize);
        }
        float[] dest = new float[newSize];
        if (original == null) {
            return dest;
        }
        int size = original.length;
        System.arraycopy(original, 0, dest, 0, Math.min(newSize, size));
        return dest;
    }

    /**
     * 推荐使用的remove方法
     */
    public static long[] remove(long[] original, int deleteIndex) {
        if (original.length == 0) {
            throw new UnsupportedOperationException("array size can't be zero,while delete the array element.");
        }
        if (original.length - 1 < deleteIndex) {
            throw new UnsupportedOperationException("deleteIndex: " + deleteIndex + " greater than " + (original.length - 1));
        }
        long[] newArray = new long[original.length - 1];
        System.arraycopy(original, 0, newArray, 0, deleteIndex);
        System.arraycopy(original, deleteIndex + 1, newArray, deleteIndex, original.length - 1 - deleteIndex);
        //help gc to work
        original = null;
        return newArray;
    }

    /**
     * 推荐使用的resize方法
     */
    public static long[] resize(long[] original, int newSize) {
        if (newSize < 1) {
            throw new IllegalArgumentException("newSize can not less than 1. the size is " + newSize);
        }
        long[] dest = new long[newSize];
        if (original == null) {
            return dest;
        }
        int size = original.length;
        System.arraycopy(original, 0, dest, 0, Math.min(newSize, size));
        return dest;
    }

    /**
     * 推荐使用的remove方法
     */
    public static double[] remove(double[] original, int deleteIndex) {
        if (original.length == 0) {
            throw new UnsupportedOperationException("array size can't be zero,while delete the array element.");
        }
        if (original.length - 1 < deleteIndex) {
            throw new UnsupportedOperationException("deleteIndex: " + deleteIndex + " greater than " + (original.length - 1));
        }
        double[] newArray = new double[original.length - 1];
        System.arraycopy(original, 0, newArray, 0, deleteIndex);
        System.arraycopy(original, deleteIndex + 1, newArray, deleteIndex, original.length - 1 - deleteIndex);
        //help gc to work
        original = null;
        return newArray;
    }

    /**
     * 推荐使用的resize方法
     */
    public static double[] resize(double[] original, int newSize) {
        if (newSize < 1) {
            throw new IllegalArgumentException("newSize can not less than 1. the size is " + newSize);
        }
        double[] dest = new double[newSize];
        if (original == null) {
            return dest;
        }
        int size = original.length;
        System.arraycopy(original, 0, dest, 0, Math.min(newSize, size));
        return dest;
    }

    /**
     * 创建数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] createArray(T[] original, int newSize, Class<T> type) {
        return (type == Object[].class) ? (T[]) new Object[newSize] : (T[]) Array.newInstance(type, newSize);
    }

    /**
     * 创建同<tt>original</tt>类型相同的数组
     *
     * @param newSize 新的数组大小
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] createArray(T[] original, int newSize) {
        Class<T> type = (Class<T>) original[0].getClass();
        return createArray(original, newSize, type);
    }
}
