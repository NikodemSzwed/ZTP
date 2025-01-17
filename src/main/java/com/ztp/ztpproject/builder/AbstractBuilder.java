package com.ztp.ztpproject.builder;

/**
 * Abstract builder class providing basic implementation of the IBuilder
 * interface. Encapsulates the logic for ensuring the correct file extension of
 * the export path.
 *
 *
 * @version 1.0
 */
public abstract class AbstractBuilder implements IBuilder {

    /**
     * Ensures that the export path ends with the specified target extension. If
     * the export path does not have an extension or has a different extension,
     * the target extension is appended or replaced accordingly.
     *
     * @param exportPath the path of the file to be exported
     * @param targetExtension the desired file extension (e.g., ".pdf", ".txt")
     * @return the export path with the correct file extension
     * @throws IllegalArgumentException if the export path is null or empty
     */

    protected String ensureCorrectExtension(String exportPath, String targetExtension) {
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
