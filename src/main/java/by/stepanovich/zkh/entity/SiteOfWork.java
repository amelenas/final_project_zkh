package by.stepanovich.zkh.entity;

import java.io.Serializable;
import java.util.Objects;

public class SiteOfWork implements Serializable {
    private static final long serialVersionUID = 189L;
    long idSiteOfWork;
    String nameOfRegion;

    public SiteOfWork() {
    }

    public SiteOfWork(long idSiteOfWork, String nameOfRegion) {
        this.idSiteOfWork = idSiteOfWork;
        this.nameOfRegion = nameOfRegion;
    }

    public long getIdSiteOfWork() {
        return idSiteOfWork;
    }

    public void setIdSiteOfWork(long idSiteOfWork) {
        this.idSiteOfWork = idSiteOfWork;
    }

    public String getNameOfRegion() {
        return nameOfRegion;
    }

    public void setNameOfRegion(String nameOfRegion) {
        this.nameOfRegion = nameOfRegion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SiteOfWork)) return false;
        SiteOfWork that = (SiteOfWork) o;
        return getIdSiteOfWork() == that.getIdSiteOfWork() && getNameOfRegion().equals(that.getNameOfRegion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdSiteOfWork(), getNameOfRegion());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass());
        builder.append(" idSiteOfWork = ").append(idSiteOfWork);
        builder.append(" nameOfRegion = ").append(nameOfRegion);
        return builder.toString();
    }
}
