package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Mahasiswa {
    String nama;
    Long nim;
    String jurusan;

    public Mahasiswa(String nama, Long nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    static String tampilUniversitas() {
        return("Universitas Muhammadiyah Malang");
    }

    String tampilDataMahasiswa() {
        return("Nama: " + nama + ", " + "NIM: " + nim + ", " + "Jurusan: " + jurusan);
    }

}

class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

    public static void main(String[] args) {
        int pilihan = 0;
        do {
            System.out.println("===============================");
            System.out.println("Menu:");
            System.out.println("===============================");
            System.out.println("1. Tambah Data Mahasiswa");
            System.out.println("2. Tampilkan Data Mahasiswa");
            System.out.println("3. Keluar");
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
                        System.out.println("Terima kasih!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid, silakan pilih lagi");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Input tidak valid, coba lagi");
            }
        } while (pilihan != 3);
    }

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
                        System.out.println("NIM tidak valid, NIM harus 15 digit");
                    } else {
                        inputValid = true;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println(Arrays.toString(ex.getStackTrace()));
                    System.out.println("NIM tidak valid, NIM harus berupa angka");
                }
            } while (!inputValid);

            System.out.print("Masukkan Jurusan Mahasiswa: ");
            String jurusan = reader.readLine();

            // Tambahkan Mahasiswa ke daftar setelah input selesai
            daftarMahasiswa.add(new Mahasiswa(nama, Long.parseLong(nim), jurusan));

            System.out.println("=================================");
            System.out.println("Data Mahasiswa Berhasil Ditambah.");
        } catch (IOException e) {
            // System.out.println("Input tidak valid, coba laagi.");
        }
    }

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