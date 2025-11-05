# Developer Workflow Guide

## Purpose
This guide defines the *simple* workflow every developer must follow:
- Never push or commit directly to `development`
- Use feature/bugfix branches for all work
- Use GitHub Copilot as a helper, not a black-box copy/paste
- Create Pull Requests (PRs), assign the Lead for review, and wait for approval before merging

---

## Branches (simple)
- `main` — protected, release-ready
- `development` — protected, integration branch (only Lead can merge)
- Feature branches — created from `development`

### Branch name format (required)
- Feature: `feature/<service>-<short-description>`
  - Example: `feature/walletotp-otp-validation`
- Bugfix: `bugfix/<service>-<short-description>`
  - Example: `bugfix/walletotp-nullpointer-fix`
- Hotfix (rare): `hotfix/<short-description>` — created from `main`

---

## Step-by-step for Developers

1. Sync `development`
```bash
git checkout development
git pull origin development
