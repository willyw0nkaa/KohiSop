KohiSop

Sistem pembayaran sederhana berbasis Java untuk manajemen pemesanan makanan, minuman, dan pembayaran dengan berbagai metode seperti tunai, e-money, dan QRIS.

Fitur

Manajemen menu makanan dan minuman.
Pemrosesan pesanan pelanggan.
Pembayaran melalui tunai, e-money, dan QRIS.
Konversi mata uang.
Format tampilan tabel yang rapi.
Struktur Project

KohiSopPaymentSystem.java - Entry point aplikasi.
OrderManager.java - Mengelola daftar pesanan.
MenuManager.java - Mengelola daftar menu.
PaymentProcessor.java - Mengatur proses pembayaran.
DisplayManager.java - Menangani tampilan output.
InputHandler.java - Menangani input dari user.
Makanan.java, Minuman.java - Representasi item menu.
EMoneyPayment.java, QRIS.java, Tunai.java - Implementasi metode pembayaran.
TukarCurrency.java - Fungsi tukar mata uang.
