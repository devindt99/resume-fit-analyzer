package io.github.devindt99.resumefit.domain;

import java.util.ArrayList;
import java.util.List;

public class JobPosting {

    private final ArrayList<JobRequirement> requirements;

    public JobPosting() {
        this.requirements = new ArrayList<>();
    }

    public void addRequirement(JobRequirement requirement) {
        requirements.add(requirement);
    }

    public List<JobRequirement> getRequirements() {
        return requirements;
    }

    public String toPlainText() {
        StringBuilder sb = new StringBuilder();

        for (JobRequirement requirement : requirements) {
            sb.append(requirement.getText()).append("\n");
        }

        return sb.toString().trim();
    }
}
