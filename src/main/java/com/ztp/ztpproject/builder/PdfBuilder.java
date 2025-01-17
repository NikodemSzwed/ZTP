package com.ztp.ztpproject.builder;

import com.ztp.ztpproject.models.Task;
import java.awt.*;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

/**
 * Class used to build a report in PDF format.
 *
 */
public class PdfBuilder extends AbstractBuilder {

    private StringBuilder content;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public PdfBuilder() {
        this.content = new StringBuilder();
    }

    /**
     * Adds a title to the report, containing the start and end time of the
     * report.
     *
     * @param timeFrom start time of the report
     * @param timeTo end time of the report
     */
    public void addTitle(Date timeFrom, Date timeTo) {
        String formattedTimeFrom = dateFormat.format(timeFrom);
        String formattedTimeTo = dateFormat.format(timeTo);

        content.append("Raport zadań od ")
                .append(formattedTimeFrom)
                .append(" do ")
                .append(formattedTimeTo)
                .append("\n\n");
    }

    /**
     * Adds a task to the report.
     *
     * @param task the task to be added to the report
     */
    public void addTask(Task task) {
        String formattedDeadline = dateFormat.format(task.getDeadline());
        content.append("Nazwa zadania: ").append(task.getName())
                .append("\n")
                .append("Ukończone: ").append(task.isDone() ? "Tak" : "Nie")
                .append("\n")
                .append("Priorytet: ").append(task.getPriority())
                .append("\n")
                .append("Deadline: ").append(formattedDeadline)
                .append("\n")
                .append("-".repeat(30))
                .append("\n");
    }

    /**
     * Adds a summary to the report, detailing the total number of tasks, the
     * number of completed tasks, and the number of pending tasks.
     *
     * @param tasks the list of tasks to be summarized
     */
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

    /**
     * Builds the report in PDF format and saves it to the specified path.
     *
     * @param exportPath path where the report should be saved
     */
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

    /**
     * Concrete implementation of the {@link IBuilder} interface responsible for
     * building a report in PDF format.
     *
     * This class provides the necessary methods to add a title, tasks, and a
     * summary to the report. The report is then built and saved to the
     * specified path using the {@link #build(String)} method.
     *
     */
    private static class ReportPrintable implements Printable {

        private final String content;

        public ReportPrintable(String content) {
            this.content = content;
        }

        /**
         * Prints the content onto a page using the specified graphics context.
         *
         * @param g the graphics context used for rendering the content
         * @param pageFormat the format of the page being printed
         * @param pageIndex the zero-based index of the page to be printed
         * @return PAGE_EXISTS if the page is rendered successfully;
         * NO_SUCH_PAGE if the page index is greater than 0
         * @throws PrinterException if an error occurs during printing
         */
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
}
