public class MaxHeap {
    private int[] heap;
    private int size;
    private int maxSize;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new int[maxSize + 1];
        this.heap[0] = Integer.MAX_VALUE;
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
        int temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    private void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;

        int left = leftChild(pos);
        int right = rightChild(pos);

        int largest = pos;

        if (left <= size && heap[left] > heap[largest])
            largest = left;

        if (right <= size && heap[right] > heap[largest])
            largest = right;

        if (largest != pos) {
            swap(pos, largest);
            maxHeapify(largest);
        }
    }

    public void insert(int element) {
        if (size >= maxSize)
            return;

        heap[++size] = element;
        int current = size;

        while (heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public int remove() {
        int removed = heap[1];
        heap[1] = heap[size--];
        maxHeapify(1);
        return removed;
    }
}