package io.github.devindt99.resumefit.domain;

import java.util.LinkedList;
import java.util.List;

public class ResumeSection {

    private final String title;
    private final String content;
    private final LinkedList<Skill> skills;

    public ResumeSection(String title) {
        this.title = title;
        this.content = "";
        this.skills = new LinkedList<>();
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() { 
        return content; 
    }
    
    public List<Skill> getSkills() {
        return skills;
    }
}
