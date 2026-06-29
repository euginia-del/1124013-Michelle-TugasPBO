package pbo;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MahasiswaForm extends JDialog {

    private JTextField txtNim;
    private JTextField txtNama;
    private JTextField txtEmail;

    private JLabel lblError;

    private JButton btnSimpan;

    private MahasiswaFrame parent;

    private Mahasiswa mahasiswa;

    private boolean editMode = false;

    public MahasiswaForm(MahasiswaFrame parent) {

        super(parent, true);

        this.parent = parent;

        initComponents();

    }

    public MahasiswaForm(MahasiswaFrame parent, Mahasiswa mahasiswa) {

        super(parent, true);

        this.parent = parent;
        this.mahasiswa = mahasiswa;

        editMode = true;

        initComponents();

        setTitle("Edit Mahasiswa");

        btnSimpan.setText("Update");

        txtNim.setText(mahasiswa.getNim());
        txtNama.setText(mahasiswa.getNama());
        txtEmail.setText(mahasiswa.getEmail());

    }

    private void initComponents() {

        setTitle("Tambah Mahasiswa");

        setSize(350, 300);

        setLocationRelativeTo(parent);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("NIM"), gbc);

        gbc.gridx = 1;
        txtNim = new JTextField(15);
        add(txtNim, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Nama"), gbc);

        gbc.gridx = 1;
        txtNama = new JTextField(15);
        add(txtNama, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Email"), gbc);

        gbc.gridx = 1;
        txtEmail = new JTextField(15);
        add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        lblError = new JLabel("");

        lblError.setForeground(Color.RED);

        add(lblError, gbc);

        gbc.gridy = 4;

        btnSimpan = new JButton("Add");

        add(btnSimpan, gbc);

        btnSimpan.addActionListener(e -> simpan());

    }

    private void simpan() {

        String nim = txtNim.getText().trim();
        String nama = txtNama.getText().trim();
        String email = txtEmail.getText().trim();

        if (nim.isEmpty() || nama.isEmpty() || email.isEmpty()) {

            lblError.setText("Semua field wajib diisi.");

            return;

        }

        if (editMode) {

            mahasiswa.setNim(nim);
            mahasiswa.setNama(nama);
            mahasiswa.setEmail(email);

            parent.refreshTable();

        } else {

            parent.tambahMahasiswa(
                    new Mahasiswa(nim, nama, email));

        }

        dispose();

    }

}
