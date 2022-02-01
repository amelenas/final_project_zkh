package by.stepanovich.zkh.tablebuild;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class ReportSet implements Serializable {
    private static final long serialVersionUID = 1693L;

    private Set set;

    public ReportSet() {
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public Set getSet() {
        return set;
    }

    public ReportSet(Set set) {
        this.set = set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportSet reportSet)) return false;
        return getSet().equals(reportSet.getSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSet());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass());
        builder.append(" set = ").append(set);
        return builder.toString();
    }
}
