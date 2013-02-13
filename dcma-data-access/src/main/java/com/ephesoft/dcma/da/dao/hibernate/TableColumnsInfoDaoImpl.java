/********************************************************************************* 
* Ephesoft is a Intelligent Document Capture and Mailroom Automation program 
* developed by Ephesoft, Inc. Copyright (C) 2010-2012 Ephesoft Inc. 
* 
* This program is free software; you can redistribute it and/or modify it under 
* the terms of the GNU Affero General Public License version 3 as published by the 
* Free Software Foundation with the addition of the following permission added 
* to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED WORK 
* IN WHICH THE COPYRIGHT IS OWNED BY EPHESOFT, EPHESOFT DISCLAIMS THE WARRANTY 
* OF NON INFRINGEMENT OF THIRD PARTY RIGHTS. 
* 
* This program is distributed in the hope that it will be useful, but WITHOUT 
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
* FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more 
* details. 
* 
* You should have received a copy of the GNU Affero General Public License along with 
* this program; if not, see http://www.gnu.org/licenses or write to the Free 
* Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 
* 02110-1301 USA. 
* 
* You can contact Ephesoft, Inc. headquarters at 111 Academy Way, 
* Irvine, CA 92617, USA. or at email address info@ephesoft.com. 
* 
* The interactive user interfaces in modified source and object code versions 
* of this program must display Appropriate Legal Notices, as required under 
* Section 5 of the GNU Affero General Public License version 3. 
* 
* In accordance with Section 7(b) of the GNU Affero General Public License version 3, 
* these Appropriate Legal Notices must retain the display of the "Ephesoft" logo. 
* If the display of the logo is not reasonably feasible for 
* technical reasons, the Appropriate Legal Notices must display the words 
* "Powered by Ephesoft". 
********************************************************************************/ 

package com.ephesoft.dcma.da.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinFragment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ephesoft.dcma.core.dao.hibernate.HibernateDao;
import com.ephesoft.dcma.da.dao.TableColumnsInfoDao;
import com.ephesoft.dcma.da.domain.TableColumnsInfo;
import com.ephesoft.dcma.da.domain.TableInfo;

/**
 * Implementation of a Dao representing table_columns_info table in database.
 * 
 * @author Ephesoft
 * @version 1.0
 * @see com.ephesoft.dcma.da.dao.TableColumnsInfoDao
 */
@Repository
public class TableColumnsInfoDaoImpl extends HibernateDao<TableColumnsInfo> implements TableColumnsInfoDao {

	/**
	 * LOGGER to print the logging information.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TableColumnsInfoDaoImpl.class);
	
	/**
	 * An API to fetch all TableColumnsInfo by TableInfo.
	 * 
	 * @param tableInfo TableInfo
	 * @return List<TableColumnsInfo>
	 */
	@Override
	public List<TableColumnsInfo> getTableColumnsInfoByTableInfo(TableInfo tableInfo) {

		LOGGER.info("TableInfo : " + tableInfo);
		DetachedCriteria criteria = criteria();
		criteria.add(Restrictions.eq("tableInfo", tableInfo));

		return find(criteria);

	}

	/**
	 * An API to fetch all TableColumnsInfo by document type name and table name.
	 * 
	 * @param docTypeName String
	 * @param tableName String
	 * @return List<TableInfo>
	 */
	@Override
	public List<TableColumnsInfo> getTableColumnsInfo(String docTypeName, String tableName) {

		LOGGER.info("docTypeName  : " + docTypeName);
		DetachedCriteria criteria = criteria();
		criteria.createAlias("tableInfo", "tableInfo", JoinFragment.INNER_JOIN);
		criteria.add(Restrictions.eq("tableInfo.name", tableName));
		criteria.createAlias("tableInfo.docType", "docType", JoinFragment.INNER_JOIN);
		criteria.add(Restrictions.eq("docType.name", docTypeName));

		return find(criteria);

	}

	/**
	 * An API to insert the TableColumnsInfo object.
	 * 
	 * @param TableColumnsInfo TableColumnsInfo
	 */
	public void insertTableColumnsInfo(TableColumnsInfo TableColumnsInfo) {
		create(TableColumnsInfo);
	}

	/**
	 * An API to update the TableColumnsInfo object.
	 * 
	 * @param TableColumnsInfo TableColumnsInfo
	 */
	public void updateTableColumnsInfo(TableColumnsInfo TableColumnsInfo) {
		saveOrUpdate(TableColumnsInfo);
	}

	/**
	 * An API to remove the TableColumnsInfo object.
	 * 
	 * @param TableColumnsInfo TableColumnsInfo
	 */
	public void removeTableColumnsInfo(TableColumnsInfo TableColumnsInfo) {
		remove(TableColumnsInfo);
	}

}