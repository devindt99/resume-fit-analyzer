# Resume Fit Analyzer

A proof-of-concept Java application that scores how well a resume matches a job posting using semantic text embeddings.

## Overview
- Parses resumes and job requirements into structured domain models
- Converts text into vector embeddings via Hugging Face Inference API
- Computes cosine similarity as a fit score
- Designed with swappable embedding providers and testability in mind

## Tech Stack
- Java 17
- Maven
- Hugging Face Transformers (sentence-transformers/all-MiniLM-L6-v2)
- Apache HttpClient 5
- JUnit 5

## Running locally

Set your Hugging Face API token:

```powershell
$env:HF_API_TOKEN="your_token_here"
