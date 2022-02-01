package by.stepanovich.zkh.entity;

import java.io.Serializable;
import java.util.Objects;

public class TypeOfWork implements Serializable {
    private static final long serialVersionUID = 169L;
    long idTypeOfWork;
    String nameOfWork;

    public TypeOfWork() {
    }

    public TypeOfWork(long idTypeOfWork, String nameOfWork) {
        this.idTypeOfWork = idTypeOfWork;
        this.nameOfWork = nameOfWork;
    }

    public long getIdTypeOfWork() {
        return idTypeOfWork;
    }

    public void setIdTypeOfWork(long idTypeOfWork) {
        this.idTypeOfWork = idTypeOfWork;
    }

    public String getNameOfWork() {
        return nameOfWork;
    }

    public void setNameOfWork(String nameOfWork) {
        this.nameOfWork = nameOfWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeOfWork)) return false;
        TypeOfWork that = (TypeOfWork) o;
        return getIdTypeOfWork() == that.getIdTypeOfWork() && getNameOfWork().equals(that.getNameOfWork());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdTypeOfWork(), getNameOfWork());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass());
        builder.append(" idTypeOfWork = ").append(idTypeOfWork);
        builder.append(" nameOfWork = ").append(nameOfWork);
        return builder.toString();
    }
}
