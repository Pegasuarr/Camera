# 📷 Camera Application

A simple Java Swing desktop application that allows users to capture photos using their webcam and manage them in a gallery.

## ✨ Features

- **Camera Capture**: Access your webcam and take photos in real-time
- **Gallery Management**: View all captured images in a grid layout
- **Image Operations**: 
  - View images in full size
  - Delete individual images
  - Delete all images at once
- **Auto-save**: Photos are automatically saved with timestamps
- **Clean UI**: Simple and intuitive Swing-based interface

## 🛠️ Technologies Used

- **Java Swing**: GUI framework
- **OpenCV**: Computer vision library for camera access and image processing
- **Nu Pattern OpenCV**: OpenCV Java bindings loader

## 📋 Prerequisites

- Java Development Kit (JDK) 8 or higher
- OpenCV library
- Nu Pattern OpenCV library

## 📦 Dependencies

Add these dependencies to your project:

```xml
<!-- For Maven projects -->
<dependency>
    <groupId>org.openpnp</groupId>
    <artifactId>opencv</artifactId>
    <version>4.7.0-0</version>
</dependency>
```

Or download the JAR files manually:
- OpenCV Java bindings
- Nu Pattern OpenCV

## 🚀 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Leangheng-huge/camera-application.git
   cd camera-application
   ```

2. **Set up the project structure**
   ```
   camera-application/
   ├── src/
   │   ├── Main.java
   │   ├── MainMenuFrame.java
   │   ├── CameraFrame.java
   │   ├── GalleryFrame.java
   │   └── gallery/          # Auto-created for storing images
   └── README.md
   ```

3. **Add OpenCV libraries to your project**
   - Add the required JAR files to your classpath
   - Or use Maven/Gradle dependency management

4. **Compile the project**
   ```bash
   javac -cp ".:opencv-*.jar" *.java
   ```

5. **Run the application**
   ```bash
   java -cp ".:opencv-*.jar" Main
   ```

## 💻 Usage

### Main Menu
When you launch the application, you'll see three options:
1. **Open Camera** - Launch the camera interface
2. **Gallery** - View saved photos
3. **Exit** - Close the application

### Camera Mode
- The camera feed displays in real-time
- Click **"Take Picture"** to capture and save an image
- Click **"Back"** to return to the main menu
- Images are saved to `src/gallery/` with timestamp filenames

### Gallery Mode
- View all captured images in a 3-column grid
- Click on an image to select it (blue border indicates selection)
- **View Image** - Display the selected image in full size
- **Delete Selected** - Remove the selected image
- **Delete All** - Clear the entire gallery
- **Back** - Return to the main menu

## 📁 Project Structure

```
src/
├── Main.java              # Application entry point
├── MainMenuFrame.java     # Main menu GUI
├── CameraFrame.java       # Camera capture interface
├── GalleryFrame.java      # Image gallery interface
└── gallery/               # Directory for stored images
    └── IMG_*.png          # Captured images
```

## 🎯 Key Features Explained

### Image Naming Convention
Images are automatically named using the pattern:
```
IMG_yyyyMMdd_HHmmss.png
```
Example: `IMG_20240204_143052.png`

### Supported Image Formats
- PNG (default for captures)
- JPG/JPEG (gallery viewing)

### Camera Compatibility
The application uses OpenCV's VideoCapture with device index 0, which typically represents the default webcam.

## ⚠️ Troubleshooting

**Camera Not Found Error**
- Ensure your webcam is connected and not being used by another application
- Check camera permissions in your operating system
- Try changing the camera index in `CameraFrame.java` (from 0 to 1, 2, etc.)

**OpenCV Loading Issues**
- Verify OpenCV libraries are in the classpath
- Check that the OpenCV version matches your system architecture (32-bit vs 64-bit)

**Images Not Saving**
- Ensure the application has write permissions in the `src/gallery/` directory
- Check available disk space

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 👤 Author

Leangheng Korn - [@Leangheng-huge](https://github.com/Leangheng-huge)

## 🙏 Acknowledgments

- OpenCV community for the excellent computer vision library
- Nu Pattern for the OpenCV Java bindings loader

## 📸 Screenshots

*Add screenshots of your application here*

---

**Note**: Make sure to grant camera permissions when running the application for the first time.
