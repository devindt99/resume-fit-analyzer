package io.github.devindt99.resumefit.parser;

import io.github.devindt99.resumefit.domain.Resume;

public interface ResumeParser {

    Resume parse(String rawText);
}
