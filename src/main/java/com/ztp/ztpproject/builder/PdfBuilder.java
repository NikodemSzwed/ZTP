package com.ztp.ztpproject.builder;
import com.ztp.ztpproject.models.Task;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.*;
import java.awt.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

public class PdfBuilder implements IBuilder {
    private StringBuilder content;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public PdfBuilder() {
        this.content = new StringBuilder();
    }

    public void addTitle(Date timeFrom, Date timeTo) {
        String formattedTimeFrom = dateFormat.format(timeFrom);
        String formattedTimeTo = dateFormat.format(timeTo);

        content.append("Raport zadań od ")
            .append(formattedTimeFrom)
            .append(" do ")
            .append(formattedTimeTo)
            .append("\n\n");
    }

    public void addTask(Task task) {
        String formattedDeadline = dateFormat.format(task.getDeadline());
        content.append("Nazwa zadania: ").append(task.getName()).append("\n")
            .append("Ukończone: ").append(task.isDone() ? "Tak" : "Nie").append("\n")
            .append("Priorytet: ").append(task.getPriority()).append("\n")
            .append("Deadline: ").append(formattedDeadline).append("\n")
            .append(new String(new char[30]).replace("\0", "-")).append("\n");
    }

    public void addSummary(List<Task> tasks) {
        int doneTasks = 0;
        int pendingTasks = 0;

        for (Task task : tasks) {
            if (task.isDone()) {
                doneTasks++;
            } else {
                pendingTasks++;
            }
        }

        content.append("\nPodsumowanie raportu:\n")
            .append("Liczba zadań: ").append(tasks.size()).append("\n")
            .append("Liczba zadań zrealizowanych: ").append(doneTasks).append("\n")
            .append("Liczba zadań niezrealizowanych: ").append(pendingTasks).append("\n");
    }

    public void build(String exportPath) {
        String correctExportPath = ensureCorrectExtension(exportPath, ".pdf");

        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(new ReportPrintable(content.toString()));

            HashPrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            attributes.add(MediaSizeName.ISO_A4);

            if (job.printDialog()) {
                job.print(attributes);
                System.out.println("Raport PDF zapisany: " + correctExportPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ReportPrintable implements Printable {
        private final String content;

        public ReportPrintable(String content) {
            this.content = content;
        }

        @Override
        public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex > 0) {
                return NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            Font font = new Font("Serif", Font.PLAIN, 12);
            g2d.setFont(font);

            int x = 100;
            int y = 100;
            int lineHeight = g2d.getFontMetrics(font).getHeight();

            for (String line : content.split("\n")) {
                g2d.drawString(line, x, y);
                y += lineHeight;
            }

            return PAGE_EXISTS;
        }
    }

    private String ensureCorrectExtension(String exportPath, String targetExtension) {
        if (exportPath == null || exportPath.isEmpty()) {
            throw new IllegalArgumentException("Ścieżka pliku nie może być pusta.");
        }
    
        int lastDotIndex = exportPath.lastIndexOf('.');
        int lastSlashIndex = exportPath.lastIndexOf('/');
    
        if (lastDotIndex == -1 || lastDotIndex < lastSlashIndex) {
            return exportPath + targetExtension;
        }
        if (lastDotIndex == exportPath.length() - 1) {
            return exportPath.substring(0, lastDotIndex) + targetExtension;
        }
    
        String currentExtension = exportPath.substring(lastDotIndex);
        if (!currentExtension.equalsIgnoreCase(targetExtension)) {
            return exportPath.substring(0, lastDotIndex) + targetExtension;
        }

        return exportPath;
    }
}
