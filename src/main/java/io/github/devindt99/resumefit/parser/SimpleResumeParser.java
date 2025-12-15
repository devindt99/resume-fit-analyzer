package io.github.devindt99.resumefit.parser;

import io.github.devindt99.resumefit.domain.Resume;
import io.github.devindt99.resumefit.domain.ResumeSection;
import io.github.devindt99.resumefit.domain.Skill;

public class SimpleResumeParser implements ResumeParser {

    @Override
    public Resume parse(String rawText) {
        Resume resume = new Resume();

        // Extremely naive parsing logic for now
        // Format assumption:
        // Section: Backend
        // - Java
        // - Spring

        ResumeSection currentSection = null;

        for (String line : rawText.split("\n")) {
            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }

            if (line.startsWith("Section:")) {
                String title = line.substring("Section:".length()).trim();
                currentSection = new ResumeSection(title);
                resume.addSection(currentSection);
            } else if (line.startsWith("-") && currentSection != null) {
                String skillName = line.substring(1).trim();
                currentSection.addSkill(new Skill(skillName, "Uncategorized"));
            }
        }

        return resume;
    }
}
