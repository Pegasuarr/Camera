import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    public MainMenuFrame() {
        setTitle("Camera Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));


        JButton openCameraBtn = new JButton("1. Open Camera");
        JButton galleryBtn = new JButton("2. Gallery");
        JButton exitBtn = new JButton("3. Exit");


        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        openCameraBtn.setFont(buttonFont);
        galleryBtn.setFont(buttonFont);
        exitBtn.setFont(buttonFont);


        openCameraBtn.addActionListener(e -> {
            this.dispose();
            new CameraFrame();
        });

        galleryBtn.addActionListener(e -> {
            this.dispose();
            new GalleryFrame();
        });

        exitBtn.addActionListener(e -> {
            System.exit(0);
        });


        mainPanel.add(openCameraBtn);
        mainPanel.add(galleryBtn);
        mainPanel.add(exitBtn);

        add(mainPanel);
        setVisible(true);
    }
}