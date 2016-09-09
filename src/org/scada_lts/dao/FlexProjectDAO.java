package org.scada_lts.dao;

/*
 * (c) 2016 Abil'I.T. http://abilit.eu/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

import br.org.scadabr.api.vo.FlexProject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * DAO for FlexProject.
 *
 * @author Mateusz Kaproń Abil'I.T. development team, sdt@abilit.eu
 */

public class FlexProjectDAO {

	private static final Log LOG = LogFactory.getLog(FlexProjectDAO.class);

	private static final String COLUMN_NAME_ID = "id";
	private static final String COLUMN_NAME_NAME = "name";
	private static final String COLUMN_NAME_DESCRIPTION = "description";
	private static final String COLUMN_NAME_XMLCONFIG = "xmlConfig";

	private static final String FLEX_PROJECT_SELECT = ""
			+ "select "
				+ COLUMN_NAME_ID + ", "
				+ COLUMN_NAME_NAME + ", "
				+ COLUMN_NAME_DESCRIPTION + ", "
				+ COLUMN_NAME_XMLCONFIG + " "
			+ "from "
				+ "flexProjects";

	private static final String FLEX_PROJECT_INSERT = ""
			+ "insert into flexProjects ("
				+ COLUMN_NAME_NAME + ", "
				+ COLUMN_NAME_DESCRIPTION + ", "
				+ COLUMN_NAME_XMLCONFIG
			+ ") "
			+ "values (?,?,?)";

	private static final String FLEX_PROJECT_UPDATE = ""
			+ "update flexProjects set "
				+ COLUMN_NAME_NAME + "=?, "
				+ COLUMN_NAME_DESCRIPTION + "=?, "
				+ COLUMN_NAME_XMLCONFIG + "=? "
			+ "where "
				+ COLUMN_NAME_ID + "=?";

	private static final String FLEX_PROJECT_DELETE = ""
			+ "delete from flexProjects where "
				+ COLUMN_NAME_ID + "=?";


	private class FlexProjectRowMapper implements RowMapper<FlexProject> {

		@Override
		public FlexProject mapRow(ResultSet resultSet, int i) throws SQLException {
			FlexProject fP = new FlexProject();
			fP.setId(resultSet.getInt(COLUMN_NAME_ID));
			fP.setName(resultSet.getString(COLUMN_NAME_NAME));
			fP.setDescription(resultSet.getString(COLUMN_NAME_DESCRIPTION));
			fP.setXmlConfig(resultSet.getString(COLUMN_NAME_XMLCONFIG));
			return fP;
		}
	}

	public FlexProject getFlexProject(int id) {

		if (LOG.isTraceEnabled()) {
			LOG.trace("getFlexProject(int id) id:" + id);
		}

		String templateSelectWhereId = FLEX_PROJECT_SELECT + " where " + COLUMN_NAME_ID + "=? ";

		return DAO.getInstance().getJdbcTemp().queryForObject(templateSelectWhereId, new Object[]{id}, new FlexProjectRowMapper());
	}

	@Transactional(readOnly = false,propagation= Propagation.REQUIRES_NEW,isolation= Isolation.READ_COMMITTED,rollbackFor=SQLException.class)
	public int insert(String name, String description, String xmlConfig) {

		if (LOG.isTraceEnabled()) {
			LOG.trace("insertFlexProject(String name, String description, String xmlConfig) name" + name + ", description:" + description + ", xmlConfig" + xmlConfig);
		}

		DAO.getInstance().getJdbcTemp().update(FLEX_PROJECT_INSERT, new Object[] {name, description, xmlConfig},
				new int [] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
		return DAO.getInstance().getId();

	}

	@Transactional(readOnly = false,propagation= Propagation.REQUIRES_NEW,isolation= Isolation.READ_COMMITTED,rollbackFor=SQLException.class)
	public int update(int id, String name, String description, String xmlConfig) {

		if (LOG.isTraceEnabled()) {
			LOG.trace("updateFlexProject(int id, String name, String descriptor, String xmlConfig) id:" + id + ", name" + name + ", description:" + description + ", xmlConfig" + xmlConfig);
		}

		DAO.getInstance().getJdbcTemp().update(FLEX_PROJECT_UPDATE, new Object[] {name, description, xmlConfig, id},
				new int [] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER});
		return DAO.getInstance().getId();
	}

	@Transactional(readOnly = false,propagation= Propagation.REQUIRES_NEW,isolation= Isolation.READ_COMMITTED,rollbackFor=SQLException.class)
	public void delete(int id) {

		if (LOG.isTraceEnabled()) {
			LOG.trace("deleteFlexProject(int id) id:" + id);
		}

		DAO.getInstance().getJdbcTemp().update(FLEX_PROJECT_DELETE, new Object[] {id},
				new int [] {Types.INTEGER});
	}

	public List<FlexProject> getFlexProjects() {

		if (LOG.isTraceEnabled()) {
			LOG.trace("getFlexProjects()");
		}

		return DAO.getInstance().getJdbcTemp().query(FLEX_PROJECT_SELECT, new FlexProjectRowMapper());
	}
}
