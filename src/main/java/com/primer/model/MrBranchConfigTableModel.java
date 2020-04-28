package com.primer.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author HIFeng
 */
public class MrBranchConfigTableModel {
    private final SimpleStringProperty sourceBranch;
    private final SimpleStringProperty targetBranch;

    public MrBranchConfigTableModel(String fName, String lName) {
        this.sourceBranch = new SimpleStringProperty(fName);
        this.targetBranch = new SimpleStringProperty(lName);
    }

    public String getSourceBranch() {
        return sourceBranch.get();
    }

    public SimpleStringProperty sourceBranchProperty() {
        return sourceBranch;
    }

    public void setSourceBranch(String sourceBranch) {
        this.sourceBranch.set(sourceBranch);
    }

    public String getTargetBranch() {
        return targetBranch.get();
    }

    public SimpleStringProperty targetBranchProperty() {
        return targetBranch;
    }

    public void setTargetBranch(String targetBranch) {
        this.targetBranch.set(targetBranch);
    }
}