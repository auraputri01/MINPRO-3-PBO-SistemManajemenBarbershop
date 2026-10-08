/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

/**
 *
 * @author Aura
 */
/** INTERFACE: kontrak untuk objek yang bisa memberi diskon. */
   public interface Diskonable {
       /** Mengembalikan nilai diskon (rupiah) dari total harga. */
       int hitungDiskon(int totalHarga);
   }
