#  Camera Application

A simple Java Swing desktop app for capturing photos using your webcam and managing them in a gallery.

##  Features

- Real-time camera capture
- Photo gallery with grid view
- View, delete, or clear images
- Auto-save with timestamps

##  Tech Stack

- Java Swing
- OpenCV (computer vision & camera access)
- Nu Pattern OpenCV (Java bindings)

##  Quick Start

1. **Clone the repo**
   ```bash
   git clone https://github.com/Leangheng-huge/camera-application.git
   cd camera-application
   ```

2. **Add OpenCV dependency** (Maven)
   ```xml
   <dependency>
       <groupId>org.openpnp</groupId>
       <artifactId>opencv</artifactId>
       <version>4.7.0-0</version>
   </dependency>
   ```

3. **Run**
   ```bash
   javac -cp ".:opencv-*.jar" *.java
   java -cp ".:opencv-*.jar" Main
   ```

##  Usage

- **Main Menu**: Choose Camera, Gallery, or Exit
- **Camera**: Click "Take Picture" to capture, "Back" to return
- **Gallery**: View/delete images, browse in 3-column grid

Images saved to `src/gallery/` as `IMG_yyyyMMdd_HHmmss.png`

##  Troubleshooting

- **Camera not found**: Check webcam connection and permissions
- **OpenCV errors**: Verify library is in classpath and matches system architecture
- **Can't save**: Ensure write permissions in `src/gallery/`

## Created By: 

Leangheng Korn - [@Pegasuarr](https://github.com/Pegasuarr)
