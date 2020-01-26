package subject;

import java.util.function.BiFunction;
import java.util.function.Function;

public class SegmentTree<T, R> {
    private T[] data;
    private R[] tree;
    private final BiFunction<R, R, R> merge;
    private final Function<T, R> mapping;

    @SuppressWarnings("unchecked")
    public SegmentTree(T[] data, BiFunction<R, R, R> merge, Function<T, R> mapping) {
        this.data = data;
        this.merge = merge;
        this.mapping = mapping;
        tree = (R[]) new Object[data.length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }

    int leftIndex(int index) {
        return index * 2 + 1;
    }

    int rightIndex(int index) {
        return index * 2 + 2;
    }

    //在index的位置创建[l,r]的线段树
    R buildSegmentTree(int index, int l, int r) {
        if (l == r) {
            tree[index] = mapping.apply(data[index]);
        } else {
            int mid = l + (r - l) / 2;
            int leftIndex = leftIndex(index);
            int rightIndex = rightIndex(index);

            R leftChild = buildSegmentTree(leftIndex, l, mid);
            R rightChild = buildSegmentTree(rightIndex, mid + 1, r);
            tree[index] = merge.apply(leftChild, rightChild);
        }
        return tree[index];
    }

    public R query(int ql, int qr) {
        return query(0, 0, data.length - 1, ql, qr);
    }

    private R query(int index, int l, int r, int ql, int qr) {
        if (l == ql && r == qr)
            return tree[index];
        int mid = l + (r - l) / 2;
        int leftIndex = leftIndex(index);
        int rightIndex = rightIndex(index);
        if (ql >= mid + 1)
            return query(rightIndex, mid + 1, r, ql, qr);
        else if (qr <= mid)
            return query(leftIndex, l, mid, ql, qr);
        return merge.apply(query(leftIndex, l, mid, ql, mid), query(rightIndex, mid + 1, r, mid + 1, qr));
    }
}
