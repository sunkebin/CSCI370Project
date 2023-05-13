public class MaxHeap {
    DecisionTree[] heap;
    int size;
    int maxSize;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new DecisionTree[maxSize + 1];
        this.heap[0] = new DecisionTree();
        this.heap[0].impurity=Integer.MAX_VALUE;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return 2 * pos;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        return pos > (size / 2) && pos <= size;
    }

    private void swap(int pos1, int pos2) {
    	DecisionTree temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    private void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;

        int left = leftChild(pos);
        int right = rightChild(pos);

        int largest = pos;

        if (left <= size && (1-heap[left].impurity) > (1-heap[largest].impurity))
            largest = left;

        if (right <= size && (1-heap[right].impurity) > (1-heap[largest].impurity))
            largest = right;

        if (largest != pos) {
            swap(pos, largest);
            maxHeapify(largest);
        }
    }

    public void insert(DecisionTree element) {
        if (size >= maxSize)
            return;

        heap[++size] = element;
        int current = size;

        while (heap[current].impurity > heap[parent(current)].impurity) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public DecisionTree remove() {
        DecisionTree removed = heap[1];
        heap[1] = heap[size--];
        maxHeapify(1);
        return removed;
    }
}