package ru.stqa.pft.mantis.model;

public class BugifyIssue {
    private int id;
    private String subject;
    private String description;
    private String state_name;

    public int getId() {
        return id;
    }

    public BugifyIssue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public BugifyIssue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BugifyIssue withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getState_name() {
        return state_name;
    }

    public BugifyIssue withState_name(String state_name) {
        this.state_name = state_name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BugifyIssue that = (BugifyIssue) o;

        if (id != that.id) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
