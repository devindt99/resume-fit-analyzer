package io.github.devindt99.resumefit.domain;

public class JobRequirement {

    private final String skill;
    private final String category;

    public JobRequirement(String skill, String category) {
        this.skill = skill;
        this.category = category;
    }

    public String getSkill() {
        return skill;
    }

    public String getCategory() {
        return category;
    }

    public String getText() {
        return skill + " (" + category + ")";
    }
}
