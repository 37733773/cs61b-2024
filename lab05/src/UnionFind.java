import java.util.ArrayList;

public class UnionFind {
    // TODO: Instance variables

    ArrayList<Integer> list;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(i, -1);
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        if (list.get(v) > 0) {
            return sizeOf(parent(v));
        }
        return list.get(v);
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        if (v < 0 || v >= list.size()) {
            throw new IllegalArgumentException("Some comment to describe the reason for throwing.");
        }
        return list.get(v);
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v >= list.size() || v < 0) {
            throw new IllegalArgumentException("Some comment to describe the reason for throwing.");
        }
        if (parent(v) < 0) return v;
        else {
            list.set(v, find(parent(v)));
            return find(parent(v));
        }
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (v1 == v2) return;
        if (find(v1) <= find(v2)) {
            list.set(find(v1), find(v2));
            list.set(find(v2), sizeOf(v2)*2);
        }else{
            list.set(find(v2), find(v1));
            list.set(find(v1), sizeOf(v1)*2);
        }
    }
}
