import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import nu.pattern.OpenCV;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraFrame extends JFrame {
    private JLabel cameraScreen;
    private VideoCapture camera;
    private Mat frame;
    private Timer timer;
    private static final String GALLERY_DIR = "src/gallery";

    static {

        OpenCV.loadLocally();
    }

    public CameraFrame() {
        setTitle("Camera");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);


        File galleryDir = new File(GALLERY_DIR);
        if (!galleryDir.exists()) {
            galleryDir.mkdirs();
        }


        camera = new VideoCapture(0);
        frame = new Mat();


        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(this,
                    "Camera Not Found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            this.dispose();
            new MainMenuFrame();
            return;
        }


        cameraScreen = new JLabel();
        cameraScreen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        cameraScreen.setHorizontalAlignment(JLabel.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton captureBtn = new JButton("Take Picture");
        JButton backBtn = new JButton("Back");

        captureBtn.setFont(new Font("Arial", Font.BOLD, 14));
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(captureBtn);
        buttonPanel.add(backBtn);


        setLayout(new BorderLayout(10, 10));
        add(cameraScreen, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


        startCamera();


        captureBtn.addActionListener(e -> captureImage());

        backBtn.addActionListener(e -> {
            stopCamera();
            this.dispose();
            new MainMenuFrame();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopCamera();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void startCamera() {
        timer = new Timer(33, e -> {
            if (camera.read(frame)) {
                Image img = matToBufferedImage(frame);
                ImageIcon icon = new ImageIcon(img);
                cameraScreen.setIcon(icon);
            }
        });
        timer.start();
    }

    private void stopCamera() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        if (camera != null && camera.isOpened()) {
            camera.release();
        }
    }

    private void captureImage() {
        if (!frame.empty()) {
            // Generate filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());
            String filename = GALLERY_DIR + "/IMG_" + timestamp + ".png";

            // Save image
            boolean success = Imgcodecs.imwrite(filename, frame);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Photo Saved Successfully!\n" + filename,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to Save Photo!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] buffer = new byte[bufferSize];
        mat.get(0, 0, buffer);

        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((java.awt.image.DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);

        return image;
    }
}