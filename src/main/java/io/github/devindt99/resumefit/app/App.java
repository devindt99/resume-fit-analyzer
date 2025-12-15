package io.github.devindt99.resumefit.app;

import io.github.devindt99.resumefit.domain.JobPosting;
import io.github.devindt99.resumefit.domain.JobRequirement;
import io.github.devindt99.resumefit.domain.Resume;
import io.github.devindt99.resumefit.embedding.HuggingFaceTextEmbedder;
import io.github.devindt99.resumefit.embedding.MockTextEmbedder;
import io.github.devindt99.resumefit.embedding.TextEmbedder;
import io.github.devindt99.resumefit.parser.ResumeParser;
import io.github.devindt99.resumefit.parser.SimpleResumeParser;
import io.github.devindt99.resumefit.scoring.FitScorer;

public class App {

    public static void main(String[] args) throws Exception {

        String resumeText = """
                Section: Backend
                - Java
                - Spring

                Section: Machine Learning
                - NLP
                """;

        ResumeParser parser = new SimpleResumeParser();
        Resume resume = parser.parse(resumeText);

        JobPosting jobPosting = new JobPosting();
        jobPosting.addRequirement(new JobRequirement("Java", "Programming"));
        jobPosting.addRequirement(new JobRequirement("Python", "Programming"));
        jobPosting.addRequirement(new JobRequirement("NLP", "Machine Learning"));

        TextEmbedder embedder =
                new HuggingFaceTextEmbedder(System.getenv("HF_API_TOKEN"));

        FitScorer scorer = new FitScorer(embedder);


        double score = scorer.score(
            resume.toPlainText(),
            jobPosting.toPlainText()
        );


        System.out.println("Resume fit score: " + score);
    }
}
