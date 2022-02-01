package by.stepanovich.zkh.dao;

import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.SiteOfWork;
import by.stepanovich.zkh.entity.TypeOfWork;

import java.util.List;

public interface WorkDao {
     List<SiteOfWork> findAllSitesOfWork() throws DaoException;

     List<TypeOfWork> findAllTypesOfWork() throws DaoException;
}
