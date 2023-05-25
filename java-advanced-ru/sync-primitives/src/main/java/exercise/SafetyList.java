package exercise;

class SafetyList {
    // BEGIN
    private int size;
    private int[] list;

    public synchronized void add(int element) {
        if (list == null) {
            list = new int[1];
            list[0] = list[element];
        } else {
            int[] arr = new int[size + 1];
            System.arraycopy(list, 0, arr, 0, size);
            arr[arr.length - 1] = element;
            list = arr;
        }
        size = size + 1;
    }

    public int get(int index) {
        if (index <= size - 1) {
            return list[index];
        } else {
            throw new IndexOutOfBoundsException("Incorrect index");
        }
    }

    public int getSize() {
        return this.size;
    }
    // END
}
