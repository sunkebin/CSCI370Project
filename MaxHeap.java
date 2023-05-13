public class MaxHeap {
    TreeNode[] heap;
    int size;
    int maxSize;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new TreeNode[maxSize + 1];
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
    	TreeNode temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    private void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;

        int left = leftChild(pos);
        int right = rightChild(pos);

        int largest = pos;

        if (left <= size && heap[left].score > heap[largest].score)
            largest = left;

        if (right <= size && heap[right].score > heap[largest].score)
            largest = right;

        if (largest != pos) {
            swap(pos, largest);
            maxHeapify(largest);
        }
    }

    public void insert(TreeNode element) {
        if (size >= maxSize)
            return;

        heap[++size] = element;
        int current = size;

        while (heap[current].score > heap[parent(current)].score) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public TreeNode remove() {
        TreeNode removed = heap[1];
        heap[1] = heap[size--];
        maxHeapify(1);
        return removed;
    }
}