package subject;
//Subject1146

import java.util.HashMap;
import java.util.Map;

/**
 * 设三元组
 * (ver,index,val)为树节点
 * 根为空节点
 * 每次操作都在对应的index的链的叶子节点插入一个新的节点
 */
//class SnapshotArray {
//    TreeMap<Integer, Integer>[] array;
//    int currVer;
//
//    @SuppressWarnings("unchecked")
//    public SnapshotArray(int length) {
//        array = new TreeMap[length];
//    }
//
//    public void set(int index, int val) {
//        TreeMap<Integer, Integer> sortedMap = array[index];
//        if (sortedMap == null) {
//            sortedMap = new TreeMap<>();
//            array[index] = sortedMap;
//        }
//        sortedMap.put(currVer, val);
//    }
//
//    public int snap() {
//        return currVer++;
//    }
//
//
//    public int get(int index, int snap_id) {
//        TreeMap<Integer, Integer> sortedMap = array[index];
//        if (sortedMap == null)
//            return 0;
//        Map.Entry<Integer, Integer> entry = sortedMap.floorEntry(snap_id);
//        if (entry==null)
//            return 0;
//        return entry.getValue();
//    }
//
//}
public class SnapshotArray {



    private int snapId = 0;
    private Map<Integer, Map<Integer, Integer>> array;

    public SnapshotArray(int length) {
        this.array = new HashMap<>(length);
    }

    public void set(int index, int val) {
        Map<Integer, Integer> snapshots = array.computeIfAbsent(index, key -> new HashMap<>());
        snapshots.put(snapId, val);
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snap_id) {
        Map<Integer, Integer> snapshots = array.get(index);
        while (snapshots != null && snap_id >= 0) {
            Integer value = snapshots.get(snap_id);
            if (value != null) {
                return value;
            }
            --snap_id;//搜索全部的版本号,a难以做到时,可以变化b,a指的是在map中找比较近的,b指的是用比较近的去找
        }
        return 0;
    }
}
