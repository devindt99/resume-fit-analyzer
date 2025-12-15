package io.github.devindt99.resumefit.domain;

/**
 * Pure domain value object representing a skill.
 * Immutable, behavior-free record with two fields: name and category.
 */
public record Skill(String name, String category) {
}
