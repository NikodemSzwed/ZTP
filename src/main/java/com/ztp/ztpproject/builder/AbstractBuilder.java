package com.ztp.ztpproject.builder;

public abstract class AbstractBuilder implements IBuilder {
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
