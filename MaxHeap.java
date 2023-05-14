public class MaxHeap {
    treeNode[] heap;
    int size;
    int maxSize;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new treeNode[maxSize + 1];
        this.heap[0] = new treeNode();
        this.heap[0].score=Integer.MAX_VALUE;
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
    	treeNode temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    private boolean shouldSwap(int pos1, int pos2) {
        return heap[pos1].score< heap[pos2].score;
    }

    private void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;

        int left = leftChild(pos);
        int right = rightChild(pos);

        int largest = pos;

        if (left <= size && shouldSwap(left, largest))
            largest = left;

        if (right <= size && shouldSwap(right, largest))
            largest = right;

        if (largest != pos) {
            swap(pos, largest);
            maxHeapify(largest);
        }
    }

    public void insert(treeNode element) {
        if (size >= maxSize)
            return;

        heap[++size] = element;
        int current = size;

        while (heap[current].score > heap[parent(current)].score) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public treeNode remove() {
        treeNode removed = heap[1];
        heap[1] = heap[size--];
        maxHeapify(1);
        return removed;
    }
}