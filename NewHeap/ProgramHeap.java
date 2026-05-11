import java.util.ArrayList;
import java.util.Scanner;

class DataBarang {
    int id;
    String nama;

    public DataBarang(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "[" + id + " - " + nama + "]";
    }
}

class MinHeap {
    private ArrayList<DataBarang> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    public void insert(DataBarang data, boolean showLog) {
        heap.add(data);
        int current = heap.size() - 1;
        
        if (showLog) {
            System.out.println("\n[Min-Heap] Memasukkan data: " + data);
            System.out.println("[Min-Heap] Melakukan heapify-up...");
        }

        heapifyUp(current, showLog);
    }

    private void heapifyUp(int i, boolean showLog) {
        while (i > 0 && heap.get(parent(i)).id > heap.get(i).id) {
            if (showLog) {
                System.out.println(" -> Swap " + heap.get(i).id + " dengan parent " + heap.get(parent(i)).id);
            }
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public DataBarang extractMin(boolean showLog) {
        if (heap.isEmpty()) {
            System.out.println("Min-Heap kosong.");
            return null;
        }

        DataBarang min = heap.get(0);
        DataBarang last = heap.remove(heap.size() - 1);
        
        if (showLog) {
            System.out.println("\n[Min-Heap] Menghapus root: " + min);
        }

        if (!heap.isEmpty()) {
            heap.set(0, last);
            if (showLog) {
                System.out.println("[Min-Heap] Memindahkan elemen terakhir " + last + " ke root.");
                System.out.println("[Min-Heap] Melakukan heapify-down...");
            }
            heapifyDown(0, showLog);
        }

        return min;
    }

    private void heapifyDown(int i, boolean showLog) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < heap.size() && heap.get(left).id < heap.get(smallest).id) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right).id < heap.get(smallest).id) {
            smallest = right;
        }

        if (smallest != i) {
            if (showLog) {
                System.out.println(" -> Swap parent " + heap.get(i).id + " dengan " + heap.get(smallest).id);
            }
            swap(i, smallest);
            heapifyDown(smallest, showLog);
        }
    }

    private void swap(int a, int b) {
        DataBarang temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    public void printHeap() {
        System.out.println(heap);
    }

    public MinHeap cloneHeap() {
        MinHeap cloned = new MinHeap();
        for (DataBarang item : this.heap) {
            cloned.heap.add(new DataBarang(item.id, item.nama));
        }
        return cloned;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}

class MaxHeap {
    private ArrayList<DataBarang> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    public void insert(DataBarang data, boolean showLog) {
        heap.add(data);
        int current = heap.size() - 1;

        if (showLog) {
            System.out.println("\n[Max-Heap] Memasukkan data: " + data);
            System.out.println("[Max-Heap] Melakukan heapify-up...");
        }

        heapifyUp(current, showLog);
    }

    private void heapifyUp(int i, boolean showLog) {
        while (i > 0 && heap.get(parent(i)).id < heap.get(i).id) {
            if (showLog) {
                System.out.println(" -> Swap " + heap.get(i).id + " dengan parent " + heap.get(parent(i)).id);
            }
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public DataBarang extractMax(boolean showLog) {
        if (heap.isEmpty()) {
            System.out.println("Max-Heap kosong.");
            return null;
        }

        DataBarang max = heap.get(0);
        DataBarang last = heap.remove(heap.size() - 1);
        
        if (showLog) {
            System.out.println("\n[Max-Heap] Menghapus root: " + max);
        }

        if (!heap.isEmpty()) {
            heap.set(0, last);
            if (showLog) {
                System.out.println("[Max-Heap] Memindahkan elemen terakhir " + last + " ke root.");
                System.out.println("[Max-Heap] Melakukan heapify-down...");
            }
            heapifyDown(0, showLog);
        }

        return max;
    }

    private void heapifyDown(int i, boolean showLog) {
        int largest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < heap.size() && heap.get(left).id > heap.get(largest).id) {
            largest = left;
        }
        if (right < heap.size() && heap.get(right).id > heap.get(largest).id) {
            largest = right;
        }

        if (largest != i) {
            if (showLog) {
                System.out.println(" -> Swap parent " + heap.get(i).id + " dengan " + heap.get(largest).id);
            }
            swap(i, largest);
            heapifyDown(largest, showLog);
        }
    }

    private void swap(int a, int b) {
        DataBarang temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    public void printHeap() {
        System.out.println(heap);
    }

    public MaxHeap cloneHeap() {
        MaxHeap cloned = new MaxHeap();
        for (DataBarang item : this.heap) {
            cloned.heap.add(new DataBarang(item.id, item.nama));
        }
        return cloned;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}

public class ProgramHeap {
    
    private static final String[] DATA_AWAL = {
        "5288,pensil", "5993,pulpen", "8689,penghapus", "8043,buku", "8699,sampul",
        "2156,penggaris", "4457,kertas", "8938,cat", "2618,stabilo", "9033,mobil",
        "9971,motor", "3874,becak", "5914,sepeda", "2398,kereta", "3725,pesawat",
        "5210,perahu", "7363,kapal", "7631,rakit", "4513,kipas", "5656,charger",
        "6453,peci", "8783,sarung", "8194,sajadah", "9783,smartphone", "3685,jam",
        "4490,televisi", "8294,laptop", "8563,komputer", "1070,mouse", "5408,keyboard",
        "8258,tablet", "9309,jendela", "1138,kaca", "2751,pintu", "3258,kompor",
        "6402,lemari", "7921,kasur", "9781,ranjang", "3818,bantal", "5204,baju",
        "6119,kaos", "1928,celana", "4207,mukena", "7255,jilbab", "5309,pigura",
        "2897,antena", "8028,kulkas", "1660,dispenser", "3248,meja", "5641,kursi",
        "7376,kemoceng", "3525,sapu", "4492,gayung", "7187,sabun", "1305,sikat",
        "6602,shampo", "8153,botol", "3561,gelas", "5082,piring", "7151,panci",
        "7524,wajan", "9178,blender", "9817,galon", "4304,cobek", "6820,termos",
        "9151,kran", "3482,selang", "3316,karpet", "5192,tikar", "7572,keset",
        "7660,sepatu", "9224,kaos kaki", "5083,jaket", "6362,piama", "6465,piano",
        "9888,gitar", "4159,angklung", "4969,suling", "5097,toples", "6271,parfum",
        "9250,sisir", "3409,topi", "4577,gunting", "6244,pisau", "8612,kaleng",
        "4650,tisu", "6799,tas", "9298,ikat pinggang", "4361,korek api", "4379,kopi",
        "6928,gula", "3195,cabai", "5741,wortel", "6852,timun", "8147,apel",
        "8902,jeruk", "8967,tomat", "1302,pisang", "2363,pepaya", "6861,bawang"
    };

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        MaxHeap maxHeap = new MaxHeap();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Memuat data awal ke dalam Min-Heap dan Max-Heap...");
        for (String item : DATA_AWAL) {
            String[] parts = item.split(",");
            int id = Integer.parseInt(parts[0]);
            String nama = parts[1];
            DataBarang data = new DataBarang(id, nama);
            
            minHeap.insert(data, false); 
            maxHeap.insert(data, false);
        }
        System.out.println("Berhasil memuat 100 data awal!\n");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n===== MENU HEAP =====");
            System.out.println("1. Tambah Data");
            System.out.println("2. Tampilkan Ascending (Min-Heap)");
            System.out.println("3. Tampilkan Descending (Max-Heap)");
            System.out.println("4. Hapus Data Min-Heap");
            System.out.println("5. Hapus Data Max-Heap");
            System.out.println("6. Tampilkan Isi Heap");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    System.out.print("Masukkan ID (angka): ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    DataBarang newData = new DataBarang(id, nama);
                    
                    minHeap.insert(newData, true);
                    maxHeap.insert(newData, true);
                    System.out.println("Data berhasil ditambahkan!");
                    break;

                case "2":
                    System.out.println("\n--- Data Terurut Ascending (Min-Heap) ---");
                    MinHeap tempMinHeap = minHeap.cloneHeap();
                    while (!tempMinHeap.isEmpty()) {
                        System.out.print(tempMinHeap.extractMin(false).id + " ");
                    }
                    System.out.println();
                    break;

                case "3":
                    System.out.println("\n--- Data Terurut Descending (Max-Heap) ---");
                    MaxHeap tempMaxHeap = maxHeap.cloneHeap();
                    while (!tempMaxHeap.isEmpty()) {
                        System.out.print(tempMaxHeap.extractMax(false).id + " ");
                    }
                    System.out.println();
                    break;

                case "4":
                    System.out.println("\nMenghapus dari Min-Heap...");
                    DataBarang deletedMin = minHeap.extractMin(true);
                    if (deletedMin != null) {
                        System.out.println("Data terhapus: " + deletedMin);
                    }
                    break;

                case "5":
                    System.out.println("\nMenghapus dari Max-Heap...");
                    DataBarang deletedMax = maxHeap.extractMax(true);
                    if (deletedMax != null) {
                        System.out.println("Data terhapus: " + deletedMax);
                    }
                    break;

                case "6":
                    System.out.println("\nIsi Min-Heap saat ini (Array Form):");
                    minHeap.printHeap();
                    System.out.println("\nIsi Max-Heap saat ini (Array Form):");
                    maxHeap.printHeap();
                    break;

                case "0":
                    System.out.println("Keluar dari program. Terima kasih!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }
}