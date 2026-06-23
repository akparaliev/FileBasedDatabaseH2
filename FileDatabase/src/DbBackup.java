import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class DbBackup {
    private final String FILENAME;

    public DbBackup(String filename) {
        FILENAME = filename;
    }

    public void RunEvery(int minutes, int seconds) {
        int miliseconds = (minutes * 60 + seconds) * 1000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Running backup every " + minutes + " minutes " + seconds + " seconds to file: " + FILENAME);
                CreateNewSnapshot();
                RemoveOldSnapshots();
            }
        }, 0, miliseconds);
    }

    private void CreateNewSnapshot() {
        try (FileInputStream fis = new FileInputStream(FILENAME)) {
            try (FileOutputStream fos = new FileOutputStream(createNewSnapshotName())) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            } catch (Exception e) {
                System.out.println("Error while creating backup: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error while reading original file for backup: " + e.getMessage());
        }
    }

    private String createNewSnapshotName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timeStamp = now.format(formatter);

        return FILENAME + "." + timeStamp;
    }

    private void RemoveOldSnapshots() {
        // Implementation for removing old snapshots
        System.out.println("Removing old snapshots - not implemented yet.");
    }
}