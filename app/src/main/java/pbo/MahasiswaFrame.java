package pbo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MahasiswaFrame extends JFrame {

    private JTextField txtSearch;
    private JButton btnReset;
    private JButton btnTambah;
    private JButton btnEdit;
    private JButton btnDelete;

    private JTable table;
    private DefaultTableModel model;

    private ArrayList<Mahasiswa> list;

    private final Color PRIMARY = new Color(25, 118, 210);
    private final Color PRIMARY_LIGHT = new Color(227, 242, 253);
    private final Color DANGER = new Color(211, 47, 47);
    private final Color GRAY = new Color(117, 117, 117);

    public MahasiswaFrame() {

        setTitle("Data Mahasiswa");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        list = new ArrayList<>();

        list.add(new Mahasiswa("230001", "FeiXiao", "feixiao@gmail.com"));
        list.add(new Mahasiswa("230002", "Moze", "moze@gmail.com"));
        list.add(new Mahasiswa("230003", "JiaoQiu", "jiaoqiu@gmail.com"));
        list.add(new Mahasiswa("230004", "YaoGuang", "yaoguang@gmail.com"));
        list.add(new Mahasiswa("230005", "HuoHuo", "huohuo@gmail.com"));
        list.add(new Mahasiswa("230006", "YanQing", "yanqing@gmail.com"));
        list.add(new Mahasiswa("230007", "BaiLu", "bailu@gmail.com"));
        list.add(new Mahasiswa("230008", "HanYa", "hanya@gmail.com"));
        list.add(new Mahasiswa("230009", "GuNaiFen", "gunaifen@gmail.com"));
        list.add(new Mahasiswa("230010", "JingYuan", "jingyuan@gmail.com"));
        list.add(new Mahasiswa("230011", "DanHeng", "danheng@gmail.com"));
        list.add(new Mahasiswa("230012", "LuoCha", "luocha@gmail.com"));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(PRIMARY_LIGHT);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(PRIMARY_LIGHT);

        JLabel lblSearch = new JLabel("Search");
        lblSearch.setForeground(Color.BLACK);
        leftPanel.add(lblSearch);

        txtSearch = new JTextField(20);
        txtSearch.setBackground(Color.WHITE);
        txtSearch.setForeground(Color.BLACK);
        leftPanel.add(txtSearch);

        btnReset = new JButton("Reset");
        btnReset.setBackground(GRAY);
        btnReset.setForeground(Color.BLACK);
        btnReset.setFocusPainted(false);
        leftPanel.add(btnReset);

        topPanel.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(PRIMARY_LIGHT);

        btnTambah = new JButton("Tambah");
        btnTambah.setBackground(PRIMARY);
        btnTambah.setForeground(Color.BLACK);
        btnTambah.setFocusPainted(false);

        btnEdit = new JButton("Edit");
        btnEdit.setBackground(PRIMARY);
        btnEdit.setForeground(Color.BLACK);
        btnEdit.setFocusPainted(false);

        btnDelete = new JButton("Delete");
        btnDelete.setBackground(DANGER);
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setFocusPainted(false);

        rightPanel.add(btnTambah);
        rightPanel.add(btnEdit);
        rightPanel.add(btnDelete);

        topPanel.add(rightPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        model.addColumn("NIM");
        model.addColumn("Nama");
        model.addColumn("Email");

        table = new JTable(model);

        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setRowHeight(35);

        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(new Color(200, 200, 200));

        table.setRowHeight(30);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(new Color(200, 200, 200));

        // Header
        table.getTableHeader().setBackground(PRIMARY);
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
        table.getTableHeader().setPreferredSize(new java.awt.Dimension(100, 35));

        // Header rata tengah
        DefaultTableCellRenderer headerRenderer
                = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();

        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setRowHeight(30);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(new Color(200, 200, 200));

        // Warna saat dipilih
        table.setSelectionBackground(new Color(100, 181, 246));
        table.setSelectionForeground(Color.BLACK);

        // Baris selang-seling + rata tengah
        table.setDefaultRenderer(Object.class,
                new DefaultTableCellRenderer() {

            @Override
            public java.awt.Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                java.awt.Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                // Teks di tengah
                setHorizontalAlignment(SwingConstants.CENTER);

                if (!isSelected) {

                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(227, 242, 253));
                    }

                    c.setForeground(Color.BLACK);

                }

                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        tampilkanData("");

        // Font
        Font fontLabel = new Font("Arial", Font.BOLD, 16);
        Font fontButton = new Font("Arial", Font.BOLD, 15);
        Font fontText = new Font("Arial", Font.BOLD, 15);

        lblSearch.setFont(fontLabel);
        txtSearch.setFont(fontText);

        btnReset.setFont(fontButton);
        btnTambah.setFont(fontButton);
        btnEdit.setFont(fontButton);
        btnDelete.setFont(fontButton);

        // SEARCH
        txtSearch.addActionListener(e -> tampilkanData(txtSearch.getText()));

        // RESET
        btnReset.addActionListener(e -> {
            txtSearch.setText("");
            tampilkanData("");
        });

        // TAMBAH
        btnTambah.addActionListener(e -> {
            MahasiswaForm form = new MahasiswaForm(this);
            form.setVisible(true);
        });

        // EDIT
        btnEdit.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Pilih data yang ingin diedit!");
                return;
            }

            Mahasiswa m = list.get(row);

            MahasiswaForm form = new MahasiswaForm(this, m);
            form.setVisible(true);

        });

        // DELETE
        btnDelete.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Pilih data yang ingin dihapus!");
                return;
            }

            int pilihan = JOptionPane.showConfirmDialog(
                    this,
                    "Apakah Anda yakin ingin menghapus data ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION);

            if (pilihan == JOptionPane.YES_OPTION) {

                list.remove(row);

                refreshTable();

                JOptionPane.showMessageDialog(
                        this,
                        "Data berhasil dihapus.");

            }

        });

        setVisible(true);
    }

    private void tampilkanData(String keyword) {

        model.setRowCount(0);

        keyword = keyword.toLowerCase();

        for (Mahasiswa m : list) {

            if (keyword.isEmpty()
                    || m.getNim().toLowerCase().contains(keyword)
                    || m.getNama().toLowerCase().contains(keyword)
                    || m.getEmail().toLowerCase().contains(keyword)) {

                model.addRow(new Object[]{
                    m.getNim(),
                    m.getNama(),
                    m.getEmail()
                });

            }

        }

    }

    public void tambahMahasiswa(Mahasiswa m) {

        list.add(m);
        refreshTable();

    }

    public void refreshTable() {

        tampilkanData(txtSearch.getText());

    }

}
