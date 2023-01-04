/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fadelperpustakaanclient.controller;

import com.mycompany.fadelperpustakaanclient.FormBuku;
import com.mycompany.fadelperpustakaanclient.model.Buku;
import com.mycompany.fadelperpustakaanclient.service.BukuService;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BukuController {

    private final BukuService bukuService;
    private final FormBuku formBuku;

    public BukuController(FormBuku formBuku) {
        this.formBuku = formBuku;
        bukuService = new BukuService();
    }

    public void bersihForm() {
        formBuku.getTxtBukuId().setText("");
        formBuku.getTxtJudul().setText("");
        formBuku.getTxtKode().setText("");
        formBuku.getTxtPenerbit().setText("");
        formBuku.getTxtTahunTerbit().setText("");
    }

    public void getBukuId() {
        Long id = Long.parseLong(formBuku.getTxtBukuId().getText());
        Buku buku = bukuService.getBuku(id);
        if (buku != null) {
            formBuku.getTxtJudul().setText(buku.getJudul());
            formBuku.getTxtKode().setText(buku.getKode());
            formBuku.getTxtPenerbit().setText(buku.getPenerbit());
            formBuku.getTxtTahunTerbit().setText(buku.getTahunTerbit());
        } else {
            JOptionPane.showMessageDialog(formBuku, "Data Tidak Ditemukan");
        }
    }

    public void saveBuku() {
        Buku buku = new Buku();
        buku.setJudul(formBuku.getTxtJudul().getText());
        buku.setKode(formBuku.getTxtKode().getText());
        buku.setPenerbit(formBuku.getTxtPenerbit().getText());
        buku.setTahunTerbit(formBuku.getTxtTahunTerbit().getText());
        buku = bukuService.saveBuku(buku);
        if (buku != null) {
            formBuku.getTxtBukuId().setText(buku.getBukuId().toString());
            JOptionPane.showMessageDialog(formBuku, "Entri Data Berhasil");
        } else {
            JOptionPane.showMessageDialog(formBuku, "Entri Data Gagal");
        }
    }

    public void updateBuku() {
        Buku buku = new Buku();
        buku.setBukuId(Long.parseLong(formBuku.getTxtBukuId().getText()));
        buku.setJudul(formBuku.getTxtJudul().getText());
        buku.setKode(formBuku.getTxtKode().getText());
        buku.setPenerbit(formBuku.getTxtPenerbit().getText());
        buku.setTahunTerbit(formBuku.getTxtTahunTerbit().getText());
        buku = bukuService.updateBuku(buku);
        if (buku != null) {
            formBuku.getTxtBukuId().setText(buku.getBukuId().toString());
            JOptionPane.showMessageDialog(formBuku, "Update Data Berhasil");
        } else {
            JOptionPane.showMessageDialog(formBuku, "Update Data Gagal");
        }
    }

    public void deleteBuku() {
        Long id = Long.parseLong(formBuku.getTxtBukuId().getText());
        bukuService.deleteBuku(id);
        JOptionPane.showMessageDialog(formBuku, "Delete Data Berhasil");
    }

    public void viewTable() {
        DefaultTableModel tabelModel = (DefaultTableModel) formBuku.getTabelBuku().getModel();
        tabelModel.setRowCount(0);
        List<Buku> bukuList = bukuService.getAllBuku();
        for (Buku buku : bukuList) {
            Object[] row = {
                buku.getBukuId(),
                buku.getJudul(),
                buku.getKode(),
                buku.getPenerbit(),
                buku.getTahunTerbit()
            };
            tabelModel.addRow(row);
        }
    }
}
