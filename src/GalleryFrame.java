import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class GalleryFrame extends JFrame {
    private JPanel imagePanel;
    private ArrayList<File> imageFiles;
    private JLabel countLabel;
    private static final String GALLERY_DIR = "src/gallery";
    private File selectedImageFile = null;

    public GalleryFrame() {
        setTitle("Gallery");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load images from gallery directory
        loadImages();

        // Check if gallery is empty
        if (imageFiles.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Gallery is Empty",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new MainMenuFrame();
            return;
        }

        // Create UI components
        imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(0, 3, 10, 10));
        imagePanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        JPanel bottomPanel = new JPanel(new BorderLayout());

        countLabel = new JLabel("Total Images: " + imageFiles.size());
        countLabel.setFont(new Font("Arial", Font.BOLD, 14));
        countLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel();
        JButton viewBtn = new JButton("1. View Image");
        JButton deleteSelectedBtn = new JButton("2. Delete Selected");
        JButton deleteAllBtn = new JButton("3. Delete All");
        JButton backBtn = new JButton("4. Back");

        Font btnFont = new Font("Arial", Font.BOLD, 12);
        viewBtn.setFont(btnFont);
        deleteSelectedBtn.setFont(btnFont);
        deleteAllBtn.setFont(btnFont);
        backBtn.setFont(btnFont);

        buttonPanel.add(viewBtn);
        buttonPanel.add(deleteSelectedBtn);
        buttonPanel.add(deleteAllBtn);
        buttonPanel.add(backBtn);

        bottomPanel.add(countLabel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);


        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


        displayImages();


        viewBtn.addActionListener(e -> viewSelectedImage());

        deleteSelectedBtn.addActionListener(e -> deleteSelectedImage());

        deleteAllBtn.addActionListener(e -> deleteAllImages());

        backBtn.addActionListener(e -> {
            this.dispose();
            new MainMenuFrame();
        });

        setVisible(true);
    }

    private void loadImages() {
        imageFiles = new ArrayList<>();
        File galleryDir = new File(GALLERY_DIR);

        if (galleryDir.exists() && galleryDir.isDirectory()) {
            File[] files = galleryDir.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".png") ||
                            name.toLowerCase().endsWith(".jpg") ||
                            name.toLowerCase().endsWith(".jpeg"));

            if (files != null) {
                for (File file : files) {
                    imageFiles.add(file);
                }
            }
        }
    }

    private void displayImages() {
        imagePanel.removeAll();

        for (File file : imageFiles) {
            try {
                BufferedImage img = ImageIO.read(file);
                Image scaledImg = img.getScaledInstance(250, 200, Image.SCALE_SMOOTH);

                JLabel imgLabel = new JLabel(new ImageIcon(scaledImg));
                imgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                imgLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

                // Add click listener to select image
                imgLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        // Deselect all
                        for (Component comp : imagePanel.getComponents()) {
                            if (comp instanceof JLabel) {
                                ((JLabel) comp).setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                            }
                        }
                        // Select clicked
                        imgLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
                        selectedImageFile = file;
                    }
                });

                imagePanel.add(imgLabel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        imagePanel.revalidate();
        imagePanel.repaint();
        countLabel.setText("Total Images: " + imageFiles.size());
    }

    private void viewSelectedImage() {
        if (selectedImageFile == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select an image first!",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            BufferedImage img = ImageIO.read(selectedImageFile);
            Image scaledImg = img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);

            JLabel imgLabel = new JLabel(new ImageIcon(scaledImg));
            JOptionPane.showMessageDialog(this,
                    imgLabel,
                    selectedImageFile.getName(),
                    JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading image!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedImage() {
        if (selectedImageFile == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select an image first!",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this image?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            if (selectedImageFile.delete()) {
                imageFiles.remove(selectedImageFile);
                selectedImageFile = null;

                if (imageFiles.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Gallery is now empty!",
                            "Info",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new MainMenuFrame();
                } else {
                    displayImages();
                    JOptionPane.showMessageDialog(this,
                            "Image deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to delete image!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteAllImages() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete ALL images?",
                "Confirm Delete All",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            boolean allDeleted = true;

            for (File file : imageFiles) {
                if (!file.delete()) {
                    allDeleted = false;
                }
            }

            if (allDeleted) {
                JOptionPane.showMessageDialog(this,
                        "All images deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new MainMenuFrame();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Some images could not be deleted!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                loadImages();
                displayImages();
            }
        }
    }
}