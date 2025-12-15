package io.github.devindt99.resumefit.domain;

import java.util.ArrayList;
import java.util.List;

public class Resume {

    private final ArrayList<ResumeSection> sections;

    public Resume() {
        this.sections = new ArrayList<>();
    }

    public void addSection(ResumeSection section) {
        sections.add(section);
    }

    public List<ResumeSection> getSections() {
        return sections;
    }

        public String toPlainText() {
        StringBuilder sb = new StringBuilder();

        for (ResumeSection section : sections) {
            sb.append(section.getTitle()).append("\n");
            sb.append(section.getContent()).append("\n\n");
        }

        return sb.toString().trim();
    }

}
