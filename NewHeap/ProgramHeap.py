class DataBarang:
    def __init__(self, id_barang, nama):
        self.id = id_barang
        self.nama = nama

    def __repr__(self):
        return f"[{self.id} - {self.nama}]"


class MinHeap:
    def __init__(self):
        self.heap = []

    def parent(self, i):
        return (i - 1) // 2

    def left_child(self, i):
        return 2 * i + 1

    def right_child(self, i):
        return 2 * i + 2

    def insert(self, data, show_log=False):
        self.heap.append(data)
        current = len(self.heap) - 1

        if show_log:
            print(f"\n[Min-Heap] Memasukkan data: {data}")
            print("[Min-Heap] Melakukan heapify-up...")

        self.heapify_up(current, show_log)

    def heapify_up(self, i, show_log):
        while i > 0 and self.heap[self.parent(i)].id > self.heap[i].id:
            p_idx = self.parent(i)
            if show_log:
                print(f" -> Swap {self.heap[i].id} dengan parent {self.heap[p_idx].id}")
            self.swap(i, p_idx)
            i = p_idx

    def extract_min(self, show_log=False):
        if not self.heap:
            print("Min-Heap kosong.")
            return None

        min_val = self.heap[0]
        last_val = self.heap.pop()

        if show_log:
            print(f"\n[Min-Heap] Menghapus root: {min_val}")

        if self.heap:
            self.heap[0] = last_val
            if show_log:
                print(f"[Min-Heap] Memindahkan elemen terakhir {last_val} ke root.")
                print("[Min-Heap] Melakukan heapify-down...")
            self.heapify_down(0, show_log)

        return min_val

    def heapify_down(self, i, show_log):
        smallest = i
        left = self.left_child(i)
        right = self.right_child(i)

        if left < len(self.heap) and self.heap[left].id < self.heap[smallest].id:
            smallest = left
        if right < len(self.heap) and self.heap[right].id < self.heap[smallest].id:
            smallest = right

        if smallest != i:
            if show_log:
                print(f" -> Swap parent {self.heap[i].id} dengan {self.heap[smallest].id}")
            self.swap(i, smallest)
            self.heapify_down(smallest, show_log)

    def swap(self, a, b):
        self.heap[a], self.heap[b] = self.heap[b], self.heap[a]

    def print_heap(self):
        print(self.heap)

    def clone_heap(self):
        cloned = MinHeap()
        for item in self.heap:
            cloned.heap.append(DataBarang(item.id, item.nama))
        return cloned

    def is_empty(self):
        return len(self.heap) == 0


class MaxHeap:
    def __init__(self):
        self.heap = []

    def parent(self, i):
        return (i - 1) // 2

    def left_child(self, i):
        return 2 * i + 1

    def right_child(self, i):
        return 2 * i + 2

    def insert(self, data, show_log=False):
        self.heap.append(data)
        current = len(self.heap) - 1

        if show_log:
            print(f"\n[Max-Heap] Memasukkan data: {data}")
            print("[Max-Heap] Melakukan heapify-up...")

        self.heapify_up(current, show_log)

    def heapify_up(self, i, show_log):
        while i > 0 and self.heap[self.parent(i)].id < self.heap[i].id:
            p_idx = self.parent(i)
            if show_log:
                print(f" -> Swap {self.heap[i].id} dengan parent {self.heap[p_idx].id}")
            self.swap(i, p_idx)
            i = p_idx

    def extract_max(self, show_log=False):
        if not self.heap:
            print("Max-Heap kosong.")
            return None

        max_val = self.heap[0]
        last_val = self.heap.pop()

        if show_log:
            print(f"\n[Max-Heap] Menghapus root: {max_val}")

        if self.heap:
            self.heap[0] = last_val
            if show_log:
                print(f"[Max-Heap] Memindahkan elemen terakhir {last_val} ke root.")
                print("[Max-Heap] Melakukan heapify-down...")
            self.heapify_down(0, show_log)

        return max_val

    def heapify_down(self, i, show_log):
        largest = i
        left = self.left_child(i)
        right = self.right_child(i)

        if left < len(self.heap) and self.heap[left].id > self.heap[largest].id:
            largest = left
        if right < len(self.heap) and self.heap[right].id > self.heap[largest].id:
            largest = right

        if largest != i:
            if show_log:
                print(f" -> Swap parent {self.heap[i].id} dengan {self.heap[largest].id}")
            self.swap(i, largest)
            self.heapify_down(largest, show_log)

    def swap(self, a, b):
        self.heap[a], self.heap[b] = self.heap[b], self.heap[a]

    def print_heap(self):
        print(self.heap)

    def clone_heap(self):
        cloned = MaxHeap()
        for item in self.heap:
            cloned.heap.append(DataBarang(item.id, item.nama))
        return cloned

    def is_empty(self):
        return len(self.heap) == 0


def main():
    data_awal = [
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
    ]

    min_heap = MinHeap()
    max_heap = MaxHeap()

    print("Memuat data awal ke dalam Min-Heap dan Max-Heap...")
    for item in data_awal:
        parts = item.split(",")
        id_barang = int(parts[0])
        nama = parts[1]
        data = DataBarang(id_barang, nama)
        
        min_heap.insert(data, False)
        max_heap.insert(data, False)
    print("Berhasil memuat 100 data awal!\n")

    is_running = True
    while is_running:
        print("\n===== MENU HEAP =====")
        print("1. Tambah Data")
        print("2. Tampilkan Ascending (Min-Heap)")
        print("3. Tampilkan Descending (Max-Heap)")
        print("4. Hapus Data Min-Heap")
        print("5. Hapus Data Max-Heap")
        print("6. Tampilkan Isi Heap")
        print("0. Keluar")
        pilihan = input("Pilih menu: ")

        if pilihan == "1":
            id_barang = int(input("Masukkan ID (angka): "))
            nama = input("Masukkan Nama: ")
            new_data = DataBarang(id_barang, nama)
            
            min_heap.insert(new_data, True)
            max_heap.insert(new_data, True)
            print("Data berhasil ditambahkan!")

        elif pilihan == "2":
            print("\n--- Data Terurut Ascending (Min-Heap) ---")
            temp_min_heap = min_heap.clone_heap()
            while not temp_min_heap.is_empty():
                print(temp_min_heap.extract_min(False).id, end=" ")
            print()

        elif pilihan == "3":
            print("\n--- Data Terurut Descending (Max-Heap) ---")
            temp_max_heap = max_heap.clone_heap()
            while not temp_max_heap.is_empty():
                print(temp_max_heap.extract_max(False).id, end=" ")
            print()

        elif pilihan == "4":
            print("\nMenghapus dari Min-Heap...")
            deleted_min = min_heap.extract_min(True)
            if deleted_min is not None:
                print(f"Data terhapus: {deleted_min}")

        elif pilihan == "5":
            print("\nMenghapus dari Max-Heap...")
            deleted_max = max_heap.extract_max(True)
            if deleted_max is not None:
                print(f"Data terhapus: {deleted_max}")

        elif pilihan == "6":
            print("\nIsi Min-Heap saat ini (Array Form):")
            min_heap.print_heap()
            print("\nIsi Max-Heap saat ini (Array Form):")
            max_heap.print_heap()

        elif pilihan == "0":
            print("Keluar dari program. Terima kasih!")
            is_running = False

        else:
            print("Pilihan tidak valid. Silakan coba lagi.")


if __name__ == "__main__":
    main()