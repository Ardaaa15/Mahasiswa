package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class representing a student (Mahasiswa) with attributes for name, NIM, and major.
 */
class Mahasiswa {
    String nama;
    Long nim;
    String jurusan;
    static String universitas;

    /**
     * Constructs a Mahasiswa object with the specified name, NIM, and major.
     *
     * @param nama   the name of the student
     * @param nim    the NIM (student ID) of the student
     * @param jurusan the major of the student
     */
    public Mahasiswa(String nama, Long nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    /**
     * Displays the name of the university.
     *
     * @return the name of the university
     */
    static String tampilUniversitas() {
        return "Universitas: " + universitas;
    }

    /**
     * Returns a string representation of the Mahasiswa data.
     *
     * @return a formatted string containing the student's name, NIM, and major
     */
    String tampilDataMahasiswa() {
        return "Nama: " + nama + ", " + "NIM: " + nim + ", " + "Jurusan: " + jurusan;
    }
}

/**
 * Main class to manage student data, including adding, displaying, searching, and deleting student records.
 */
public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

    /**
     * Main method to run the application and display the menu.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.print("Masukkan Nama Universitas: ");
            Mahasiswa.universitas = reader.readLine();
        } catch (IOException e) {
            System.out.println("Input tidak valid, program akan berjalan dengan universitas kosong.");
        }

        int pilihan = 0;
        do {
            System.out.println("===============================");
            System.out.println("Menu:");
            System.out.println("===============================");
            System.out.println("1. Tambah Data Mahasiswa");
            System.out.println("2. Tampilkan Data Mahasiswa");
            System.out.println("3. Cari Mahasiswa");
            System.out.println("4. Hapus Mahasiswa");
            System.out.println("5. Keluar");
            System.out.println("===============================");
            System.out.print("Pilih menu: ");

            try {
                pilihan = Integer.parseInt(reader.readLine());
                switch (pilihan) {
                    case 1:
                        tambahDataMahasiswa();
                        break;
                    case 2:
                        tampilkanDataMahasiswa();
                        break;
                    case 3:
                        cariMahasiswa();
                        break;
                    case 4:
                        hapusMahasiswa();
                        break;
                    case 5:
                        System.out.println("Terima kasih!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid, silakan pilih lagi.");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Input tidak valid, coba lagi.");
            }
        } while (pilihan != 5);
    }

    /**
     * Searches for a student by their NIM and displays their data.
     */
    static void cariMahasiswa() {
        System.out.print("Masukkan NIM Mahasiswa yang dicari: ");
        try {
            String nim = reader.readLine();
            Long nimLong = Long.parseLong(nim);
            boolean found = false;

            for (Mahasiswa mahasiswa : daftarMahasiswa) {
                if (mahasiswa.nim.equals(nimLong)) {
                    System.out.println("=================================");
                    System.out.println(mahasiswa.tampilDataMahasiswa());
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Input tidak valid, coba lagi.");
        }
    }

    /**
     * Prompts the user to input student data and adds a new Mahasiswa to the list.
     */
    static void tambahDataMahasiswa() {
        try {
            System.out.println("================================");
            System.out.print("Masukkan Nama Mahasiswa: ");
            String nama = reader.readLine();

            String nim = "";
            boolean inputValid = false;
            do {
                System.out.print("Masukkan NIM Mahasiswa (15 digit): ");
                try {
                    nim = reader.readLine();
                    Long.parseLong(nim);
                    if (nim.length() != 15) {
                        System.out.println("NIM tidak valid, NIM harus 15 digit.");
                    } else {
                        inputValid = true;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("NIM tidak valid, NIM harus berupa angka.");
                }
            } while (!inputValid);

            System.out.print("Masukkan Jurusan Mahasiswa: ");
            String jurusan = reader.readLine();

            daftarMahasiswa.add(new Mahasiswa(nama, Long.parseLong(nim), jurusan));

            System.out.println("=================================");
            System.out.println("Data Mahasiswa Berhasil Ditambah.");
        } catch (IOException e) {
            System.out.println("Input tidak valid, coba lagi.");
        }
    }

    /**
     * Deletes a student record based on their NIM.
     */
    static void hapusMahasiswa() {
        System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
        try {
            String nim = reader.readLine();
            Long nimLong = Long.parseLong(nim);
            boolean removed = daftarMahasiswa.removeIf(mahasiswa -> mahasiswa.nim.equals(nimLong));

            if (removed) {
                System.out.println("Mahasiswa dengan NIM " + nim + " berhasil dihapus.");
            } else {
                System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Input tidak valid, coba lagi.");
        }
    }

    /**
     * Displays all students' data in the list.
     */
    static void tampilkanDataMahasiswa() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Belum Ada Data!");
            return;
        }
        System.out.println("=================================");
        System.out.println(Mahasiswa.tampilUniversitas());
        System.out.println("=================================");
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            System.out.println(mahasiswa.tampilDataMahasiswa());
        }
    }
}
