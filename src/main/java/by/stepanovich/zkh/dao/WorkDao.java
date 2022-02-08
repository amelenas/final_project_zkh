package by.stepanovich.zkh.dao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.SiteOfWork;
import by.stepanovich.zkh.entity.TypeOfWork;

import java.util.List;
import java.util.Set;

public interface WorkDao {
     List<SiteOfWork> findAllSitesOfWork() throws DaoException;

     List<TypeOfWork> findAllTypesOfWork() throws DaoException;

     List<Long>findPerformerForWork(long typeOfWorksId, long siteOfWork) throws DaoException;

     boolean registerRunWork(long userId, long orderId) throws DaoException;

     List<Long> findAllOrdersByEmployeeId(long employeeId) throws DaoException;

     boolean cancelEmployeeOrder(long userId, long orderId) throws DaoException;

     boolean cancelAllEmployeeOrder(long EmployeeId) throws DaoException;

     boolean registerEmployee(long siteOfWorkId, long typeOfWorkId, long employeeId) throws DaoException;

     Set<Long> findAllNewEmployeeId() throws DaoException;

     List<Long> findWorkPerformerId(long orderId) throws DaoException;

     boolean changeEmployeeStatus(long userId, boolean isActive, boolean isOnApproval) throws DaoException;
}
